package com.senai.apimuralvagas.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.senai.apimuralvagas.exceptions.TokenInvalidoException;
import com.senai.apimuralvagas.repositorys.AdminRepo;
import com.senai.apimuralvagas.repositorys.EmpresaRepo;
import com.senai.apimuralvagas.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

 @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    var token = this.recoverToken(request);

    if (token != null) {
        try {
            var login = tokenService.validateToken(token);

            // Primeiro tenta encontrar o usuário no repositório de administradores
            UserDetails user = adminRepo.findByEmail(login);
            // Se não encontrado, tenta buscar na empresa
            if (user == null) {
                user = empresaRepo.findByEmail(login);
            }

            // Se o usuário for encontrado, configura o contexto de segurança
            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (TokenInvalidoException ex) {
            // Resposta personalizada para token inválido
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Token Inválido");
            return;

        } catch (TokenExpiredException ex) {
            // Resposta personalizada para token expirado
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Token Expirado");
            return;
        }
    }

    filterChain.doFilter(request, response); // Continua com o filtro da requisição
}

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null)
            return null;
        return authHeader.replace("Bearer ", "");
    }

}

package eder.desenvolve.api.infra.security;
import eder.desenvolve.api.dominio.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Value("${api.cors.uri.permitida}")
    private String uri_permitida;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", uri_permitida);
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        var tokenJWT = recuperarToken(request);

        String path = request.getRequestURI();
        String method = request.getMethod();

        if ("/produto".equals(path) && "POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method) ) {
            if (tokenJWT != null) {
                var subjetct = tokenService.getSubjetct(tokenJWT);
                var usuario = usuarioRepository.findByLogin(subjetct);
                boolean isAdmin = tokenService.isAdm(tokenJWT);
                if (isAdmin) {
                    var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autentication);
                    filterChain.doFilter(request, response);
                    return;
                }
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        if(tokenJWT != null){
            var subjetct = tokenService.getSubjetct(tokenJWT);
            var usuario = usuarioRepository.findByLogin(subjetct);
            var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autentication);
        }
        filterChain.doFilter(request, response);
    }
        private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}

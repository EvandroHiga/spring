package br.com.fiap.spring.security;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static br.com.fiap.spring.utils.MessageConstants.*;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    public static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenHeaderAuthorization = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(tokenHeaderAuthorization != null && tokenHeaderAuthorization.startsWith(BEARER_PREFIX)){
            jwtToken = tokenHeaderAuthorization.replace(BEARER_PREFIX, "");

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException illegalArgumentException) {
                logger.info(ERRO_TOKEN_PARSE);
            } catch (ExpiredJwtException expiredJwtException) {
                logger.info(ERRO_TOKEN_EXPIRADO);
            }
        } else {
            logger.info(ERRO_TOKEN_JWT);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}

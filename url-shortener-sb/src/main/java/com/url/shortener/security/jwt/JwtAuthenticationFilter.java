package com.url.shortener.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtils jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Skip JWT processing for public endpoints
            String path = request.getServletPath();
            if (path.startsWith("/api/auth/") || 
                path.startsWith("/api/gmail/") ||
                path.matches("/[a-zA-Z0-9]{6,12}")) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = jwtTokenProvider.getJwtFromHeader(request);
            if(jwt != null && jwtTokenProvider.validateToken(jwt)){
                String email = jwtTokenProvider.getUsernameFromJwtToken(jwt);
                // System.out.println("Extracted email from JWT: " + email);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                if(userDetails != null){
                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // System.out.println("authentication: "+authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            System.out.println("token validation failed! in filter");
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

}

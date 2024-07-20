package com.stera.papertrade.Config.Security.Filter;

import com.mongodb.lang.NonNull;
import com.stera.papertrade.Config.jwtInterface;
import com.stera.papertrade.model.User;
import com.stera.papertrade.serviceinterface.UserProfileInterface;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private jwtInterface jwtService;

    @Autowired
    private UserProfileInterface userDetailsService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull  HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String JWT;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        JWT = authHeader.substring(7);
        userEmail = jwtService.extractUsername(JWT);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userDetails = this.userDetailsService.loadUserByUserEmail(userEmail);
            if (!userDetails.isPresent()){
                throw new UsernameNotFoundException("User Details Null!");
            }
            if (jwtService.isTokenValid(JWT,userDetails.get())){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails.get(),
                        null,
                        userDetails.get().getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

package com.yash.ytms_security_poc.security.jwt;

import com.yash.ytms_security_poc.exception.ApplicationException;
import com.yash.ytms_security_poc.security.userdetails.CustomUserDetails;
import com.yash.ytms_security_poc.security.userdetails.CustomUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        LOGGER.info(header);

        String userName = null;

        String token = null;

        if (header != null) {
            if (header.startsWith(TOKEN_PREFIX)) {
                token = header.substring(7);
                try {
                    userName = this.jwtTokenHelper.getUserNameFromToken(token);
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Unable to get Jwt Token !!",
                            new ApplicationException("Unable to get Jwt Token !!"));
                } catch (ExpiredJwtException e) {
                    LOGGER.error("Jwt Token has expired !!",
                            new ApplicationException("Jwt Token has expired !!"));
                } catch (MalformedJwtException e) {
                    LOGGER.error("Invalid Jwt !!",
                            new ApplicationException("Invalid Jwt !!"));
                }
            } else
                LOGGER.info("Jwt Token does not begin with Bearer.");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails userDetails = null;
            userDetails = this.userDetailsService.loadUserByUsername(userName);

            if (userDetails != null) {
                if (this.jwtTokenHelper.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else
                    LOGGER.error("Invalid Jwt token !!",
                            new ApplicationException("Invalid Jwt token !!"));
            } else
                LOGGER.error("Wrong User details provided !!",
                        new ApplicationException("Wrong User details provided !!"));
        } else
            LOGGER.error("Username is null or context is not null !!");

        filterChain.doFilter(request, response);
    }
}

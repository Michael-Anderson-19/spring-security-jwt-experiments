package io.github.MichaelAnderson19.ManualLogin.config.filter;

import io.github.MichaelAnderson19.ManualLogin.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * validates the jwt passed in with requests
 */

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService; //TODO make my own
    private final JwtUtils jwtUtils;

    public JwtAuthFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = parseJwt(request);

        if(jwt != null && jwtUtils.validateJwt(jwt)) {

            String email = jwtUtils.getEmailFromToken(jwt);

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication = createAuthenticationToken(userDetails);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            addPrincipleToSecurityContext(authentication);

        } else {
            logger.error("failed to authenticate user request jwt was either null or invalid");
        }

        filterChain.doFilter(request,response);
    }

    /**
     * get the authorization header from the request, if it exists
     * @param request
     * @return the JWT taken from the Authorization header of the request, with the "Bearer " prefix removed
     */
    private String parseJwt(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if((StringUtils.hasText(authHeader)) && (authHeader.startsWith("Bearer"))){
            //remove the 'Bearer ' from the jwt and return it
            return authHeader.substring(7);
        }
        return null;
    }

    /***
     * Add authentication object to the security context
     * @param authenticationToken the authentication object representing the authenticated user
     */
    private void addPrincipleToSecurityContext(UsernamePasswordAuthenticationToken authenticationToken) {
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * create a usernamePasswordAuthenticationToken based on userDetails obtained from the database
     * @param userDetails the username/email, password and authorities of the user
     * @return The UsernamePasswordAuthenticationToken which represents an authentication object
     */
    private UsernamePasswordAuthenticationToken createAuthenticationToken(UserDetails userDetails){
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        return authentication;




    }

}

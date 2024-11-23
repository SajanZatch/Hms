package com.hms.config;

import com.hms.Service.JWTService;
import com.hms.entity.AppUser;
import com.hms.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

//below code is a fix code
//OncePerRequestFilter is a abstract class with one incomplete method doFilterInternal
@Component
public class JWTFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    //if any incoming request have a token init then the request will automatically come to this request object of doFilter method
    @Override
    protected void doFilterInternal(
             HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain
            ) throws ServletException, IOException {

        //Here "Authorization" is the key that the token have in the header
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if(token!=null && token.startsWith("Bearer ")){
            String tokenVal =token.substring(8,token.length()-1);
            String username = jwtService.getUsername(tokenVal);
            System.out.println(username);
            Optional<AppUser> opUsername = appUserRepository.findByUsername(username);
            if(opUsername.isPresent()){
                //below code will get the user details like username and role
                AppUser appUser = opUsername.get();

                //it's a builtin Spring Security class
                UsernamePasswordAuthenticationToken auth = new

                        //there are 3 parameters like userDetails for whom loggedIn,credential,role For authorities
                        //in 3rd parameter we provided a collection of only having one object
                        UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));

                //this code will define which user wants which request
                auth.setDetails(new WebAuthenticationDetails(request));

                //this line of code will grant access to user for his request
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        //after jwtFilter runs it helps to run further the internal filter methods
        //it basically tells Spring Security that don't send all urls here /send only urls that have a token in it
        filterChain.doFilter(request,response);
    }
}
package com.example.sharath.BlogHub.Security;

import com.example.sharath.BlogHub.Exception.JwtIllegalArgsException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Autowired
    CustomUserDetailService userDetailService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken=request.getHeader("Authorization");

        System.out.println(requestToken);
        String userName=null;
        String token=null;

//        if(requestToken!=null && requestToken.startsWith("Bearer")){
//
//            token=requestToken.substring(7);
//
//            try{
//                username=jwtTokenHelper.getUsernameFromToken(token);
//            }
//            catch (IllegalArgumentException e){
//                throw new JwtIllegalArgsException(e.getMessage());
//            }
//            catch (ExpiredJwtException e){
//                throw new JwtIllegalArgsException("This Jwt token is Expired");
//            }
//            catch (MalformedJwtException e){
//                throw new JwtIllegalArgsException("This token is not valid,please don't try to hack me :/");
//            }
//        }
//        else{
//            System.out.println("Token Doesn't start with Bearer ");
//        }
//
//        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//            UserDetails userDetails=userDetailService.loadUserByUsername(username);
//
//            if(jwtTokenHelper.validateToken(token,userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
//                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }else {
//                throw new JwtIllegalArgsException("Invalid JWT Token");
//            }
//        }else {
//            throw new JwtIllegalArgsException("Username is null or SecurityContext is not null %s");
//        }
        if(null != requestToken && requestToken.startsWith("Bearer ")) {
            token = requestToken.substring(7);
            userName = jwtTokenHelper.getUsernameFromToken(token);
        }

        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = userDetailService.loadUserByUsername(userName);

            if(jwtTokenHelper.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

        filterChain.doFilter(request,response);
    }
}

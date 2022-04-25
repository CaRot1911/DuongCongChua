package com.vti.finalexam.configuration.jwt;

import com.vti.finalexam.service.IAccountService;
import com.vti.finalexam.untils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// request luon phai di qua
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private IAccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            try {
                String token = getTokenFormRequest(request);
                if(token != null && jwtUtil.validateJwtToken(token)){
                    String userName = jwtUtil.getUserNameByToken(token);
                    UserDetails userDetails = accountService.loadUserByUsername(userName);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                      userDetails,null,userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }catch (Exception e){
                throw e;
            }

            filterChain.doFilter(request,response);
    }

//    Ham lay token
    private String getTokenFormRequest(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.startsWith("Bearer")){
            return header.substring(7,header.length()).trim();
        }
        return null;
    }
}

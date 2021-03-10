package com.zhang.components.security.security;

import com.zhang.components.security.config.SecurityProperties;
import com.zhang.components.security.entity.SelfUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证token
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, content-type, accept");
//        response.setHeader("Access-Control-Allow-Credentials", "true"); //是否支持cookie跨域
        //请求头中的token信息
        String authHeader = request.getHeader(properties.getHeader());
        if (authHeader != null && authHeader.startsWith(properties.getTokenHead())) {
            String authToken = authHeader.substring(properties.getTokenHead().length());
            String username = tokenProvider.getUsername(authToken, properties.getBase64Secret());
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //验证token
                if (tokenProvider.validateToken(properties.getOnlineKey() + username)) {
                    //验证token和用户
                    SelfUserDetail userDetails = (SelfUserDetail) userDetailsService.loadUserByUsername(username);
                    if (tokenProvider.validateToken(authToken, properties.getBase64Secret(), userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        tokenProvider.delToken(username);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

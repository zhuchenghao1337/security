package xyz.zhuchenghao.jwtAuthentication.filter;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.zhuchenghao.jwtAuthentication.model.User;
import xyz.zhuchenghao.jwtAuthentication.utils.JwtUtil;
import xyz.zhuchenghao.jwtAuthentication.utils.UserContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 简单的白名单，登录这个接口直接放行
        if ("/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 已登录就放行
        String token = request.getHeader("token");
        Claims parse = JwtUtil.parse(token);
        if(parse != null) {
            UserContext.add(parse.getSubject());
            filterChain.doFilter(request, response);
            return;
        }
        // 走到这里就代表是其他接口，且没有登录
        // 设置响应数据类型为json（前后端分离）
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 设置响应内容，结束请求
        out.write("请先登录");
        out.flush();
        out.close();
    }
}
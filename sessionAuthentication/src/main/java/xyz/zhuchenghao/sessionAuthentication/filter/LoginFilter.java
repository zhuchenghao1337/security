package xyz.zhuchenghao.sessionAuthentication.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.zhuchenghao.sessionAuthentication.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录认证过滤器
 * 除了登录接口不需要判断是否登录，其他接口都要先判断是否登录
 */
@Component
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 白名单
        if ("/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        // 需要进行登录认证
        User user = (User) request.getSession().getAttribute("user");
        // 认证成功放行
        if (user != null) {
            filterChain.doFilter(request, response);
            return;
        }
        // 认证失败
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("请先登录");
        out.flush();
        out.close();
    }
}

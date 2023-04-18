package xyz.zhuchenghao.sessionAuthentication.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.zhuchenghao.sessionAuthentication.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 上下文对象
 * SpringMVC提供
 * 方便service层获取当前用户信息
 */
public class RequestContext {
    public static HttpServletRequest getCurrentRequest() {
        // 通过`RequestContextHolder`获取当前request请求对象
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    public static User getCurrentUser() {
        // 通过request对象获取session对象，再获取当前用户对象
        return (User)getCurrentRequest().getSession().getAttribute("user");
    }
}
package xyz.zhuchenghao.jwtAuthentication.controller;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhuchenghao.jwtAuthentication.model.User;
import xyz.zhuchenghao.jwtAuthentication.utils.JwtUtil;
import xyz.zhuchenghao.jwtAuthentication.utils.UserContext;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {
    /**
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping("login")
    public String login(@RequestBody User user) {
        if ("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            // 认证成功
            // 生成jwt 并响应
            String token = JwtUtil.generate(user.getUsername());
            return token;
        }
        return "账号或密码错误";
    }
    /**
     * 获取个人信息
     */
    @GetMapping("getUserInfo")
    public String getUserInfo() {
        // 使用上下文对象
        String username = UserContext.getCurrentUserName();
        return username;
    }

    // 这里未实现退出
}

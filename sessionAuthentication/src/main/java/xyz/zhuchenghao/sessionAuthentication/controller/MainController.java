package xyz.zhuchenghao.sessionAuthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhuchenghao.sessionAuthentication.model.User;
import xyz.zhuchenghao.sessionAuthentication.utils.RequestContext;

import javax.servlet.http.HttpSession;

@RestController
public class MainController {
    /**
     * 登录接口
     * @param user
     * @param session
     * @return
     */
    @PostMapping("login")
    public String login(@RequestBody User user, HttpSession session) {
        // 校验账号和密码
        if("admin".equals(user.getUsername()) && "123456".equals(user.getPassword())) {
            // 认证成功
            // 将信息存入session
            session.setAttribute("user", user);
            return "登录成功";
        } else {
            return "账号或者密码错误";
        }
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @PostMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "退出成功";
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("getUserInfo")
    public String getUserInfo() {
        // 从上下文对象获取信息
        User user = RequestContext.getCurrentUser();
        return user.toString();
    }

}

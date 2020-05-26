package hcc.pete.smartivr.controller;

import hcc.pete.smartivr.annotation.UserLoginToken;
import hcc.pete.smartivr.pojo.User;
import hcc.pete.smartivr.service.UserService;
import hcc.pete.smartivr.utils.CommonResult;
import hcc.pete.smartivr.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pete Chen
 * @date 2020/5/25
 */


@RestController
@RequestMapping(value = "user")
public class LoginController {

    @Autowired
    private CommonResult result;
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public CommonResult login(String username, String password) {
        User user = userService.findByUsername(username);
        if (user == null) {
            result.fail("登录失败用户不存在", null);
        } else {
            if (!user.getPassword().equals(password)) {
                result.fail("登录失败，密码错误", null);
            } else {
                String token = JwtUtil.getToken(user);
                result.success("登录成功", token);
            }
        }
        return result;
    }

    @UserLoginToken
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }


}

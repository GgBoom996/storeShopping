package cn.itsource.controller;


import cn.itsource.User;
import cn.itsource.util.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public AjaxResult index(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if ("root".equals(username) && "123456".equals(password)) {
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("登录成功").setObj(user);
        }
        return AjaxResult.getAjaxResult().setSuccess(false).setMessage("登录失败");
    }
}

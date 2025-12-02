package com.example.smbms.controller;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.mapper.SmbmsUserMapper;
import com.example.smbms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class LoginController {
     @Autowired
    private UserService userService;

    /**
     * 显示登录页面
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * 处理登录请求
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("userCode") String userCode,
            @RequestParam("userPassword") String userPassword,
            HttpSession session,
            Model model) {

        // 验证用户名密码
        Smbms_User user = userService.login(userCode, userPassword);

        if (user != null) {
            // 登录成功，保存用户信息到session
            session.setAttribute("currentUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userRole", user.getUserRole());

            // 重定向到首页
            return "redirect:/";
        } else {
            // 登录失败
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    /**
     * 显示注册页面
     */
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    /**
     * 处理注册请求
     */
    @PostMapping("/register")
    public String register(
            @RequestParam("userCode") String userCode,
            @RequestParam("userName") String userName,
            @RequestParam("userPassword") String userPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam(value = "gender", defaultValue = "3") Integer gender,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "address", required = false) String address,
            Model model) {

        // 验证密码是否一致
        if (!userPassword.equals(confirmPassword)) {
            model.addAttribute("error", "两次输入的密码不一致！");
            return "register";
        }

        // 检查用户编码是否已存在
        Smbms_User existingUser = userService.getUserByCode(userCode);
        if (existingUser != null) {
            model.addAttribute("error", "用户编码已存在，请使用其他编码！");
            return "register";
        }

        // 创建新用户
        Smbms_User newUser = new Smbms_User();
        newUser.setUserCode(userCode);
        newUser.setUserName(userName);
        newUser.setUserPassword(userPassword);
        newUser.setGender(gender);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setUserRole(2L); // 默认注册为普通用户
        newUser.setCreatedBy(0L); // 系统创建
        newUser.setCreationDate(new Date());

        // 保存用户
        boolean success = userService.addUser(newUser);

        if (success) {
            model.addAttribute("message", "注册成功！请登录。");
            return "login";
        } else {
            model.addAttribute("error", "注册失败，请重试！");
            return "register";
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 清除session
        session.invalidate();
        return "redirect:/login";
    }
}

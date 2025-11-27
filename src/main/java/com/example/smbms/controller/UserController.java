package com.example.smbms.controller;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<Smbms_User> getUserList() {
        System.out.println("=== 接收到 /api/user/list 请求 ===");
        List<Smbms_User> users = userService.getAllUsers();
        System.out.println("查询到的用户数量: " + (users != null ? users.size() : "null"));
        if (users != null && !users.isEmpty()) {
            for (Smbms_User user : users) {
                System.out.println("用户: " + user.getUserName() + " - " + user.getUserCode());
            }
        }
        return users;
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("=== 测试接口被调用 ===");
        return "Spring Boot 项目运行成功！时间: " + new java.util.Date();
    }

    @GetMapping("/debug")
    public String debug() {
        System.out.println("=== 调试接口被调用 ===");
        try {
            List<Smbms_User> users = userService.getAllUsers();
            return "数据库连接正常，用户数量: " + (users != null ? users.size() : "null");
        } catch (Exception e) {
            return "数据库错误: " + e.getMessage();
        }
    }
}
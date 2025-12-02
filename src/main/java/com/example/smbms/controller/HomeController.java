package com.example.smbms.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 首页
     */
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }
        return "index";
    }

    /**
     * 关于页面
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    /**
     * 帮助页面
     */
    @GetMapping("/help")
    public String help() {
        return "help";
    }

    /**
     * 联系页面
     */
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
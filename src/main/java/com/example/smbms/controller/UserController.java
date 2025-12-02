package com.example.smbms.controller;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 检查是否登录
     */
    private boolean checkLogin(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    /**
     * 检查是否是管理员
     */
    private boolean isAdmin(HttpSession session) {
        Long userRole = (Long) session.getAttribute("userRole");
        return userRole != null && userRole == 1;
    }

    /**
     * 显示用户列表页面（需要登录）
     */
    @GetMapping("/list")
    public String listUsers(HttpSession session, Model model) {
        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        List<Smbms_User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        // 添加当前用户信息
        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);

        return "user/list";
    }

    /**
     * 搜索用户（需要登录）
     */
    @GetMapping("/search")
    public String searchUsers(
            @RequestParam("keyword") String keyword,
            HttpSession session,
            Model model) {

        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        List<Smbms_User> users = userService.searchUsers(keyword);
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);

        // 添加当前用户信息
        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);

        return "user/list";
    }

    /**
     * 显示添加用户页面（需要管理员权限）
     */
    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        // 检查是否是管理员
        if (!isAdmin(session)) {
            model.addAttribute("error", "需要管理员权限！");
            return "redirect:/user/list";
        }

        model.addAttribute("user", new Smbms_User());

        // 添加当前用户信息
        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);

        return "user/add";
    }

    /**
     * 处理添加用户请求（需要管理员权限）
     */
    @PostMapping("/add")
    public String addUser(
            @RequestParam("userCode") String userCode,
            @RequestParam("userName") String userName,
            @RequestParam("userPassword") String userPassword,
            @RequestParam(value = "gender", defaultValue = "3") Integer gender,
            @RequestParam(value = "birthday", required = false) String birthdayStr,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "userRole", defaultValue = "2") Long userRole,
            HttpSession session,
            Model model) {

        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        // 检查是否是管理员
        if (!isAdmin(session)) {
            model.addAttribute("error", "需要管理员权限！");
            return "redirect:/user/list";
        }

        // 获取当前登录用户作为创建者
        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");

        Smbms_User user = new Smbms_User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(gender);

        // 处理生日
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            try {
                Date birthday = dateFormat.parse(birthdayStr);
                user.setBirthday(birthday);
            } catch (ParseException e) {
                // 日期格式错误，可以为空
            }
        }

        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(userRole);
        user.setCreatedBy(currentUser.getId());
        user.setCreationDate(new Date());

        boolean success = userService.addUser(user);

        if (success) {
            model.addAttribute("message", "用户添加成功！");
            model.addAttribute("user", new Smbms_User()); // 清空表单
        } else {
            model.addAttribute("message", "用户添加失败，用户编码可能已存在！");
            model.addAttribute("user", user); // 保留已填信息
        }

        model.addAttribute("currentUser", currentUser);
        return "user/add";
    }

    /**
     * 显示编辑用户页面（需要管理员权限或编辑自己的信息）
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model) {

        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        Smbms_User user = userService.getUserById(id);

        if (user == null) {
            model.addAttribute("error", "用户不存在！");
            return "redirect:/user/list";
        }

        // 检查权限：管理员可以编辑任何人，普通用户只能编辑自己
        if (!isAdmin(session) && !currentUser.getId().equals(user.getId())) {
            model.addAttribute("error", "没有权限编辑其他用户！");
            return "redirect:/user/list";
        }

        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        return "user/edit";
    }

    /**
     * 处理编辑用户请求（需要管理员权限或编辑自己的信息）
     */
    @PostMapping("/edit")
    public String updateUser(
            @RequestParam("id") Long id,
            @RequestParam("userCode") String userCode,
            @RequestParam("userName") String userName,
            @RequestParam(value = "userPassword", required = false) String userPassword,
            @RequestParam(value = "gender", defaultValue = "3") Integer gender,
            @RequestParam(value = "birthday", required = false) String birthdayStr,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "userRole", required = false) Long userRole,
            HttpSession session,
            Model model) {

        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        Smbms_User user = userService.getUserById(id);

        if (user == null) {
            model.addAttribute("error", "用户不存在！");
            return "redirect:/user/list";
        }

        // 检查权限：管理员可以编辑任何人，普通用户只能编辑自己
        if (!isAdmin(session) && !currentUser.getId().equals(user.getId())) {
            model.addAttribute("error", "没有权限编辑其他用户！");
            return "redirect:/user/list";
        }

        // 更新用户信息
        user.setUserCode(userCode);
        user.setUserName(userName);

        // 如果密码不为空则更新密码
        if (userPassword != null && !userPassword.trim().isEmpty()) {
            user.setUserPassword(userPassword);
        }

        user.setGender(gender);

        // 处理生日
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            try {
                Date birthday = dateFormat.parse(birthdayStr);
                user.setBirthday(birthday);
            } catch (ParseException e) {
                // 日期格式错误，保持原值
            }
        }

        user.setPhone(phone);
        user.setAddress(address);

        // 只有管理员可以修改用户角色
        if (isAdmin(session) && userRole != null) {
            user.setUserRole(userRole);
        }

        user.setModifyBy(currentUser.getId());
        user.setModifyDate(new Date());

        boolean success = userService.updateUser(user);

        if (success) {
            model.addAttribute("message", "用户更新成功！");
        } else {
            model.addAttribute("message", "用户更新失败！");
        }

        model.addAttribute("user", user);
        model.addAttribute("currentUser", currentUser);
        return "user/edit";
    }

    /**
     * 删除用户（需要管理员权限）
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model) {

        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        // 检查是否是管理员
        if (!isAdmin(session)) {
            model.addAttribute("error", "需要管理员权限！");
            return "redirect:/user/list";
        }

        // 不能删除自己
        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        if (currentUser.getId().equals(id)) {
            model.addAttribute("error", "不能删除自己！");
            return "redirect:/user/list";
        }

        boolean success = userService.deleteUser(id);

        if (success) {
            model.addAttribute("message", "用户删除成功！");
        } else {
            model.addAttribute("message", "用户删除失败！");
        }

        // 返回用户列表
        List<Smbms_User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        return "user/list";
    }

    /**
     * 显示用户详情（需要登录）
     */
    @GetMapping("/view/{id}")
    public String viewUser(
            @PathVariable("id") Long id,
            HttpSession session,
            Model model) {

        // 检查是否登录
        if (!checkLogin(session)) {
            return "redirect:/login";
        }

        Smbms_User user = userService.getUserById(id);
        if (user == null) {
            model.addAttribute("error", "用户不存在！");
            return "redirect:/user/list";
        }

        model.addAttribute("user", user);

        // 添加当前用户信息
        Smbms_User currentUser = (Smbms_User) session.getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);

        return "user/view";
    }
}
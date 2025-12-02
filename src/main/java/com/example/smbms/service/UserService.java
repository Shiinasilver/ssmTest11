package com.example.smbms.service;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.mapper.SmbmsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    /**
     * 获取所有用户
     */
    List<Smbms_User> getAllUsers();

    /**
     * 根据ID获取用户
     */
    Smbms_User getUserById(Long id);

    /**
     * 根据用户编码获取用户
     */
    Smbms_User getUserByCode(String userCode);

    /**
     * 添加用户
     */
    boolean addUser(Smbms_User user);

    /**
     * 更新用户
     */
    boolean updateUser(Smbms_User user);

    /**
     * 删除用户
     */
    boolean deleteUser(Long id);

    /**
     * 搜索用户
     */
    List<Smbms_User> searchUsers(String keyword);

    /**
     * 用户登录验证
     */
    Smbms_User login(String userCode, String userPassword);
}

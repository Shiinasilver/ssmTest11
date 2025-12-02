package com.example.smbms.service.impl;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.mapper.SmbmsUserMapper;
import com.example.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional//事务注解用于声明式事务管理，确保数据库操作符合ACID特性（原子性、一致性、隔离性、持久性）
public class UserServiceImpl implements UserService {
    @Autowired
    private SmbmsUserMapper userMapper;

    @Override
        public List<Smbms_User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public Smbms_User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Smbms_User getUserByCode(String userCode) {
        return userMapper.getUserByCode(userCode);
    }

    @Override
    public boolean addUser(Smbms_User user) {
        try {
            return userMapper.addUser(user) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUser(Smbms_User user) {
        try {
            return userMapper.updateUser(user) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            return userMapper.deleteUser(id) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Smbms_User> searchUsers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllUsers();
        }
        return userMapper.searchUsers("%" + keyword + "%");
    }

    @Override
    public Smbms_User login(String userCode, String userPassword) {
        return userMapper.login(userCode, userPassword);
    }


}
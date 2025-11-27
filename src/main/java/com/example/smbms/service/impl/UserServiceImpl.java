package com.example.smbms.service.impl;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.mapper.SmbmsUserMapper;
import com.example.smbms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//事务注解用于声明式事务管理，确保数据库操作符合ACID特性（原子性、一致性、隔离性、持久性）
public class UserServiceImpl implements UserService {
    //依赖注入
    @Autowired
    private SmbmsUserMapper userMapper;

    @Override
    public List<Smbms_User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public Smbms_User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Smbms_User login(String userCode, String userPassword) {
        return userMapper.login(userCode, userPassword);
    }
}
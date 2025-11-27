package com.example.smbms.service;

import com.example.smbms.entity.Smbms_User;
import com.example.smbms.mapper.SmbmsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    List<Smbms_User> getAllUsers();

    Smbms_User getUserById(Long id);

    Smbms_User login(String userCode, String userPassword);
}
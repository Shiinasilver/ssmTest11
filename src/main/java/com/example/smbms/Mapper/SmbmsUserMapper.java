package com.example.smbms.Mapper;

import com.example.smbms.Pojo.smbms_user;

import java.util.List;

public interface SmbmsUserMapper {
    // 添加用户
    int insertUser(smbms_user user);

    // 根据ID删除用户
    int deleteUserById(Long id);

    // 更新用户信息
    int updateUser(smbms_user user);

    // 根据ID查询用户
    smbms_user selectUserById(Long id);

    // 查询所有用户
    List<smbms_user> selectAllUsers();

    // 根据用户编码查询用户
    smbms_user selectUserByUserCode(String userCode);

    // 根据用户名模糊查询
    List<smbms_user> selectUsersByUserName(String userName);

    // 根据用户角色查询
    List<smbms_user> selectUsersByRole(Long userRole);

    // 用户登录验证
    smbms_user login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

    // 分页查询用户
    List<smbms_user> selectUsersByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    // 查询用户总数
    int selectUserCount();
}

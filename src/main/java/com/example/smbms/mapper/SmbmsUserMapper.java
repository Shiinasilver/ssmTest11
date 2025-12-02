package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SmbmsUserMapper {
    /**
     * 获取所有用户
     */
    @Select("SELECT * FROM smbms_user ORDER BY creationDate DESC")
    List<Smbms_User> getAllUsers();

    /**
     * 根据ID获取用户
     */
    @Select("SELECT * FROM smbms_user WHERE id = #{id}")
    Smbms_User getUserById(@Param("id") Long id);

    /**
     * 根据用户编码获取用户
     */
    @Select("SELECT * FROM smbms_user WHERE userCode = #{userCode}")
    Smbms_User getUserByCode(@Param("userCode") String userCode);

    /**
     * 添加用户
     */
    @Insert("INSERT INTO smbms_user(userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate) " +
            "VALUES(#{userCode}, #{userName}, #{userPassword}, #{gender}, #{birthday}, #{phone}, #{address}, #{userRole}, #{createdBy}, #{creationDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addUser(Smbms_User user);

    /**
     * 更新用户
     */
    @Update("UPDATE smbms_user SET " +
            "userCode = #{userCode}, " +
            "userName = #{userName}, " +
            "userPassword = #{userPassword}, " +
            "gender = #{gender}, " +
            "birthday = #{birthday}, " +
            "phone = #{phone}, " +
            "address = #{address}, " +
            "userRole = #{userRole}, " +
            "modifyBy = #{modifyBy}, " +
            "modifyDate = #{modifyDate} " +
            "WHERE id = #{id}")
    int updateUser(Smbms_User user);

    /**
     * 删除用户
     */
    @Delete("DELETE FROM smbms_user WHERE id = #{id}")
    int deleteUser(@Param("id") Long id);

    /**
     * 搜索用户
     */
    @Select("SELECT * FROM smbms_user WHERE " +
            "userCode LIKE #{keyword} OR " +
            "userName LIKE #{keyword} OR " +
            "phone LIKE #{keyword} " +
            "ORDER BY creationDate DESC")
    List<Smbms_User> searchUsers(@Param("keyword") String keyword);

    /**
     * 用户登录
     */
    @Select("SELECT * FROM smbms_user WHERE userCode = #{userCode} AND userPassword = #{userPassword}")
    Smbms_User login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
}
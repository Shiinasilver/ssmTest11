package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SmbmsUserMapper {
    @Select("SELECT * FROM smbms_user")
    List<Smbms_User> selectAll();

    @Select("SELECT * FROM smbms_user WHERE id = #{id}")
    Smbms_User selectById(Long id);

    // 使用注解方式，避免 XML 配置问题
    @Select("SELECT * FROM smbms_user WHERE userCode = #{userCode} AND userPassword = #{userPassword}")
    Smbms_User login(String userCode, String userPassword);
}
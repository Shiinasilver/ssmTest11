package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface SmbmsUserMapper {
    //以id查找用户
    Smbms_User selectById(Long id);
    //以用户代码查找
    Smbms_User selectByUserCode(String userCode);
    //查找所有
    List<Smbms_User> selectAll();
    //模糊查询
    List<Smbms_User> selectByCondition(@Param("userName") String userName,
                                       @Param("userRole") Long userRole);
    //增加
    int insert(Smbms_User user);
    //修改
    int update(Smbms_User user);
    //删除
    int deleteById(Long id);
    //登录
    Smbms_User login(@Param("userCode") String userCode,
                     @Param("userPassword") String userPassword);
}

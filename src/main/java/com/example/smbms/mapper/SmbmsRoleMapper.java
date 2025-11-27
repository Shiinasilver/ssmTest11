package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmbmsRoleMapper {
    //查找
    Smbms_Role selectById(Long id);
    //查找全部
    List<Smbms_Role> selectAll();
    //增加
    int insert(Smbms_Role role);
    //修改
    int update(Smbms_Role role);
    //删除
    int deleteById(Long id);
}

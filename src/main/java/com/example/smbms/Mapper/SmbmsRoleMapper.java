package com.example.smbms.Mapper;

import com.example.smbms.Pojo.smbms_role;
import java.util.List;

public interface SmbmsRoleMapper {
    // 插入
    int insertRole(smbms_role role);

    // 删除
    int deleteRoleById(Long id);

    // 更新
    int updateRole(smbms_role role);

    // 根据ID查询
    smbms_role selectRoleById(Long id);

    // 查询全部
    List<smbms_role> selectAllRoles();
}

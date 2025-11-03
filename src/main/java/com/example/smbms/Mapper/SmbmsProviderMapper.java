package com.example.smbms.Mapper;

import com.example.smbms.Pojo.smbms_addressDao;
import java.util.List;

public interface SmbmsProviderMapper {
    // 插入
    int insertProvider(smbms_addressDao provider);

    // 删除
    int deleteProviderById(Long id);

    // 更新
    int updateProvider(smbms_addressDao provider);

    // 查询单个
    smbms_addressDao selectProviderById(Long id);

    // 查询全部
    List<smbms_addressDao> selectAllProviders();
}

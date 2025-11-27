package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_AddressDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmbmsProviderMapper {
    // 查询单个
    Smbms_AddressDao selectProviderById(Long id);
    // 查询全部
    List<Smbms_AddressDao> selectAllProviders();
    // 插入
    int insertProvider(Smbms_AddressDao provider);
    // 删除
    int deleteProviderById(Long id);
    // 更新
    int updateProvider(Smbms_AddressDao provider);
}

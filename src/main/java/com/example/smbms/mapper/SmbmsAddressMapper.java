package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_AddressDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmbmsAddressMapper {
    // 根据ID查询
    Smbms_AddressDao selectAddressById(Long id);
    // 查询所有
    List<Smbms_AddressDao> selectAllAddresses();
    // 插入
    int insertAddress(Smbms_AddressDao address);
    // 根据ID删除
    int deleteAddressById(Long id);
    // 更新
    int updateAddress(Smbms_AddressDao address);
}

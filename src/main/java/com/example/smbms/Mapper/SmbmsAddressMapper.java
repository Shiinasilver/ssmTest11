package com.example.smbms.Mapper;

import com.example.smbms.Pojo.smbms_addressDao;
import java.util.List;

public interface SmbmsAddressMapper {
    // 插入
    int insertAddress(smbms_addressDao address);

    // 根据ID删除
    int deleteAddressById(Long id);

    // 更新
    int updateAddress(smbms_addressDao address);

    // 根据ID查询
    smbms_addressDao selectAddressById(Long id);

    // 查询所有
    List<smbms_addressDao> selectAllAddresses();
}

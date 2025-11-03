package com.example.smbms.Mapper;


import com.example.smbms.Pojo.smbms_billDao;
import java.util.List;


public interface SmbmsBillMapper {
    // 插入
    int insertBill(smbms_billDao bill);

    // 删除
    int deleteBillById(Long id);

    // 更新
    int updateBill(smbms_billDao bill);

    // 根据ID查询
    smbms_billDao selectBillById(Long id);

    // 查询全部
    List<smbms_billDao> selectAllBills();
}

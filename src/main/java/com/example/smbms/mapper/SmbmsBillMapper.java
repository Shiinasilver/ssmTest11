package com.example.smbms.mapper;

import com.example.smbms.entity.Smbms_BillDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmbmsBillMapper {
    // 根据ID查询
    Smbms_BillDao selectBillById(Long id);
    // 查询全部
    List<Smbms_BillDao> selectAllBills();
        // 插入
    int insertBill(Smbms_BillDao bill);
    // 删除
    int deleteBillById(Long id);
    // 更新
    int updateBill(Smbms_BillDao bill);
}

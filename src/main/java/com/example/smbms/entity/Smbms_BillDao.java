package com.example.smbms.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Smbms_BillDao {
    private Long id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private BigDecimal productCount;
    private BigDecimal totalPrice;
    private Integer isPayment;
    private Long providerId;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;

}
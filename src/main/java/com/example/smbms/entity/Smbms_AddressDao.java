package com.example.smbms.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Smbms_AddressDao {
    private Long id;
    private String contact;
    private String addressDesc;
    private String postCode;
    private String tel;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;
    private Long userId;

}
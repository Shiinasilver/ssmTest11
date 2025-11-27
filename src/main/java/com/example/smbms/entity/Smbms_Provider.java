package com.example.smbms.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Smbms_Provider {
    private Long id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String proPhone;
    private String proAddress;
    private String proFax;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;

}


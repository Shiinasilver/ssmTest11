package com.example.smbms.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Smbms_Role {
    private Long id;
    private String roleCode;
    private String roleName;
    private Long createdBy;
    private Date creationDate;
    private Long modifyBy;
    private Date modifyDate;

}



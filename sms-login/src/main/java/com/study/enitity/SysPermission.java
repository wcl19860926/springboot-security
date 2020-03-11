package com.study.enitity;

import lombok.Data;

@Data
public class SysPermission {

    private Long id;
    private String code;
    private String name;
    private String url;
    private String description;
}

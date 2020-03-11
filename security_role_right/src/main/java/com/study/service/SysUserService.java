package com.study.service;

import com.study.enitity.SysUser;

public interface SysUserService {


    SysUser  findSysUserByCode(String userCode);
}

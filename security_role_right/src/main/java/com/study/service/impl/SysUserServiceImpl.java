package com.study.service.impl;

import com.study.enitity.SysUser;
import com.study.mapper.SysUserMapper;
import com.study.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findSysUserByCode(String userCode) {

        return sysUserMapper.findUserByUserName(userCode);
    }
}

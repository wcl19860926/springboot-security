package com.study.mapper;

import com.study.enitity.SysUser;
import org.apache.ibatis.annotations.*;


@Mapper
public interface SysUserMapper {


    /**
     * 根据用户Code查询用户信息
     *
     * @param userCode
     * @return
     */
    @Select("select * from  sys_user where userCode = #{userCode}")
    SysUser findUserByUserName(@Param("userCode") String userCode);

}

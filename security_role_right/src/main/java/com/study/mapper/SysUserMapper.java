package com.study.mapper;

import com.study.enitity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface SysUserMapper {


    /**
     * 根据用户Code查询用户信息
     * @param userCode
     * @return
     */
    @Select("select * from  sys_user where userCode = #{userCode}")
    @ResultType(SysUser.class)
    @ResultMap({"id","username","userCode","password","salt"})
    SysUser findUserByUserName(@Param("userCode") String userCode);

}

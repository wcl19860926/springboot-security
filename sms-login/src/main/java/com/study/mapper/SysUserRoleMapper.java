package com.study.mapper;

import com.study.enitity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {

    @Select("select  * from   sys_user_role  where userId =#{userId} ")
    List<SysUserRole> findUserRoleByUserId(@Param("userId") Long userId);

}

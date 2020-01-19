package com.study.mapper;

import com.study.enitity.SysPermission;
import com.study.enitity.SysPermissionRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper {


    /**
     * 根据多个角色Ids 批量查询权限信息
     * @param ids
     * @return
     */
    @Select({
            "<script>",
            "SELECT  *  ",
            "from sys_permission",
            "where roleId in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    @ResultType(SysPermissionRole.class)
    List<SysPermissionRole> findPermissionByRoleIds(@Param("ids") List<Long> ids);
}

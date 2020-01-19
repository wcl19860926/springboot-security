package com.study.mapper;

import com.study.enitity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysPermissionMapper {

    /**
     * 根据角色Id查询权限信息
     * @param roleId
     * @return
     */
    @Select("SELECT * FROM sys_permission WHERE roleId=#{roleId}")
    List<SysPermission> findPermissionByRoleId(Integer roleId);


    /**
     * 根据多个角色Ids 批量查询权限信息
     * @param ids
     * @return
     */
    @Select({
            "<script>",
            "SELECT  *  ",
            "from sys_permission",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<SysPermission> findPermissionByRoleIds(@Param("ids") List<Long> ids);

}

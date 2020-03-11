package com.study.mapper;

import com.study.enitity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(@Param("id") Long id);



    @Select( {"<script>",
            "SELECT  *  ",
            "from sys_role",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"})
    List<SysRole> selectByName(@Param("ids") List<Long> ids);

}

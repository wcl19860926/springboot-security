package com.study.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {


    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    @Select("select * from  users where username = #{userName}")
    String findUserByUserName(@Param("userName") String userName);

}

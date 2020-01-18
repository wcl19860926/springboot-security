package com.study.mapper;

import com.study.entity.User;
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
    User findUserByUserName(@Param("userName") String userName);

}

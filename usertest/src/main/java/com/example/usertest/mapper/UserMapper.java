package com.example.usertest.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author NJQ-PC
 */
@Mapper
public interface UserMapper {

    @Select("select * from userlist where username=#{username} and password=#{password}")
    Boolean login(@Param("username") String username,@Param("password") String password);

    @Insert("insert into userlist value(default,#{username},#{password})")
    Boolean register(@Param("username") String username,@Param("password") String password);
    
}

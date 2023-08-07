package com.example.mybaitsspringboot.mapper;

import com.example.mybaitsspringboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper //用来连接数据库的
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findOneByName(@Param("username") String username);

    @Select("select * from user")
    List<User> findAll();

    List<User> findAll2();

    @Select("select * from  user where id > #{id}")
    List<User> findById(@Param("id") String id);

    //
    @Delete("delete from user where id = #{id}")
    Boolean deleteId(@Param("id") String id);

    Boolean updateId(@Param("id") String id);


    @Delete("delete from user where username = #{username}")
    Boolean deleteId2(@Param("username") String username);
}

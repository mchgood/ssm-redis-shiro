package com.zhidisoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhidisoft.system.entity.User;

public interface UserDao {
	
	int deleteByPrimaryKey(Integer funcId);

	int insert(User record);

	User selectByPrimaryKey(Integer funcId);

	List<User> selectAll();

	int updateByPrimaryKey(User record);

	User findByName(@Param("name") String userName);

	Integer count(@Param("userName")String userName,@Param("status")Integer status);

	List<User> findByPage(@Param("offset")Integer offset, @Param("pageSize")Integer pageSize,@Param("userName")String userName,@Param("status")Integer status);

	int deleteByIds(@Param("ids")String[] ids);
}
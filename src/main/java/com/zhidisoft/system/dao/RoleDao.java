package com.zhidisoft.system.dao;

import java.util.List;

import com.zhidisoft.system.entity.Role;

public interface RoleDao{

	int deleteByPrimaryKey(Integer funcId);

	int insert(Role record);

	Role selectByPrimaryKey(Integer funcId);

	List<Role> selectAll();

	int updateByPrimaryKey(Role record);	
	
    
    List<Role> findByUserId(Integer userId);
}
package com.zhidisoft.system.service;

import java.util.List;

import com.zhidisoft.system.entity.Role;

public interface RoleServiceIf {

	List<Role> selectAll();

	List<Role> findByUserId(Integer ids);

	int assignRole(String userIds, String roleIds);

}

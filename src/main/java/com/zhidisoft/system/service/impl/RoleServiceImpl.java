package com.zhidisoft.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.system.dao.RoleDao;
import com.zhidisoft.system.dao.UserRoleDao;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.entity.UserRole;
import com.zhidisoft.system.service.RoleServiceIf;

@Service
public class RoleServiceImpl implements RoleServiceIf {

	@Resource
	RoleDao roleDao;
	
	@Resource
	UserRoleDao userRoleDao;
	
	@Override
	public List<Role> selectAll() {
		return roleDao.selectAll();
	}

	@Override
	public List<Role> findByUserId(Integer ids) {		
		return roleDao.findByUserId(ids);
	}

	@Override
	public int assignRole(String userIds, String roleIds) {
		
		// 根据用户id，删除相关角色
		String[] userIdsArr = userIds.split(",");
		int rows = userRoleDao.deleteByUserIds(userIdsArr);
		
		// 为用户分配新的角色
		List<UserRole> records = new ArrayList();
		
		String[] roleIdsArr = roleIds.split(",");
		UserRole userRole = null;
		for(String userId:userIdsArr){
			for(String roleId:roleIdsArr){
				userRole = new UserRole(Integer.valueOf(userId) , Integer.valueOf(roleId));
				records.add(userRole);
			}
		}
		
		rows = userRoleDao.insertList(records);
		
		return rows;
	}

}

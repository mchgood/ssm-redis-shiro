package com.zhidisoft.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.system.service.UserServiceIf;

@Service
public class UserServiceImpl  implements UserServiceIf {
	
	@Resource
	UserDao userDao;

	
	@Override
	public PageBean findByPage(Integer page, Integer rows,String userName,String status) {
		PageBean  pageBean = new PageBean();
		pageBean.setPageNumber(page);
		pageBean.setPageSize(rows);
		
		int state = 1;
//		if(status.equals("可用")){
//			state = 1;
//		}
		// 查询总记录数
		Integer total = userDao.count(userName,state);
		// 查询分页数据		
		List<User> userList = userDao.findByPage(pageBean.getOffset(),pageBean.getPageSize(),userName,state);
		
		
		pageBean.setTotal(total);
		pageBean.setRows(userList);
		
		return pageBean;
	}

	@Override
	public int deleteByIds(String[] ids) {
		int rows = userDao.deleteByIds(ids);
		return rows;
	}

}

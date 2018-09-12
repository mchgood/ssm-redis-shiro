package com.zhidisoft.system.service;

import com.zhidisoft.result.PageBean;
import com.zhidisoft.system.entity.User;

public interface UserServiceIf {

	public PageBean findByPage(Integer page, Integer rows,String userName,String status);

	public int deleteByIds(String[] ids);
}

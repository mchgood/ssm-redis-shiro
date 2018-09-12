package com.zhidisoft.system.service;

import java.util.List;

import com.zhidisoft.result.TreeNode;
import com.zhidisoft.system.entity.Function;

public interface FunctionServiceIf {

	public List<Function> findByUserId(Integer userId);

	public List<TreeNode> findByUserId_menu(Integer userId,Integer funcId);
	
}

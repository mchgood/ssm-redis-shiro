package com.zhidisoft.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.result.TreeNode;
import com.zhidisoft.system.dao.FunctionDao;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.service.FunctionServiceIf;

@Service
public class FunctionServiceImpl implements FunctionServiceIf {

	@Resource
	FunctionDao funcDao;
	
	@Override
	public List<Function> findByUserId(Integer userId) {
		List<Function> funcList = funcDao.findByUserId(userId);
		return funcList;
	}

	@Override
	public List<TreeNode> findByUserId_menu(Integer userId,Integer funcId) {	
		 List<Function> funcList = funcDao.findByUserId_menu(userId,funcId);
		 List<TreeNode> treeNodeList = new ArrayList();
		 
		 for(Function func:funcList){			
			 TreeNode tn = new  TreeNode();
			 
			 tn.setId(func.getFuncId());
			 tn.setText(func.getFuncName());
			 // 父菜单
			 if(func.getParentId()==null || func.getFuncUrl()==null){
				 tn.setState("closed");
			 }
			 if(func.getFuncUrl()!=null){
				 tn.getAttributes().put("url", func.getFuncUrl());
			 }
			
			 treeNodeList.add(tn);			 
		 }
		 
		return treeNodeList;
	}
}

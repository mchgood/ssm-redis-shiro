package com.zhidisoft.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhidisoft.system.entity.Function;

public interface FunctionDao{
 
	int deleteByPrimaryKey(Integer funcId);

	int insert(Function record);

	Function selectByPrimaryKey(Integer funcId);

	List<Function> selectAll();

	int updateByPrimaryKey(Function record);
	
	
	List<Function> findByUserId(Integer userId);

	List<Function> findByUserId_menu(@Param("userId")Integer userId,@Param("funcId")Integer funcId);
}
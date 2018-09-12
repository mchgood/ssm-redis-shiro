package com.zhidisoft.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.service.RoleServiceIf;

@Controller
@RequestMapping("/system/role")
public class RoleController {
	@Resource
	RoleServiceIf roleService;
	
	@RequestMapping("/list")
	@ResponseBody
	public Map listRole(){
		Map map = new HashMap();
		
		List<Role> roleList = roleService.selectAll();
		
		map.put("success", true);
		map.put("result", roleList);
		
		return map;
	}
	
	@RequestMapping("/findByUserId")
	@ResponseBody
	public JsonResult findByUserId(Integer ids){
		
		List<Role> roleList = roleService.findByUserId(ids);		
		
		return JsonResult.buildSuccessResult(roleList);
	}
	
//	@RequestMapping("/assignRole")
//	@ResponseBody
//	public Map assignRole(String userIds,String roleIds){
//		Map map = new HashMap();
//		
//		int rows = roleService.assignRole(userIds,roleIds);
//		if(rows>0){		
//			map.put("success", true);	
//			map.put("message", "分配成功");
//		}else{
//			map.put("success", false);	
//			map.put("message", "分配失败");
//		}
//		return map;
//	}
	
	@RequestMapping("/assignRole")
	@ResponseBody
	public JsonResult assignRole(String userIds,String roleIds){
		
		int rows = roleService.assignRole(userIds,roleIds);

		if(rows>0){
			return JsonResult.buildSuccessResult("分配成功");
		}else{
			return JsonResult.buildFailResult("分配失败");
		}
		
	}
}

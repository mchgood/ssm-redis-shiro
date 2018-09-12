package com.zhidisoft.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;
import com.zhidisoft.result.PageBean;
import com.zhidisoft.result.TreeNode;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.system.service.FunctionServiceIf;
import com.zhidisoft.system.service.UserServiceIf;
@SuppressWarnings("all")
@Controller
public class UserController {
	@Resource
	UserServiceIf userService;
	
	@Resource
	FunctionServiceIf funcService;

	@RequestMapping("/menu")
	@ResponseBody
	public Map menu(HttpSession session,Integer id){
		// 从数据库中查询当前用户的父菜单
		//User user = (User) session.getAttribute("user");
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		
		List<TreeNode> treeNodeList = funcService.findByUserId_menu(user.getUserId(),id);	
		
		Map map = new HashMap();
		map.put("success", true);
		map.put("result", treeNodeList);

		return map;
	}
	
	@RequestMapping("/system/user/list")
	public String toListUser(){
		return "system/list_user";
	}
	
	// page:当前页码 ， rows：当前页的行数
	@RequestMapping("/system/user/show")
	@ResponseBody	
	public JsonResult userShow(Integer page,Integer rows,String userName,String status){
		
	   PageBean pageBean = userService.findByPage(page,rows,userName,status);		

	   return  JsonResult.buildSuccessResult(pageBean);
	}
	
	@RequestMapping("/system/user/remove")
	@ResponseBody
	public Map userRemove(String idsStr){
		String[] ids = idsStr.split(",");
		int rows = userService.deleteByIds(ids);
		
		Map map = new HashMap();
		if(rows>0){
		
			map.put("success", true);
			map.put("message", "删除成功");
		}else{
			map.put("success", false);
			map.put("message", "删除失败");
		}
			
		return  map;
	}
	
	@RequestMapping("/system/user/add")
	public String openUserAdd(){
		return "system/add_user";
	}
	
	@RequestMapping("/system/user/adduser")
	@ResponseBody
	public Map addUser(){
		Map map = new HashMap();
		
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/system/user/grant")
	public String userGrant(String ids,Model model){
		System.out.println(ids);
		
		// 将用户id保存到模型中
		model.addAttribute("ids", ids);
		
		return "system/assign_user";
		
	}
}

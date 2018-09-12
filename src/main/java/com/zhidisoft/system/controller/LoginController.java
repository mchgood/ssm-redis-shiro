package com.zhidisoft.system.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhidisoft.result.JsonResult;
@SuppressWarnings("all")
@Controller
public class LoginController {

	@GetMapping("/login")
	public String toLogin(){
		return "login";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public JsonResult login(String username,String password){
		
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try {
			currentUser.login(token);
		} catch (UnknownAccountException e) {
			throw new UnknownAccountException("用户名错误");		
		} catch (IncorrectCredentialsException e) {
			throw new IncorrectCredentialsException("密码错误");	
		} catch (AuthenticationException e) {
			throw new AuthenticationException("认证时的其他错误");
		} catch (Exception e) {
			throw new RuntimeException("认证时的其他错误");				
		}	
		
		return JsonResult.buildSuccessResult();
	}
	
	

	@RequestMapping("/index")
	public String toIndex(){
		return "index";
	}
}

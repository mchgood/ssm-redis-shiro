package com.zhidisoft.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.zhidisoft.system.dao.FunctionDao;
import com.zhidisoft.system.dao.RoleDao;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.Function;
import com.zhidisoft.system.entity.Role;
import com.zhidisoft.system.entity.User;
@SuppressWarnings("all")
public class AhtuRealm extends AuthorizingRealm{

	@Resource
	UserDao userDao;
	@Resource
	FunctionDao functionDao;
	@Resource
	RoleDao roleDao;
	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = ((UsernamePasswordToken)token).getUsername();
		// 根据username从数据库查询用户信息
		User user = userDao.findByName(username);
		if(user == null){
			return null;
		}
		return new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),"authRealm");
	}
	
	/*
	 * 授权
	 *  */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//通过principals获得登录后的user对象
		User user = (User)(principals.getPrimaryPrincipal());
		
		//从数据库中查询所有的功能的集合
		List<Function> functionList = functionDao.findByUserId(user.getUserId());
		Set funSet = new HashSet<>();
		for (Function fun : functionList) {
			if(fun.getFuncUrl()!=null){
				funSet.add(fun.getFuncUrl());
			}	
		}
		
		//从数据库中查询所有角色的集合
		List<Role> roleList = roleDao.findByUserId(user.getUserId());
		Set roleSet = new HashSet<>();
		for(Role role:roleList){
			roleSet.add(role.getRoleName());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//设置角色集合到SimpleAuthorizationInfo中
		info.setRoles(roleSet);
		//设置功能集合到SimpleAuthorizationInfo中
		info.setStringPermissions(funSet);
		return info;
	}
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}

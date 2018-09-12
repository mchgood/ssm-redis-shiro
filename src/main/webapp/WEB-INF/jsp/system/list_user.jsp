
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<%@include file="../script.html" %>
	</head>
	<body>
		<div id="toolbar">
			<span>用户名：</span>
			<input type="text" class="easyui-textbox" id="username" name="username">			
			<!-- <span>用户角色：</span>
			<input type="text" class="easyui-textbox" id="roleId" name="roleId"> -->
			<span>用户状态：</span>
			<input type="text" class="easyui-textbox" id="status" name="status">
			<a href="javascript:void(0)" class="easyui-linkbutton"  onClick="searcha()" >搜索</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"  onClick="addUser()">新增</a>
			
			<shiro:hasPermission name="system/user/edit">
				<a class="easyui-linkbutton">修改</a>	
			</shiro:hasPermission>	
			
			<a  href="javascript:void(0)" class="easyui-linkbutton" onClick="deleteUser()">删除</a>	
			
			<shiro:hasPermission name="system/user/import">
				<a class="easyui-linkbutton">导入</a>	
			</shiro:hasPermission>	
			
			<a href="javascript:void(0)" class="easyui-linkbutton"  onClick="assign()">角色分配</a>
		</div>
		<table id="datagrid"></table>
	</body>
	
	<script type="text/javascript">
		$("#datagrid").datagrid({
			url:"system/user/show",
			method:"post",
			toolbar:"#toolbar",
			fitColumns:true, //自动调整列宽
			idField:"userId", // 指示服务器传递的数据中，哪个字段是主键
			rownumbers:true, // 显示行号
			pagination:true, // 使用分页
			columns:[[
				{field:"userId",title:"选择",checkbox:true},
				{field:"userName",title:"用户名"},
				{field:"roleList",title:"角色",formatter:function(value,rowValue,index){
					var names = new Array();
					$.each(value,function(index,obj){
						if(obj){
							names.push(obj.roleName);
						}
					})
					return names.join(",");
				}},
				{field:"phone",title:"电话"},
				{field:"status",title:"状态",formatter:function(value,rowValue,index){
					if(value==1){
						return "可用";
					}else {
						return "禁用";
					}
				}},
				{field:"createTime",title:"创建时间"}
			]],
			loadFilter:function(data){
				if(data && data.success){
					return {"total":data.result.total,"rows":data.result.rows};
				}
			}
		})	
		
		 function searcha(){
			
			var username = $("#username").val();
			var status = $("#status").val();
			
			$("#datagrid").datagrid("load",{			
				"userName":username,
				"status":status
			})
		} 
		
		function deleteUser(){
			// 获取选中的用户
			var  rows = $("#datagrid").datagrid("getChecked");
			if(rows==null || rows.length<1){
				parent.$.messager.alert("警告","请选择需要删除的用户");				
				return;
			}
			
			// 删除前提供确认框
			parent.$.messager.confirm('请确认', '您真的要删除选择的用户吗?', function(r){
				if (r){
					var ids = new Array();
					$.each(rows,function(index,user){
						ids.push(user.userId);
					})
					
					
					// 将选中用户的id通过异步请求传递到后，进行删除 ，删除完毕，刷新数据网格
					$.ajax({
						url:"system/user/remove",
						data:{"idsStr":ids.join(",")},
						success:function(data){					
							if(data && data.success){
								parent.$.messager.show({
									title:'提示',
									msg:data.message,
									timeout:5000,  // 毫秒
									showType:'slide'
								});
								// 刷新数据网格
								searcha();
							}else{
								parent.$.messager.alert("提示",data.message);
							}
						},
						datatype:"json"
					})
				}
			});			
		}
		
		
		function addUser(){
			// 打开窗口
			parent.openTopWindow({
				"width":600,
				"title":"添加用户",
				"url":"system/user/add",
				"onClose":function(){
					// 刷新数据网格
					searcha();
				}
			})
		}
		
		// 为用户分配角色
		function assign(){
			// 获取勾选的用户信息
			var  rows = $("#datagrid").datagrid("getChecked");
			if(rows==null || rows.length<1){
				parent.$.messager.alert("警告","请选择用户");				
				return;
			}			
			// 获取用户id，封装到array数组中
			var ids = new Array();
			$.each(rows,function(index,user){
				ids.push(user.userId);
			})
					
			
			// 弹出窗口：分配角色 ， 并将勾选的用户信息传递到新窗口中
			parent.openTopWindow({
				"title":"分配角色",
				"url":"system/user/grant?ids="+ids.join(","),
				onClose:function(){
					// 刷新数据网格
					searcha();
				}				
			});
			
			
			
		}
	</script>
</html>
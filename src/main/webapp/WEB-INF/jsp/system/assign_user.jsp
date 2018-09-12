
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>分配角色</title>
		<%@include file="../script.html" %>
	</head>
	<body>
		<!-- 保存所选的用户id -->
		用户ID：<input type="text"  id="ids" value="${requestScope.ids }">
		<input type="button" id="assignBtn" value="分配">
			
		<!-- 把所有的角色查询出来展示到这个table中 -->
		<table id="datalist"></table>
	</body>
	
	<script type="text/javascript">
		$("#datalist").datagrid({
			url:"system/role/list",
			rownumbers:true,
			idField:"roleId",
			columns:[[
				{field:"roleId",title:"选择",checkbox:true},
				{field:"roleName",title:"角色名称"},
				{field:"note",title:"角色说明"}
			]],
			loadFilter:function(data){
				if(data && data.success){
					return data.result;
				}
			},
			onLoadSuccess:function(){
				// 判断选中的用户数量
				var ids = $("#ids").val();
				if(ids.indexOf(",")==-1){
					// 字符串中没有逗号，只选中了一个用户 ， 需要默认勾选该用户的角色
					
					// 发送异步请求，到后台查询当前用户的已有角色
					$.ajax({
						url:"system/role/findByUserId",
						data:{"ids":ids},
						success:function(data){
							if(data && data.success){
								// 得到用户的已有角色，勾选datagrid中的角色
								$.each(data.result,function(index,role){
									$("#datalist").datagrid("selectRecord",role.roleId);
								})
							}
						},
						datatype:"json"
					})
					
					
				} else {
					// 选中了多个用户，不需要勾选用户角色
					
				}
			}
		})
	
		
		$("#assignBtn").on("click",function(){
			// 得到用户id
			var userIds = $("#ids").val();
			
			// 得到角色id
			var roleIds = new Array();
			var rows = $("#datalist").datagrid("getChecked");
			if(rows!=null && rows.length>0){
				$.each(rows,function(index,role){
					roleIds.push(role.roleId);
				})
			}
			
			// 将用户id和角色id传递到后台
			$.ajax({
				url:"system/role/assignRole",
				data:{"userIds":userIds,"roleIds":roleIds.join(",")},
				success:function(data){
					alert(data.success)
					if(data && data.success){
						$.messager.alert("提示",data.message);
						// 关闭分配窗口
						parent.closeTopWindow();
					}else{
						parent.$.messager.alert("提示",data.message);
					}
				},
				datatype:"json"
			})
			
		})
	</script>
	
</html>
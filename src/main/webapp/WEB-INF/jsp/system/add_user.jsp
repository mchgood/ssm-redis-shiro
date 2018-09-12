
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
		<title>用户管理</title>
		<%@include file="../script.html" %>
	</head>
	<body>
		<form id="addForm">
			用户名：<input type="text" name="userName"><br>
			<input type="button" value="添加"  id="addBtn">
		</form>
	</body>
	
	<script type="text/javascript">
		$("#addBtn").on("click",function(){
			$.ajax({
				url:"system/user/adduser",
				data:"",
				success:function(data){
					if(data && data.success){
						// 用户添加成功
						parent.closeTopWindow();
					}
				},
				datatype:"json"
			})
		})
	
	</script>
	
</html>
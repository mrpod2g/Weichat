﻿<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="nju.iip.dto.WeixinUser;"%>
<html>
<head>
	<title>用户信息</title>
	<meta name="viewport" content="width=device-width,user-scalable=0">
	<style type="text/css">
		*{margin:0; padding:0}
		table{border:1px dashed #B9B9DD;font-size:12pt}
		td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
	</style>
</head>
<body>
	<% 
		// 获取由OAuthServlet中传入的参数
		WeixinUser user = (WeixinUser)request.getAttribute("snsUserInfo"); 
		if(null != user) {
	%>
	<table>
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td><%=user.getOpenId()%></td></tr>
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><img src="<%=user.getHeadImgUrl()%>" width="20%"/></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
		<tr><td>姓名</td><td><%=user.getName()%></td></tr>
		<tr><td>病历号</td><td><%=user.getCardID()%></td></tr>
		<tr><td>电话</td><td><%=user.getPhone()%></td></tr>
	</table>
	<%
		}
		else 
			out.print("用户不同意授权,未获取到用户信息！");
	%>
</body>
</html>
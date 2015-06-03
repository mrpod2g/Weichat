﻿<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="nju.iip.dto.WeixinUser"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link type="text/css" rel="stylesheet" href="css/base.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<title>已测量表记录</title>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
		*{margin:0; padding:0}
		table{border:1px dashed #B9B9DD;font-size:12pt}
		td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
	</style>
</head>
<body>
	<% 
		// 获取由OAuthServlet中传入的参数
		List<Map<String, String>> record_listList = (List<Map<String, String>>)request.getAttribute("record_listList"); 
		if(null != record_listList) {
			for(Map<String, String> map:record_listList) {
	%>
	<div class="bgfff form ov">
	<table>
		<tr><td>量表名称</td><td><%=map.get("scaleName")%></td></tr>
		<tr><td>得分</td><td><%=map.get("score")%></td></tr>
		<tr><td>填写时间</td><td><%=map.get("time")%></td></tr>
	</table>
	</div>
	<%
		     }
		}
		else 
			out.print("获取量表信息失败！");
	%>
</body>
</html>
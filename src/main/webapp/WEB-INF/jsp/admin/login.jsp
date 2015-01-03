<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Full Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/res/jeasyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/jeasyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/jeasyui/k.css">

    <script type="text/javascript" src="${ctx}/res/jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/jeasyui/locale/easyui-lang-zh_CN.js"></script>
    <style>
  		.login_div {
  			width: 400px;
  			margin-left: 280px;
  			margin-top: 100px;
  		}
  		
  		body {
  			margin: auto;
  			width: 960px;
  		}
    </style>
</head>
<body>
	<div class="login_div">
	    <div style="margin:20px 0;"></div>
	    <form id="form" action="${ctx }/j_spring_security_check" method="post">
	    <div class="easyui-panel" title="后台登陆" style="width:400px;padding:30px 70px 20px 70px">
	        <div style="margin-bottom:10px">
	            <input name="j_username" class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38">
	        </div>
	        <div style="margin-bottom:20px">
	            <input name="j_password" class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'密码',iconCls:'icon-lock',iconWidth:38">
	        </div>
	        <c:if test="${!empty(param.error) }">
	        	<div style="margin-bottom:20px; color: red;">
						用户名或密码错误
				</div>		
					</c:if>
	        <div>
	            <a href="#" onclick="submit()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
	                <span style="font-size:14px;">登陆</span>
	            </a>
	        </div>
	    </div>
	    </form>
    </div>
	<script type="text/javascript" src="${ctx }/res/jeasyui/k.js"></script>
	<script>
		function submit() {
			$('#form').submit();
		}
	</script>
	
</body>
</html>	

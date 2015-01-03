<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	  <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
	<body>
		<div class="container">
			<form action="${ctx }/test/push" method="post">
			<div>结果：${result }</div>
			<div>
				<select name="code">
					<option value="0"  ${push.code eq '0' ? 'selected' : ''}>(0)一般推送</option>
					<option value="10" ${push.code eq '10' ? 'selected' : ''}>(10)通知公告</option>
					<option value="20" ${push.code eq '20' ? 'selected' : ''}>(20)物业维修状态变更</option>
					<option value="30" ${push.code eq '30' ? 'selected' : ''}>(30)物业回复了帖子</option>
					<option value="40" ${push.code eq '40' ? 'selected' : ''}>(40)系统消息</option>
				</select>
			</div>
			<div>
				标题
				<input type="text" name="title" value="${push.title }"/>
			</div>
			<div>
				内容
				<textarea rows="10" cols="2000" name="content">${push.content }</textarea>
			</div>
			<div>
				id（如果需要的放。有些类型的推送是客户端拿着id去服务端拿数据）
				<input type="text" value="${push.id }" name="id">
			</div>
			<div>
				<input type="submit" value="提交"/>
			</div>
			</form>
		</div>
	</body>
</html>
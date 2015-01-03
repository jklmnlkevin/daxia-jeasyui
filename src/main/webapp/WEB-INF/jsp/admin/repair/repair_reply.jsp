<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<style>
	.message {
		border: 1px solid #ccc;
		width: 80%;
		margin-bottom: 5px;
		margin-left: 10px;
		margin-right: 10px;
	}
	.message_name {
		color: #aaa;
		padding: 10px;
	}
	.message_content {
		padding: 10px;
	}
	
	.reply {
		padding: 10px;
	}
</style>

<c:set var="module" value="repair"/>

<div class="pageContent">
		<div layoutH="10">
			<form action="${ctx }/admin/${module }/replySubmit?navTabId=${param.navTabId}"" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);" method="post">
			<input type="hidden" name="repair.id" value="${n.id }">
			<input type="hidden" name="user.id" value="${currentUser.id }">
			<div class="reply" style="height: 150px;" >
			<textarea name="content" style="width: 80%;height: 150px;"></textarea>
			</div>
			<div style="padding: 10px;">
				<input type="submit" value="回复"/>
			</div>
			</form>
			
			<hr/>
			<div>
			<c:forEach items="${replies }" var="r">
			<div class="message">
				<c:if test="${r.user.userType != 3}">
				<div class="message_name">${r.user.username }&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${r.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
				<div class="message_content">
					${r.content }
				</div>
				</c:if>
				<c:if test="${r.user.userType eq 3}">
				<div class="message_name" style="text-align: right">物业&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${r.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
				<div class="message_content" style="text-align: right">
					${r.content }
				</div>
				</c:if>
			</div>
			</c:forEach>
		</div>
		</div>
		
</div>

<script>
</script>

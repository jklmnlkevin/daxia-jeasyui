<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="question"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="user.id" value="${n.user.id }"/>
		<input type="hidden" name="community.id" value="${currentUser.community.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>提问者:</label>
				<input type="text" disabled value="${n.user.username }" size="30" />
			</p>
			<p>
				<label>问题标题:</label>
				<input type="text" name="title" value="${n.title }" size="30" readonly="readonly"/>
			</p>
			<div class="unit">
				<label>问题内容:</label>
				<textarea name="content" style="width:70%; height: 100px;" readonly>${n.content }</textarea>
			</div>
			<div class="unit">
				<label>提问时间:</label>
				<input type="text" name="createTime" value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${n.createTime }'/>" size="30" readonly/>
			</div>
			<div class="unit">
				<label>回复状态:</label>
				<select name="status">
					<option>---</option>
					<c:forEach items="${questionStatus }" var="s">
						<option value="${s.value }" ${s.value eq n.status ? 'selected' : '' }>${s.remark }</option>
					</c:forEach>
				</select>
			</div>
			<div class="unit">
				<label>回复:</label>
				<textarea name="reply" style="width:70%; height: 100px;">${n.reply }</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(question.id)}">
						<sec:authorize ifAnyGranted="question.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(question.id)}">
						<sec:authorize ifAnyGranted="question.update">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

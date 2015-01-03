<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="advise"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="user.id" value="${n.user.id }"/>
		<input type="hidden" name="store.id" value="${n.store.id }"/>
		<input type="hidden" name="community.id" value="${currentUser.community.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户:</label>
				<input type="text" value="${n.user.username }" size="30" />
			</p>
			<p>
				<label>评分:</label>
				<input type="text" name="score" value="${n.score }" size="30" />
			</p>
			<p>
				<label>创建时间:</label>
				<input type="text" name="createTime" value='<fmt:formatDate value="${n.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' size="30" />
			</p>
			<p>
				<label>物业:</label>
				<input type="text" value="${n.store.name }" size="30" />
			</p>
			<div class="unit">
				<label>内容:</label>
				<textarea name="content" style="width: 70%; height: 100px;">${n.content }</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(advise.id)}">
						<sec:authorize ifAnyGranted="advise.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(advise.id)}">
						<sec:authorize ifAnyGranted="advise.update">
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="systemMessage"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>标题:</label>
				<input type="text" name="title" value="${n.title }" size="30" />
			</p>
			<div class="unit">
				<label>内容:</label>
				<textarea name="content" style="width: 60%; height: 100px;">${n.content }</textarea>
			</div>
			<c:if test="${not empty(n.id) }">
				<p>
					<label>创建时间:</label>
					<input type="text" name="createTime" readonly value="<fmt:formatDate value='${n.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" size="30" />
				</p>
			</c:if>
			<c:if test="${not empty(n.id) }">
			<p>
				<label>推送时间:</label>
					<input type="text" name="pushTime" readonly value="<fmt:formatDate value='${n.pushTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" size="30" />
			</p>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(systemMessage.id)}">
						<sec:authorize ifAnyGranted="systemMessage.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(systemMessage.id)}">
						<sec:authorize ifAnyGranted="systemMessage.update">
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

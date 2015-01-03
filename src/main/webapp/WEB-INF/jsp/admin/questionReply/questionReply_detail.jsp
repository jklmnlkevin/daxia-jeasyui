<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="questionReply"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>咨询问题:</label>
				<input type="text" name="question" value="${n.question }" size="30" />
			</p>
			<p>
				<label>回复者:</label>
				<input type="text" name="user" value="${n.user }" size="30" />
			</p>
			<p>
				<label>回复内容:</label>
				<input type="text" name="content" value="${n.content }" size="30" />
			</p>
			<p>
				<label>回复时间:</label>
				<input type="text" name="createTime" value="${n.createTime }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(questionReply.id)}">
						<sec:authorize ifAnyGranted="questionReply.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(questionReply.id)}">
						<sec:authorize ifAnyGranted="questionReply.update">
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

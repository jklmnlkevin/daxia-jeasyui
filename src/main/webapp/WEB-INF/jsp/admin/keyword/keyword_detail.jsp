<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="keyword"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${keyword.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>关键词:</label>
				<input type="text" name="keywords" value="${keyword.keywords }" size="30" />
			</p>
			<p>
				<label>文章:</label>
				<input type="text" name="article.id" bringBackName="article.id" value="${keyword.article.id }"/>
				<input type="text" bringBackName="article.name" value="${keyword.article.title }" size="30"  lookupGroup="article" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/article/search" lookupGroup="article"></a>
			</p>
			<p>
				<label>商家:</label>
				<input type="text" name="store.id" value="${keyword.store.id }" size="30" store="true"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(keyword.id)}">
						<sec:authorize ifAnyGranted="keyword.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(keyword.id)}">
						<sec:authorize ifAnyGranted="keyword.update">
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

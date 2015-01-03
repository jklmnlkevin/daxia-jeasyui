<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="systemConfig"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${systemConfig.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>key:</label>
				<input type="text" name="key" value="${systemConfig.key }" size="30" />
			</p>
			<p>
				<label>value:</label>
				<input type="text" name="value" value="${systemConfig.value }" size="30" />
			</p>
			
			<p>
				<label>说明:</label>
				<textarea name="remark" rows="10" cols="100">${systemConfig.remark }</textarea>
			</p>

		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(systemConfig.id)}">
						<sec:authorize ifAnyGranted="system.systemConfig.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(systemConfig.id)}">
						<sec:authorize ifAnyGranted="system.systemConfig.update">
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

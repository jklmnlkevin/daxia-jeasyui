<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="apiTestParameter"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${apiTestParameter.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>api:</label>
				<input type="hidden" name="apiTest.id" value="${apiTestParameter.apiTest.id }" size="30" lookupGroup="lookup" bringBackName="lookup.id"/>
				<input type="text" value="${apiTestParameter.apiTest.name }" size="30" lookupGroup="lookup" bringBackName="lookup.name"/>
				<a class="btnLook" href="${ctx }/admin/apiTest/search" lookupGroup="lookup">查找</a>
			</p>
			<p>
				<label>参数名:</label>
				<input type="text" name="name" value="${apiTestParameter.name }" size="30" />
			</p>
			<p>
				<label>参数描述:</label>
				<input type="text" name="description" value="${apiTestParameter.description }" size="30" />
			</p>
			<p>
				<label>是否必须:</label>
				<input type="text" name="required" value="${apiTestParameter.required }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(apiTestParameter.id)}">
						<sec:authorize ifAnyGranted="apiTestParameter.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(apiTestParameter.id)}">
						<sec:authorize ifAnyGranted="apiTestParameter.update">
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

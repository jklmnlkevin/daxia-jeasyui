<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="communityAddApply"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>小区名称:</label>
				<input type="text" name="name" value="${n.name }" size="30" />
			</p>
			<p>
				<label>小区地址:</label>
				<input type="text" name="address" value="${n.address }" size="30" />
			</p>
			<p>
				<label>地区:</label>
				<input type="text" name="district" value="${n.district }" size="30" />
			</p>
			<p>
				<label>0，新增， 1，同意，2，拒绝:</label>
				<input type="text" name="status" value="${n.status }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(communityAddApply.id)}">
						<sec:authorize ifAnyGranted="communityAddApply.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(communityAddApply.id)}">
						<sec:authorize ifAnyGranted="communityAddApply.update">
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

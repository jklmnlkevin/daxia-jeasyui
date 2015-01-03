<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="doorplate"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>楼栋号:</label>
				<input type="hidden" name="building.id" bringBackName="building.id" value="${n.building.id }" size="30" />
				<input type="text" bringBackName="building.name" value="${n.building.name }" size="30"  lookupGroup="building" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/building/search" lookupGroup="building"></a>
			</p>
			<p>
				<label>门牌号:</label>
				<input type="text" name="name" value="${n.name }" size="30" />
			</p>
			<p>
				<label>房屋面积:</label>
				<input type="text" class="numbers" name="area" value="${n.area }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(doorplate.id)}">
						<sec:authorize ifAnyGranted="doorplate.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(doorplate.id)}">
						<sec:authorize ifAnyGranted="doorplate.update">
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

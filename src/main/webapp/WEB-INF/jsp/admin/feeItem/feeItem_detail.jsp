<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="feeItem"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="community.id" value="${currentUser.community.id}" size="30" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>缴费项目名称:</label>
				<select class="required" name="type">
					<option value="">---</option>
					<c:forEach items="${feeItemTypes}" var="item">
						<option value="${item.value }" ${item.remark eq n.name ? 'selected' : '' }>${item.remark }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>单价:</label>
				<input class="required" type="text" name="price" value="${n.price }" size="30" />
			</p>
			<p>
				<label>单位:</label>
				<input class="required" type="text" name="unit" value="${n.unit }" size="30" />
			</p>
			<%-- <p>
				<label>小区:</label>
				<input type="text" name="community" value="${n.community }" size="30" />
			</p> --%>
			<c:if test="${not empty(n.id) }">
			<p>
				<label>创建时间:</label>
				<input type="text" name="createTime" value='<fmt:formatDate value="${n.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' size="30" />
			</p>
			</c:if>
			<div class="unit">
				<label>备注:</label>
				<textarea name="remark" style="width: 40%; height: 100px;">${n.remark }</textarea>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(feeItem.id)}">
						<sec:authorize ifAnyGranted="feeItem.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(feeItem.id)}">
						<sec:authorize ifAnyGranted="feeItem.update">
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

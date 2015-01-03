<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="houseKeeping"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户:</label>
				<input type="text" name="user" value="${n.user }" size="30" />
			</p>
			<p>
				<label>小区:</label>
				<input type="text" name="community" value="${n.community }" size="30" />
			</p>
			<p>
				<label>标题:</label>
				<input type="text" name="title" value="${n.title }" size="30" />
			</p>
			<p>
				<label>内容:</label>
				<input type="text" name="content" value="${n.content }" size="30" />
			</p>
			<p>
				<label>年龄:</label>
				<input type="text" name="age" value="${n.age }" size="30" />
			</p>
			<p>
				<label>价格:</label>
				<input type="text" name="price" value="${n.price }" size="30" />
			</p>
			<p>
				<label>服务类型:</label>
				<input type="text" name="serviceType" value="${n.serviceType }" size="30" />
			</p>
			<p>
				<label>供需类型:</label>
				<input type="text" name="type" value="${n.type }" size="30" />
			</p>
			<p>
				<label>创建时间:</label>
				<input type="text" name="createTime" value="${n.createTime }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(houseKeeping.id)}">
						<sec:authorize ifAnyGranted="houseKeeping.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(houseKeeping.id)}">
						<sec:authorize ifAnyGranted="houseKeeping.update">
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

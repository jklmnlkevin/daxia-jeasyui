<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="user"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/saveRole?id=${user.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div style="margin:10px;padding:10px; border-bottom:1px solid #ccc; font-size:15pt;">
				用户名：${user.username }
			</div>
			<div  style="margin:10px;padding:10px;  border:0px solid #ccc; font-size:15pt;">
			角色:
			</div>
			<div  style="margin:10px; margin-left:20px; border:0px solid #ccc; font-size:12pt;">
				<ul>
					<c:forEach items="${roles}" var="r">
					<li>
						<c:set var="contains" value="false"/>
						<c:forEach items="${user.roles }" var="ur">
							<c:if test="${ur.id eq r.id }">
								<c:set var="contains" value="true"/>
							</c:if>
						</c:forEach>
						<label for="role_${r.id }"><input type="checkbox" ${contains ? 'checked' : '' } name="roleIds" value="${r.id }"  id="role_${r.id }">
						${r.name }</label>
					</li>
					</c:forEach>
				</ul>	
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(user.id)}">
						<sec:authorize ifAnyGranted="user.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(user.id)}">
						<sec:authorize ifAnyGranted="user.update">
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

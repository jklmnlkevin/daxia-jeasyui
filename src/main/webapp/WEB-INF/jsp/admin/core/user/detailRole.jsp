<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="module" value="user"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/saveRole?id=${user.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
			<p>
				<label>用户名:</label>
				<input type="text" readonly name="username" value="${user.username }" size="30" />
			</p>
			
			<p>
				<label>角色:</label>
				<div>
					<c:forEach items="${roles }" var="r">
						<input type="checkbox" ${r.contains ? 'checked' : '' } name="rolesId" value="${r.id }">${r.name } 
					</c:forEach>
				</div>
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

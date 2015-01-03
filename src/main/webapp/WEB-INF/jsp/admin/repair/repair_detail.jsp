<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="repair"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="user.id" value="${n.user.id }"/>
		<input type="hidden" name="community.id" value="${currentUser.community.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户:</label>
				<input type="text" disabled="disabled" value="${n.user.username }" size="30" />
			</p>
			<p>
				<label>标题:</label>
				<input type="text"  value="${n.title }" name="title" size="30" />
			</p>
			
			<p>
				<label>创建时间:</label>
				<input type="text" name="createTime" value='<fmt:formatDate value="${n.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>' size="30" readonly="readonly"/>
			</p>
			<p>
				<label>状态:</label>
				<select name="state">
				<c:forEach items="${repairStatus}" var="s">
					<option value="${s.value }" ${s.value eq n.state ? 'selected' : '' }>${s.remark }</option>
				</c:forEach>
				</select>
			</p>
			<div class="unit">
				<label>备注:</label>
				<textarea name="remark" style="width: 60%; height: 100px;">${n.remark }</textarea>
			</div>
			<div class="unit">
				<label>图片:</label>
				<input type="hidden" name="image" value="${n.image }" size="30" />
				<img src="${ctx }/image/${n.image}">
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(repair.id)}">
						<sec:authorize ifAnyGranted="repair.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(repair.id)}">
						<sec:authorize ifAnyGranted="repair.update">
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

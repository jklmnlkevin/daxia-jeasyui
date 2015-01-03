<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="notice"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/doPush?id=${notice.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<input type="hidden" name="createTime" value="<fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/>"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>小区:</label>
				<input type="hidden" name="community.id" value="${notice.community.id }" size="30" bringBackName="community.id"/>
				<input type="text" bringBackName="community.name" value="${notice.community.name }" size="30" readonly="readonly"/>
			</p>
			<p>
				<label>标题:</label>
				<input type="text" name="title" value="${notice.title }" size="30" />
			</p>
			<div class="unit">
				<label>内容:</label>
				<textarea cols="100" rows="10" name="content">${notice.content }</textarea>
			</div>
			<div class="unit">
				<label>推送对象:</label>
				<input type="radio" name="pushTarget" value="all" checked onchange="$('#selectDiv').hide();">所有人
				<!-- <input type="radio" name="pushTarget" value="selected" onchange="$('#selectDiv').show()"> 部分人 -->
			</div>
			<div class="unit" id="selectDiv" style="display: none;">
				<label>选择推送对象:</label>
				<input type="text" name="selectedUser" bringBackName="user.username" size="30" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/user/search" lookupGroup="user"></a>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(notice.id)}">
						<sec:authorize ifAnyGranted="notice.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(notice.id)}">
						<sec:authorize ifAnyGranted="notice.update">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">发布</button></div></div>
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

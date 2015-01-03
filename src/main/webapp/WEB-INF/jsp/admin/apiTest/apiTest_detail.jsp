<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="apiTest"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${apiTest.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>api名称:</label>
				<input type="text" name="name" value="${apiTest.name }" size="30" />
			</p>
			<p>
				<label>描述:</label>
				<input type="text" name="description" value="${apiTest.description }" size="30" />
			</p>
			<p>
				<label>url:</label>
				<textarea name="url" cols="100" rows="20">${apiTest.url }</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(apiTest.id)}">
						<sec:authorize ifAnyGranted="apiTest.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(apiTest.id)}">
						<sec:authorize ifAnyGranted="apiTest.update">
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
<script>
	function _delete(btn) {
		$(btn).parent().remove();
	}
</script>

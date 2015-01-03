<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="manager"/>

<div class="pageContent">
	<span id="currentHeadStoreId" style="display:none;">${currentStoreName }</span>
	<form method="post" action="${ctx }/admin/${module }/save?id=${manager.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="setStoreName();return validateCallback(this,navTabAjaxDone);">
		<input id="isHead_ipt" type="hidden" name="type" value="${manager.type }" size="30" />
		<input id="storeName_ipt" type="hidden" name="storeName" value="${manager.storeName }" size="30" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户名:</label>
				<input type="text" name="username" value="${manager.username }" size="30" />
			</p>
			<c:if test="${empty(manager.id) }">
				<p>
					<label>密码:</label>
					<input type="password" name="password" size="30" />
				</p>
			</c:if>
			<p id="manager_p">
				<label>商户:</label>
				<input type="hidden" id="oldValue" value="${discount.storeId }"/>				
				<input type="text" name="storeId" value="${discount.storeId }" size="30"  attr="storeName" submitName="storeId" firstBlank="false"/>
			</p>
			<p>
				<label>联系方式:</label>
				<input type="text" name="telephone" value="${manager.telephone }" size="30" />
			</p>
			<p>
				<label>邮箱:</label>
				<input type="text" name="email" value="${manager.email }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(manager.id)}">
						<sec:authorize ifAnyGranted="manager.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(manager.id)}">
						<sec:authorize ifAnyGranted="manager.update">
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
<script type="text/javascript">	
	var headStroName;
	
	$(document).ready(function(){
		headStroName = $("#currentHeadStoreId").html();
	});

	function setStoreName() {
		$("#storeName_ipt").val($("#manager_p select[name='storeId']").find("option:selected").text());
		if($("#storeName_ipt").val()==headStroName) {
			$("#isHead_ipt").val(0);
		}else {
			$("#isHead_ipt").val(1);
		}
	}
</script>

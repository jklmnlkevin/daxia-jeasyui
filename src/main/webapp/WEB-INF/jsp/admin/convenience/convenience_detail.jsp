<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="convenience"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);" enctype="multipart/form-data">
		<input type="hidden" name="image" value="${n.image }"/>
		<input type="hidden" name="community.id" value="${currentUser.community.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>服务类型:</label>
				<select name="type" id="type" onchange="changeType();">
					<c:forEach items="${convenienceTypes }" var="t">
						<option value="${t.value }" ${t.value eq n.type ? 'selected' : '' }>${t.remark }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>服务名称:</label>
				<input type="text" name="name" value="${n.name }" size="30" />
			</p>
			<p ct="contact">
				<label>办公地址:</label>
				<input type="text" name="address" value="${n.address }" size="30" />
			</p>
			<p ct="contact">
				<label>办公电话:</label>
				<input type="text" name="phone" value="${n.phone }" size="30" />
			</p>
			<p ct="contact">
				<label>联系人:</label>
				<input type="text" name="contacter" value="${n.contacter }" size="30" />
			</p ct="contact">
			<div class="unit"  ct="flow">
				<label>办事流程:</label>
				<textarea name="flow" style="width: 70%; height: 100px;">${n.flow }</textarea>
			</div>
			<div class="unit" ct="contact">
				<label>照片:</label>
				<input type="file" name="imageFile" size="30" />
			</div>
			<c:if test="${not empty(n.image) }">
				<div class="unit"  ct="contact">
					<label>当前图片</label>
					<div>
						<img src="${ctx }/image/${n.image}" style="width: 60%;"/>
					</div>
				</div>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(convenience.id)}">
						<sec:authorize ifAnyGranted="convenience.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(convenience.id)}">
						<sec:authorize ifAnyGranted="convenience.update">
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
	function changeType() {
		var $select = $('#type');
		if ($select.val() == 0) { // 联系方式
			$('p[ct=flow]').hide();
			$('div[ct=flow]').hide();
			
			$('p[ct=contact]').show();
			$('div[ct=contact]').show();
		} else if ($select.val() == 1) { // 办事流程
			$('p[ct=flow]').show();
			$('div[ct=flow]').show();
			
			$('p[ct=contact]').hide();
			$('div[ct=contact]').hide();
		}
	}
	
	changeType();
</script>

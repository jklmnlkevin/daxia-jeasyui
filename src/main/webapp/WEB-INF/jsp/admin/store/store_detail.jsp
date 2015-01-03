<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="store"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${store.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>商家名称:</label>
				<input type="text" name="name" value="${store.name }" size="30" />
			</p>
			<p>
				<label>地址:</label>
				<input type="text" name="address" value="${store.address }" size="30" />
			</p>
			<p>
				<label>百度纬度:</label>
				<input type="text" name="baiduLat" value="${store.baiduLat }" size="30" />
			</p>
			<p>
				<label>百度经度:</label>
				<input type="text" name="baiduLong" value="${store.baiduLong }" size="30" />
			</p>
			<p>
				<label>google纬度:</label>
				<input type="text" name="googleLat" value="${store.googleLat }" size="30" />
			</p>
			<p>
				<label>google经度:</label>
				<input type="text" name="googleLong" value="${store.googleLong }" size="30" />
			</p>
			<p>
				<label>公交站:</label>
				<input type="text" name="busStop" value="${store.busStop }" size="30" />
			</p>
			<p>
				<label>商家图片:</label>
				<input type="text" name="storeImage" value="${store.storeImage }" size="30" />
			</p>
			<p>
				<label>联系电话:</label>
				<input type="text" name="phone" value="${store.phone }" size="30" />
			</p>

		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(store.id)}">
						<sec:authorize ifAnyGranted="store.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(store.id)}">
						<sec:authorize ifAnyGranted="store.update">
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

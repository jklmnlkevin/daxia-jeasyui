<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="product"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${product.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return iframeCallback(this,navTabAjaxDone);" enctype="multipart/form-data">
		<input type="hidden" name="community.id" value="${currentUser.community.id}"/>
		<div class="pageFormContent" layoutH="56">
		
			<p>
				<label>商品类别:</label>
				<input type="hidden" name="category.id" bringBackName="category.id" value="${product.category.id }" size="30" />
				<input type="text" bringBackName="category.name" value="${product.category.name }" size="30"  lookupGroup="category" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/category/search" lookupGroup="category"></a>
			</p>
			
			<p>
				<label>商品名称:</label>
				<input type="text" name="name" value="${product.name }" size="30" />
			</p>
			<p>
				<label>价格:</label>
				<input type="text" name="price" value="${product.price }" size="30" />
			</p>
			<div class="unit">
				<label>型号:</label>
				<input type="text" name="specification" value="${product.specification }" size="30" />
				<span class="info">请填入如：500g， 10支， 100粒， 10kg等信息</span>
			</div>
			<div class="unit">
				<label>价格单位:</label>
				<input type="text" name="unit" value="${product.unit }" size="30" />
				<span class="info">请填入如：元/包，元/斤等信息</span>
			</div>
			<div class="unit">
				<label>备注:</label>
				<input type="text" name="remark" value="${product.remark }" size="60" />
			</div>
				<div class="unit">
					<label>图片:</label>
					<input type="file" name="imagePath" size="10" />
				</div>
			<c:if test="${not empty(product.id) }">
				<input type="hidden" name="image" value="${product.image }">
				<div class="unit">
					<label>图片:</label>
					<img src="${ctx }/image/${product.image}"/>
				</div>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(product.id)}">
						<sec:authorize ifAnyGranted="product.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(product.id)}">
						<sec:authorize ifAnyGranted="product.update">
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

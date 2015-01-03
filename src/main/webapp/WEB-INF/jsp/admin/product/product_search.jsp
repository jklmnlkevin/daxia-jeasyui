<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="product"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/search">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form onsubmit="return dwzSearch(this, 'dialog');" action="${ctx }/admin/${module}/search" method="post">
	<input type="hidden" name="productType" value="${product.productType }"/>
	<div class="searchBar">
				用户名或手机：
				<input type="text" name="term" value="${product.term}"/>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<!-- <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li> -->
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="100">商品名称</th>
				<th align="center" width="100">价格</th>
				<th align="center" width="100">型号</th>
				<th align="center" width="100">商家</th>
				<th align="center" width="100">备注</th>
				<th align="center" width="100">图片路径</th>
				<th align="center" width="100">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${products }" var="n">
			<tr target="sid_product" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
					<td>${n.name}</td>
					<td>${n.price}</td>
					<td>${n.model}</td>
					<td>${n.store.name}</td>	
					<td>${n.remark}</td>
					<td>${n.imagePath}</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({name:'${n.productname }', orgName:'技术部', orgNum:'1001'})" title="查找带回">选择</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:'10'})">
				<option value="${page.numPerPage}" selected>${page.numPerPage}</option>
				<c:if test="${page.numPerPage != 15}">
					<option value="15">15</option>
				</c:if>
				<c:if test="${page.numPerPage != 50}">
					<option value="50">50</option>
				</c:if>
				<c:if test="${page.numPerPage != 100}">
					<option value="100">100</option>
				</c:if>
			</select>
			<span>条，共${page.totalRecords}条</span>
		</div>
		
		<div class="pagination" targetType="dialog" totalCount="${page.totalRecords}" numPerPage="${page.numPerPage}" pageNumShown="10" currentPage="${page.pageNum}"></div>

	</div>
</div>

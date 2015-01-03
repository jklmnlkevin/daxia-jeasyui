<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="reply"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/search">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form onsubmit="return dwzSearch(this, 'dialog');" action="${ctx }/admin/${module}/search" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>关联id：</label>
				<input type="text" name="refrenceId" value="${query.refrenceId}"/>	
			</li>
			<li>
				<label>关联类型：</label>
				<input type="text" name="type" value="${query.type}"/>	
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
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
				<th align="center" width="100">关联id</th>
				<th align="center" width="100">回复内容</th>
				<th align="center" width="100">回复用户</th>
				<th align="center" width="100">回复时间</th>
				<th align="center" width="100">是否是物业回复</th>
				<th align="center" width="100">引用回复</th>
				<th align="center" width="100">第几楼</th>
				<th align="center" width="100">关联类型</th>
				<th align="center" width="100">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="n">
			<tr target="sid_reply" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
				<td>${n.refrenceId}</td>
				<td>${n.content}</td>
				<td>${n.user.username}</td>
				<td><fmt:formatDate value="${n.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${n.isEstate}</td>
				<td>${n.parentReply.content}</td>
				<td>${n.floor}</td>
				<td>${n.type}</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({id:'${n.id }', name:'技术部'})" title="查找带回">选择</a>
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

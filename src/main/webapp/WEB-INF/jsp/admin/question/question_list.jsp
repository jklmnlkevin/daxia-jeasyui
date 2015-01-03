<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="question"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>提问者：</label>
				<input type="text" name="user.username" value="${dto.user.username}"/>	
			</li>
			<li>
				<label>问题标题：</label>
				<input type="text" name="title" value="${dto.title}"/>	
			</li>
			<li>
				<label>问题内容：</label>
				<input type="text" name="content" value="${dto.content}"/>	
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				                <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
				
			</ul>
		</div>
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<%-- <sec:authorize ifAnyGranted="question.add">
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="navTab" mask="true"><span>添加</span></a></li>
			</sec:authorize> --%>
			
			<sec:authorize ifAnyGranted="question.delete">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="question.update">
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>查看或修改</span></a></li>
			</sec:authorize>
			<sec:authorize ifAnyGranted="question.update">
			<li><a class="edit" href="${ctx}/admin/${module}/reply?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" title="咨询回复" warn="请选择要修改的记录"><span>回复</span></a></li>
			</sec:authorize>
			
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="100">回复状态</th>
				<th align="center" width="100">提问者</th>
				<th align="center" width="100">问题标题</th>
				<th align="center" width="100">提问时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="n">
			<tr target="sid_user" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
				<td>
					<c:forEach items="${questionStatus}" var="s">
						<c:if test="${s.value eq n.status }">
							${s.remark }
						</c:if>
					</c:forEach>
				</td>
				<td>${n.user.username}</td>
				<td>${n.title}</td>
				<td>
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${n.createTime}" />
				</td>
				
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
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
		
		<div class="pagination" targetType="navTab" totalCount="${page.totalRecords}" numPerPage="${page.numPerPage}" pageNumShown="10" currentPage="${page.pageNum}"></div>

	</div>
</div>

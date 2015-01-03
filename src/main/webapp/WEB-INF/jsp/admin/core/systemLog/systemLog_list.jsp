<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="systemLog"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>模块：</label>
				<select name="module">
					<option>
						<c:forEach items="${logModules }" var="m">
							<option value="${m.remark }" ${systemLog.module eq m.remark ? 'selected' : ''}>${m.remark }</option>
						</c:forEach>
					</option>
				</select>
			</li>
			<li>
				<label>操作：</label>
				<input type="text" name="operation" value="${systemLog.operation}"/>
			</li>
			<li>
				<label>用户：</label>
				<input type="text" name="user.username" value="${systemLog.user.username}"/>
			</li>
		</ul>
		<ul class="searchContent">
			<li>
				<label>开始时间：</label>
				<input type="text"  class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" name="startDate" value="<fmt:formatDate value="${systemLog.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</li>
			<li>
				<label>结束时间：</label>
				<input type="text"  class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" name="endDate" value="<fmt:formatDate value="${systemLog.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</li>
			<li>
				<label>是否成功：</label> 
				<select name="isSuccess">
					<option selected value="">---</option>
					<option value="1" ${systemLog.isSuccess eq true ? 'selected' : '' }>成功</option>
					<option value="0" ${systemLog.isSuccess eq false ? 'selected' : '' }>失败</option>
				</select>
			</li>
		</ul>
	
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
			<%-- <sec:authorize ifAnyGranted="system.systemLog.add">
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="navTab" mask="true"><span>添加</span></a></li>
			</sec:authorize> --%>
			
			<sec:authorize ifAnyGranted="system.systemLog.delete">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li>
			</sec:authorize>
			
			<%-- <sec:authorize ifAnyGranted="system.systemLog.update">
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			</sec:authorize> --%>
			
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="162" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22px"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="50px">用户</th>
				<th align="center" width="100px">模块</th>
				<th align="center" width="100px">操作</th>
				<th align="center" width="200px">参数</th>
				<th align="center" width="120px">时间</th>
				<th align="center" width="50px">url</th>
				<th align="center" width="60px">IP</th>
				<th align="center" width="60px">是否成功</th>
				<th align="center" width="100px">错误信息</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${systemLogs }" var="n">
			<tr target="sid_user" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
				<td>${n.user.username }</td>
				<td>${n.module }</td>
				<td>${n.operation }</td>
				<%-- <td width="100px;">${n.params }</td> --%>
				<td><a href="http://localhost:8080/dw/admin/index#96" target="ajax">查看</a></td>
				<td><fmt:formatDate value="${n.date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${n.url }</td>
				<td>${n.ip }</td>
				<td>${n.isSuccess ? '是' : '否' }</td>
				<td>${n.errorMessage }</td>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="manager"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>用户名：</label>
				<input type="text" name="username" value="${manager.username}"/>
			</li>
			<li>
				<label>用户类型：</label>
				<select name="type">
					<option value="">---</option>
					<c:forEach items="${typeList }" var="tl">
						<option value="${tl.value }" <c:if test="${manager.type eq tl.value }">selected="true"</c:if>>${tl.remark }</option>
					</c:forEach>
				</select>
			</li>
			<li>
				<label>联系方式：</label>
				<input type="text" name="telephone" value="${manager.telephone}"/>
			</li>
		</ul>
		<ul class="searchContent">
			<li>
				<label>邮箱：</label>
				<input type="text" name="email" value="${manager.email}"/>
			</li>
			<li>
				<label>商家名称：</label>
				<input type="text" name="storeName" value="${manager.store.name}"/>
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
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}&type=${manager.type}" target="navTab" mask="true"><span>添加</span></a></li>
			
			<%-- <li><a title="确实要禁用这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/disable?navTabId=${param.navTabId}" class="delete"><span>禁用</span></a></li>
			<li><a title="确实要解禁这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/active?navTabId=${param.navTabId}" class="delete"><span>解禁</span></a></li> --%>
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			
			<li><a class="edit" href="${ctx}/admin/${module}/detailRole?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>角色查看及修改</span></a></li>
			
			<li><a class="edit" href="${ctx}/admin/${module}/resetPassword?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>重置密码</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="100">用户名</th>
				<th align="center" width="100">商家名称</th>
				<th align="center" width="100">用户类型</th>
				<th align="center" width="100">联系方式</th>
				<th align="center" width="100">邮箱</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${managers }" var="n">
			<tr target="sid_user" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
				<td>${n.username }</td>
				<td>${n.store.name }</td>
				<td>
					<c:forEach  items="${typeList }" var="tl">
						<c:if test="${tl.value eq n.type }">
							${tl.remark }
						</c:if>						
					</c:forEach>
				</td>
				<td>${n.telephone }</td>
				<td>${n.email }</td>
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

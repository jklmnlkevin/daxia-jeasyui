<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="user"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="onlyFindType" value="${user.onlyFindType }"/>
</form>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}" method="post">
	<input type="hidden" name="onlyFindType" value="${user.onlyFindType }"/>
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>用户名：</label>
				<input type="text" name="username" value="${user.username}"/>	
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
			<sec:authorize ifAnyGranted="user.add">
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="navTab" mask="true"><span>添加</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="user.delete">
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="user.update">
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			</sec:authorize>
			
			<li><a class="edit" href="${ctx}/admin/${module}/detailRole?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>角色查看及修改</span></a></li>
			
			<li><a class="edit" href="${ctx}/admin/${module}/resetPassword?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>重置密码</span></a></li>
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th  align="left"  width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th align="center" width="100">小区</th>
				<th align="center" width="100">用户类型</th>
				<th align="center" width="100">用户名</th>
				<th align="center" width="100">电话</th>
				<c:if test="${user.onlyFindType eq 0}"> <!-- 普通用户显示更多字段 -->
					<th align="center" width="100">身份证</th>
					<th align="center" width="100">经度</th>
					<th align="center" width="100">纬度</th>
					<th align="center" width="100">楼号</th>
					<th align="center" width="100">门牌号</th>
					<th align="center" width="100">业主</th>
					<th align="center" width="100">业主身份证号</th>
					<!-- <th align="center" width="100">Push ID</th> -->
					<th align="center" width="100">是否认证</th>
					<th align="center" width="100">是否接收通知</th>
				</c:if>
				<th align="center" width="100">省份</th>
				<th align="center" width="100">城市</th>
				<th align="center" width="100">地区</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users }" var="n">
			<tr target="sid_user" rel="${n.id }">
				<td><input name="ids" value="${n.id }" type="checkbox"></td>
				<td></td>
				<td>
				<c:forEach items="${userTypes }" var="t">
					<c:if test="${n.userType eq t.value }">${t.remark }</c:if>
				</c:forEach>
				</td>
				<td>${n.username}</td>
				<td>${n.telephone}</td>
				<c:if test="${user.onlyFindType eq 0}"> <!-- 普通用户显示更多字段 -->
					<td>${n.idCard}</td>
					<td>${n.longitude}</td>
					<td>${n.latitude}</td>
					<td>${n.building}</td>
					<td>${n.doorplate}</td>
					<td>${n.owner}</td>
					<td>${n.ownerIdCard}</td>
					<%-- <td>${n.pushId }</td> --%>
					<td>${n.authenticated ? '是' : '否' }</td>
					<td>${n.receiveNotice ? '是' : '否' }</td>
				</c:if>
				<td>${n.province.name}</td>
				<td>${n.city.name}</td>
				<td>${n.district.name}</td>
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

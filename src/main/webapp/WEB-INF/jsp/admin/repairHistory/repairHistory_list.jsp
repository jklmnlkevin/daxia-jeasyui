<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="repairHistory"/>

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
		
	</div>
	</form>
</div>

<div class="pageContent">
	<div class="panelBar" style="padding: 10xp; font-size: 15px; font-weight: bold">
		${repair.title }
	</div>
	<table class="table" width="100%" layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th align="center" width="100">状态</th>
				<th align="center" width="100">更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="n">
			<tr target="sid_user" rel="${n.id }">
				<td>
				<c:forEach items="${repairStatus}" var="s">
					<c:if test="${s.value eq n.state }">${s.remark }</c:if>
				</c:forEach>
				</td>
				<td>
				<fmt:formatDate value="${n.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>

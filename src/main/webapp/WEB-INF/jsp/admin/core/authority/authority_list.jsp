<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="module" value="authority"/>

<form id="pagerForm" method="post" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}">
	<%-- <input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="${page.pageNum}" />
	<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" /> --%>
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${ctx }/admin/${module}/list?navTabId=${param.navTabId}" method="post">
	<div class="searchBar">
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
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="ajax" rel="${module }_jbsxBox" mask="true"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}" class="delete"><span>删除</span></a></li>
			
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	
	<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
			<ul class="tree treeFolder collapse">
				<c:forEach items="${authorities }" var="a">
				    	<c:if test="${empty a.parentAuthority }">
				    	<!-- 第一级的权限 -->
						<li class="menu_tree" mid="${a.id }" mname="${a.name }"><a href="${ctx }/admin/${module }/detail?id=${a.id }"  target="ajax" rel="${module }_jbsxBox">${a.name }</a>
							<ul>
								<c:forEach items="${a.children }" var="c">
								<li class="menu_tree" mid="${c.id }" mname="${c.name }"><a href="${ctx }/admin/${module }/detail?id=${c.id }" target="ajax" rel="${module }_jbsxBox">${c.name }</a>
									<ul>
										<c:forEach items="${c.children }" var="coc">
										<li class="menu_tree" mid="${coc.id }" mname="${coc.name }"><a href="${ctx }/admin/${module }/detail?id=${coc.id }" target="ajax" rel="${module }_jbsxBox">${coc.name }</a>
										</li>
										</c:forEach>	
									</ul>		
								</li>
								</c:forEach>
							</ul>
						</li>
						</c:if>
						</c:forEach>
				     </ul>
				</div>	
				
				<div id="${module }_jbsxBox" class="unitBox" style="margin-left:246px;">
				
				</div>
	
</div>


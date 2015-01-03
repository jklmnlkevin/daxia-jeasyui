<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="module" value="menu"/>

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
			<sec:authorize ifAnyGranted="system.menu.update">
			<li><a class="add" href="${ctx}/admin/${module}/detail?navTabId=${param.navTabId}" target="ajax" rel="${module}_jbsxBox" mask="true"><span>添加</span></a></li>
			</sec:authorize>
			
			<sec:authorize ifAnyGranted="system.menu.update">
			<li><a class="edit" href="${ctx}/admin/${module}/detail?id={sid_user}&navTabId=${param.navTabId}" target="navTab" mask="true" warn="请选择要修改的记录"><span>修改</span></a></li>
			</sec:authorize>
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
	    <ul class="tree treeFolder collapse">
	    	<c:forEach items="${menus }" var="m">
	    	<c:if test="${m.level eq 1 }">
			<li class="menu_tree" mid="${m.id }" mname="${m.name }"><a href="${ctx }/admin/menu/detail?id=${m.id }"  target="ajax" rel="${module }_jbsxBox">${m.name }</a>
				<ul>
					<c:forEach items="${m.children }" var="subMenu">
						<li class="menu_tree" mid="${subMenu.id }" mname="${subMenu.name }">
							<a href="${ctx }/admin/menu/detail?id=${subMenu.id }" target="ajax"  rel="${module }_jbsxBox">
								${subMenu.name }
							</a>
						</li>
					</c:forEach>
				</ul>
			</li>
			</c:if>
			</c:forEach>
	     </ul>
	</div>	
	
	<div id="${module }_jbsxBox" class="unitBox" style="margin-left:246px;">
		<!--#include virtual="list1.html" -->
	</div>
</div>

<script>
$(".menu_tree").contextMenu('menuCM', {
	bindings:{
		deleteCurrent:function(t){
				console.log("delteCurrent, t = " + t.attr('mname'));
	            // TODO
				alertMsg.confirm("您修改的资料未保存，请选择保存或取消！", {
	                okCall: function(){
	                             alert('那我真删了');
	                }
			});
		},
	
	    closeOther:function(t){
		},
	
	    closeAll:function(t){
	                // TODO
	    }
    },
    ctrSub:function(t,m){
          var mCur = m.find("[rel='deleteCurrent']");
          var mOther = m.find("[rel='closeOther']");
          var mAll = m.find("[rel='closeAll']");
          // TODO
    }
});
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="menu"/>

<div class="pageContent">
<div style="width:98%;margin:5px;border:1px solid #ccc;">
	<a style="display: none;" id="menu_delete" title="确认删除？" target="ajaxTodo" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}&ids=${menu.id}"><span>删除</span></a>
	<form method="post" action="${ctx }/admin/${module }/save?id=${menu.id}&navTabId=${param.navTabId }" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="128">
			<p>
				<label>菜单级别：</label>
				<select class="combox" name="level" id="search_level" ref="menu_detail_name" refUrl="${ctx}/admin/menu/findParent?level={value}">
					<option value="" >--请选择--</option>
					<option value="1">一级菜单</option>
					<option value="2">二级菜单</option>
				</select>
			</p>
			<p>
				<label>父级菜单：</label>
				<select class="required" name="parentId" id="menu_detail_name">
					<c:forEach items="${parentLevelMenus }" var="p">
						<option value="${p.id }" ${p.id eq menu.parent.id ? 'selected' : '' }>${p.name }</option>
      				</c:forEach>
				</select>
			</p>
			<p>
				<label>名称：</label>
				<input name="name" class="required" type="text" size="30" value="${menu.name }" alt="请输入菜单名称"/>
			</p>
			<p>
				<label>链接：</label>
				<input name="href" class="required" type="text" size="30" value="${menu.href }" alt="请输入菜单链接"/>
			</p>
			<p>
				<label>权限：</label>
				<input bringBackName="authority.id" name="authority.id" type="hidden" size="30" value="${menu.authority.id }"/>
				<input bringBackName="authority.name" name="authority.name" type="text" postField="name" suggestFields="authority" size="30" 
					suggestUrl="${ctx}/admin/authority/searchAuthority" lookupGroup="authority" value="${menu.authority.name }"/>
			</p>
			<p>
				<label>是否显示：</label>
				<select name="show">
					<option value="true" ${menu.show? 'selected' : '' }>是</option>
					<option value="false" ${menu.show? '' : 'selected'}>否</option>
				</select>
			</p>
			<p>
			
			</p>
		</div>
		<div class="formBar">
		<ul>
		<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<c:if test="${empty(menu.id) }">
					<sec:authorize ifAnyGranted="system.menu.add">
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					</sec:authorize>
				</c:if>
				<c:if test="${not empty(menu.id) }">
					<sec:authorize ifAnyGranted="system.menu.update">
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
					</sec:authorize>
				</c:if>
				<sec:authorize ifAnyGranted="system.menu.delete">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button onclick="$('#menu_delete').trigger('click');" type="button">删除</button>
						</div>
					</div>
				</li>
				</sec:authorize>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>	
</div>
<script>
	$('#search_level').val('${menu.level}');
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="module" value="authority"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${authority.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="120">
		<p>
				<label>父级权限：</label>
				<!-- <input name="parentAuthority.name" id="parentAuthorityId"> -->
				<input type="hidden" bringBackName="parentAuthority.id" name="parentAuthority.id" value="${authority.parentAuthority.id }" />
				<input bringBackName="parentAuthority.name" name="parentAuthority.name" type="text" postField="name" suggestFields="name" 
					suggestUrl="${ctx}/admin/authority/searchAuthority" lookupGroup="parentAuthority" value="${authority.parentAuthority.name }"/>
			</p>
			<p>
				<label>权限代码：</label>
				<input name="code" type="text"  value="${authority.code }"/>
			</p>
			<p>
				<label>权限名称：</label>
				<input name="name" type="text"  value="${authority.simpleName }" alt="请输入菜单名称"/>
			</p>
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script>
/* $("#parentAuthorityId").autocomplete({
    source: function(request, response) {
   	  $.ajax({
             url: "${ctx}/admin/authority/searchAuthority",
             dataType: "json",
             type:"post",
             data: {
           	  name: request.term
             },
             success: function(data) {
           	  response(data);
             }
         });
     }
   }); */
</script>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="role"/>

<div class="pageContent">
	<a style="display: none;" id="role_delete" title="确认删除？" target="selectedTodo" rel="ids" href="${ctx}/admin/${module}/delete?navTabId=${param.navTabId}"><span>删除</span></a>
	<form method="post" action="${ctx }/admin/${module }/save?id=${role.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="checkValue(); return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="85">
			<input type="hidden" name="authorityIds" />
			<table>
				<tr>
					<td>角色名:</td>
					<td><input type="text" name="name" value="${role.name }" size="30" /></td>
				</tr>
				<tr>
					<td>权限:</td>
					<td>
						<div class="zTreeDemoBackground left"  style="display:block;">
							<ul id="treeDemo" class="ztree">
							
							</ul>
						</div>		
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
							</div>
						</div>
				</li>
				
				<sec:authorize ifAnyGranted="system.authority.delete">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button onclick="$('#role_delete').trigger('click');" type="button">删除</button>
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
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		var zNodes = new Array();
		<c:if test="${not empty(authorities) }">
			<c:forEach items="${authorities }" var="a">
				zNodes.push({id:${a.id}, pId:0, name:"${a.simpleName}", open:false, checked:"${a.contains ? 'true' : 'false'}"});
				<c:if test="${not empty(a.children) }">
					<c:forEach items="${a.children }" var="c1">
					zNodes.push({id:${c1.id}, pId:${c1.parentAuthority.id}, name:"${c1.simpleName}", open:false, checked:"${c1.contains ? 'true' : 'false'}"});	
						<c:if test="${not empty(c1.children) }">
							<c:forEach items="${c1.children }" var="c2">
							zNodes.push({id:${c2.id}, pId:${c2.parentAuthority.id}, name:"${c2.simpleName}", open:false, checked:"${c2.contains ? 'true' : 'false'}"});
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</c:if>
		function checkValue() {
			/* for (var i = 0; i < zNodes.length; i++) {
				var li = zNodes[i];
				
				if (li.checked == true) {
					alert(li.name);
				} else {
					alert(li.checked);
				}
			} */
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = zTree.getCheckedNodes(true);
			
			var ids = new Array();
			for (var i = 0; i < nodes.length; i++) {
				var li = nodes[i];
				ids.push(li.id);
			}
			
			$('input[name=authorityIds]').val(ids);
		}
		
		
		/* var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:false},
			{ id:11, pId:1, name:"随意勾选 1-1", open:false},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:false},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:false},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2", open:false},
			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"}
		]; */

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//-->
</SCRIPT>
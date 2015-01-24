<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="module" value="menu" />


<form id="menu_detail_ff" method="post" class="k-ff easyui-form" data-options="novalidate:false">
	<table cellpadding="5" class="k-ff-table">
		<tr>
			<td><label>菜单级别：</label></td>
			<td><select class="easyui-combobox validatebox" name="level"
				id="search_level" data-options="required:true">
					<option value="">--请选择--</option>
					<option value="1" ${menu.level eq 1 ? 'selected' : '' }>一级菜单</option>
					<option value="2" ${menu.level eq 2 ? 'selected' : '' }>二级菜单</option>
			</select></td>
			<td><label>父级菜单：</label></td>
			<td><select class="easyui-combobox" name="parentId"
				id="menu_parentId" data-options="required:true">
					<c:forEach items="${parentLevelMenus }" var="p">
						<option value="${p.id }"
							${p.id eq menu.parent.id ? 'selected' : '' }>${p.name }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><label>名称：</label></td>
			<td><input name="name" class="easyui-textbox" type="text"
				size="30" value="${menu.name }" alt="请输入菜单名称"  data-options="required:true"/></td>
			<td><label>链接：</label></td>
			<td><input name="href" class="easyui-textbox" type="text"
				size="30" value="${menu.href }" alt="请输入菜单链接"  data-options="required:true"/></td>
		</tr>
		<tr>
			<td><label>权限：</label></td>
			<td>
			<select name="authority.id" class="easyui-combobox" 
				id="menu_authority" data-options="required:true">
			</select>
			</td>

			<td><label>是否显示：</label></td>
			<td><select name="show" class="easyui-combobox">
					<option value="true" ${menu.show? 'selected' : '' }>是</option>
					<option value="false" ${menu.show? '' : 'selected'}>否</option>
			</select data-options="required:true"></td>
		</tr>
	</table>
	<div style="text-align: right;">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'" onclick="submitForm();">保存</a>
			
			<c:if test="${not empty(menu.id)}">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove'" onclick="deleteMenu();">删除</a>
			</c:if>	
	</div>
</form>

<script>

	$('#menu_detail_ff').form({
		url : "${ctx}/admin/menu/save?id=${menu.id}",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			data = eval("(" + data + ")");
			if (data.statusCode == 200) {
				operateSuccess();
			} else {
				alert2(data.message);
			}
		}
	});

	
	function submitForm() {
		$('#menu_detail_ff').submit();
	}

	function refreshParentMenu(leval) {
		$('#menu_parentId').combobox({
			url : '${ctx}/admin/menu/findParent?level=' + leval,
			valueField : 'id',
			textField : 'name',
			width: '150px'
		});
	}
	
	refreshParentMenu("${menu.level}");
	
	$('#menu_authority').combobox({
		url : '${ctx}/admin/authority/searchAuthority',
		valueField : 'id',
		textField : 'name',
		value: '${menu.authority.id}',
		width: '150px'
	});
	
	<c:if test="${not empty(menu.id)}">
	function deleteMenu() {
		$.messager.confirm('确认对话框', '确认要删除该菜单吗？', function(r){
			if (r){
				$.ajax({
					url: "${ctx}/admin/menu/delete?ids=" + ${menu.id},
					dataType: "json",
					success: function(data) {
						if (data.statusCode == 200) {
							operateSuccess();
						//	var tab = $('#tt').tabs('getSelected');
						//	tab.panel("refresh");
							var node = $('#menu_tt').tree('getSelected');
							$('#menu_tt').tree('remove', node.target);
							$('#menu_detail_p').panel('clear');
						}
					}
				});
			}
		});
	}
	</c:if>
	
	$('#search_level').combobox({
		valueField: 'id',
		textField: 'name',
		data: [
			{name: '一级菜单', id:1},
			{name: '二级菜单', id:2}
		],
		onSelect: function(record) {
			$('#menu_parentId').combobox('reload', '${ctx}/admin/menu/findParent?level=' + record.id);
		}
	});
</script>

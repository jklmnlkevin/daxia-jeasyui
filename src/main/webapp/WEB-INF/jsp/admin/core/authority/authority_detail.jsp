<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="module" value="authority" />


<form id="authority_detail_ff" method="post" class="k-ff easyui-form" data-options="novalidate:false">
	<table cellpadding="5" class="k-ff-table">
		<tr>
			<td><label>上级权限：</label></td>
			<td><select class="easyui-combobox " name="parentAuthority.id"
				id="search_authority" value="${authority.parentAuthority.id }" >
			</select></td>
			<td><label>权限代码：</label></td>
			<td><input name="code" class="easyui-textbox validatebox"  type="text"
				value="${authority.code }"  size="30" data-options="required:true"/></td>
		</tr>
		<tr>
			<td><label>名称：</label></td>
			<td><input name="name" class="easyui-textbox" type="text"
				value="${authority.name }" size="30" data-options="required:true"/></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<div style="text-align: right;">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'" onclick="submitForm();">保存</a>
			
			<c:if test="${not empty(authority.id)}">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove'" onclick="deleteauthority();">删除</a>
			</c:if>	
	</div>
</form>

<script>

	$('#authority_detail_ff').form({
		url : "${ctx}/admin/authority/save?id=${authority.id}",
		onSubmit : function() {
			return $(this).form('enableValidation').form('validate');
		},
		success : function(data) {
			k_success(data);
		}
	});

	
	function submitForm() {
		$('#authority_detail_ff').submit();
	}

	function refreshParentauthority(leval) {
		$('#authority_parentId').combobox({
			url : '${ctx}/admin/authority/findParent?level=' + leval,
			valueField : 'id',
			textField : 'name',
			width: '150px'
		});
	}
	
	refreshParentauthority("${authority.code}");
	
	$('#search_authority').combobox({
		url : '${ctx}/admin/authority/searchAuthority',
		valueField : 'id',
		textField : 'name',
		value: '${authority.parentAuthority.id}',
		width: '150px'
	});
	
	<c:if test="${not empty(authority.id)}">
	function deleteauthority() {
		$.messager.confirm('确认对话框', '确认要删除该菜单吗？', function(r){
			if (r){
				$.ajax({
					url: "${ctx}/admin/authority/delete?ids=" + ${authority.id},
					dataType: "json",
					success: function(data) {
						if (data.statusCode == 200) {
							operateSuccess();
							var node = $('#authority_tree').tree('getSelected');
							$('#authority_tree').tree('remove', node.target);
							$('#authority_detail_p').panel('clear');
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
			$('#authority_parentId').combobox('reload', '${ctx}/admin/authority/findParent?level=' + record.id);
		}
	}); 
</script>

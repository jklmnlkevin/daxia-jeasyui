<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="user"/>

<div class="tab_div">
			<div class="tab_div_div">
				<form id="user_search_form">
	               
				用户名：
				<input type="text" name="username" value="${query.username}" class="easyui-textbox"/>	
			</li>
				电话：
				<input type="text" name="mobile" value="${query.mobile}" class="easyui-textbox"/>	
			</li>
				密码：
				<input type="text" name="password" value="${query.password}" class="easyui-textbox"/>	
			</li>
				<br>
				用户类型：
				<input type="text" name="userType" value="${query.userType}" class="easyui-textbox"/>	
			</li>
				用户头像：
				<input type="text" name="headImage" value="${query.headImage}" class="easyui-textbox"/>	
			</li>
				性别：
				<input type="text" name="sex" value="${query.sex}" class="easyui-textbox"/>	
			</li>
	                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="user_search_form()">查找</a>
                </form>
            </div>
            
            <table id="user_datagrid">
            </table>
</div>            

<script>
var user_toolbar = new Array();
// 新增按钮
<sec:authorize ifAnyGranted="user.add">
user_toolbar.push(k_toolbar_add('user'));
user_toolbar.push('-');
</sec:authorize>

//删除按钮
<sec:authorize ifAnyGranted="user.delete">
user_toolbar.push(k_toolbar_delete('user'));
user_toolbar.push('-');
</sec:authorize>

<sec:authorize ifAnyGranted="user.update">
// 修改按钮
user_toolbar.push({
    text:'修改',
    iconCls:'icon-edit',
    handler:function(){
    	var ids = k_getSelectedIds('user');
    	console.log('ids: ' + ids);
    	if (!!!ids || ids == '') {
    		alert2('请选择要修改的记录');
    		return;
    	} else if (ids.indexOf(',') != -1) {
    		alert2('只能选择一条记录进行修改');
    		return;
    	}
    	
    	$('#dd').dialog({
            title: '新增记录',
            width: 600,
            height: 300,
            closed: false,
            cache: false,
            href: '${ctx}/admin/user/detail?id=' + ids,
            modal: true,
            buttons: [{
                text:'保存',
                iconCls:'icon-ok',
                handler:function(){
                    $('#user_detail_form').form('submit');
                }
            },{
                text:'取消',
                handler:function(){
                    $('#dd').dialog('close');
                }
            }]

        });
    }
});
</sec:authorize>

// 初始化 datagrid
function init() {
	$('#user_datagrid').datagrid({
	    url:'${ctx}/admin/user/datagrid?1=1&',
	    method: "POST",
	    queryParams: {
			title: $('#title').val()
		},
	    columns:[[ // field是与json字段对应的，title是显示在表头的
	        {field:'ck', checkbox:'true'},
	        {field:'id',title:'ID',width:50},
		        {field:'username',title:'用户名',width:100,align:'center'},
		        {field:'mobile',title:'电话',width:100,align:'center'},
		        {field:'password',title:'密码',width:100,align:'center'},
		        {field:'userType',title:'用户类型',width:100,align:'center'},
		        {field:'headImage',title:'用户头像',width:100,align:'center'},
		        {field:'sex',title:'性别',width:100,align:'center'}
	    ]],
	    toolbar: user_toolbar,
	    rownumbers: false,
	    pagination: true,
	    onLoadError: function() {},
	    onLoadSuccess: function(data) {}, 
	    onBeforeRender:function(target, rows) {alert('r	')},
	    loadFilter: function(data){
			if (data.statusCode && data.message){
				k_success(data);
			} else {
				return data;
			}
		}
	});
}

// 初始化datagrid
init();

/*
function getSelectedId() {
	var arr = $('#user_datagrid').datagrid("getSelections");
	if (arr && arr[0]) {
		return arr[0].id;
	} else {
		return null;
	}
} */



function user_search_form() {
	var form = $('#user_search_form');
	console.log(form);
	var queryString = form.serialize();
	console.log('queryString: ' + queryString);
	
	init(queryString); 
}
</script>
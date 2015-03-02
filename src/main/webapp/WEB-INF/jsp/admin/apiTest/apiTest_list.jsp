<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="apiTest"/>

<div class="tab_div">
			<div class="tab_div_div">
				<form id="apiTest_search_form">
	               
	                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="apiTest_search_form()">查找</a>
                </form>
            </div>
            
            <table id="apiTest_datagrid">
            </table>
</div>            

<script>
var apiTest_toolbar = new Array();
// 新增按钮
<sec:authorize ifAnyGranted="apiTest.add">
apiTest_toolbar.push(k_toolbar_add('apiTest'));
apiTest_toolbar.push('-');
</sec:authorize>

//删除按钮
<sec:authorize ifAnyGranted="apiTest.delete">
apiTest_toolbar.push(k_toolbar_delete('apiTest'));
apiTest_toolbar.push('-');
</sec:authorize>

<sec:authorize ifAnyGranted="apiTest.update">
// 修改按钮
apiTest_toolbar.push({
    text:'修改',
    iconCls:'icon-edit',
    handler:function(){
    	var ids = k_getSelectedIds('apiTest');
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
            href: '${ctx}/admin/apiTest/detail?id=' + ids,
            modal: true,
            buttons: [{
                text:'保存',
                iconCls:'icon-ok',
                handler:function(){
                    $('#apiTest_detail_form').form('submit');
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
	$('#apiTest_datagrid').datagrid({
	    url:'${ctx}/admin/apiTest/datagrid?1=1&',
	    method: "POST",
	    queryParams: {
			title: $('#title').val()
		},
	    columns:[[ // field是与json字段对应的，title是显示在表头的
	        {field:'ck', checkbox:'true'},
	        {field:'id',title:'ID',width:50},
		        {field:'module',title:'模块',width:100,align:'center'},
		        {field:'name',title:'api名称',width:100, align:'center'},
		        {field:'url',title:'url',width:200,align:'center'},
		        {field:'description',title:'描述',width:400,align:'center'}
	    ]],
	    toolbar: apiTest_toolbar,
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
	var arr = $('#apiTest_datagrid').datagrid("getSelections");
	if (arr && arr[0]) {
		return arr[0].id;
	} else {
		return null;
	}
} */



function apiTest_search_form() {
	var form = $('#apiTest_search_form');
	console.log(form);
	var queryString = form.serialize();
	console.log('queryString: ' + queryString);
	
	init(queryString); 
}
</script>
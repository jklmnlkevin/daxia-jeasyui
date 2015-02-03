<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="notice"/>

<div class="tab_div">
			<div class="tab_div_div">
				<form id="notice_search_form">
	                标题: <input name="title" id="title" class="easyui-textbox">
	                标题: <input name="title" id="title" class="easyui-textbox">
	                标题: <input name="title" id="title" class="easyui-textbox">
	                标题: <input name="title" id="title" class="easyui-textbox"><br/>
	                标题: <input name="title" id="title" class="easyui-textbox">
	                标题: <input name="title" id="title" class="easyui-textbox">
	                标题: <input name="title" id="title" class="easyui-textbox">
	                标题: <input name="title" id="title" class="easyui-textbox">
	                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="notice_search_form()">查找</a>
                </form>
            </div>
            
            <table id="notice_datagrid">
            </table>
</div>            

<script>
var notice_toolbar = new Array();
// 新增按钮
notice_toolbar.push(k_toolbar_add('notice'));
notice_toolbar.push('-');

//删除按钮
notice_toolbar.push(k_toolbar_delete('notice'));
notice_toolbar.push('-');

// 修改按钮
notice_toolbar.push({
    text:'修改',
    iconCls:'icon-edit',
    handler:function(){
    	var ids = k_getSelectedIds('notice');
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
            href: '${ctx}/admin/notice/detail?id=' + ids,
            modal: true,
            buttons: [{
                text:'保存',
                iconCls:'icon-ok',
                handler:function(){
                	alert('hi');
                    $('#notice_detail_form').form('submit');
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


// 初始化 datagrid
function init() {
	$('#notice_datagrid').datagrid({
	    url:'${ctx }/admin/notice/datagrid?1=1&',
	    method: "POST",
	    queryParams: {
			title: $('#title').val()
		},
	    columns:[[ // field是与json字段对应的，title是显示在表头的
	        {field:'ck', checkbox:'true'},
	        {field:'id',title:'ID',width:100},
	        {field:'title',title:'标题',width:100},
	        {field:'price',title:'价格',width:100,align:'right'}
	    ]],
	    toolbar: notice_toolbar,
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
	var arr = $('#notice_datagrid').datagrid("getSelections");
	if (arr && arr[0]) {
		return arr[0].id;
	} else {
		return null;
	}
} */



function notice_search_form() {
	var form = $('#notice_search_form');
	console.log(form);
	var queryString = form.serialize();
	console.log('queryString: ' + queryString);
	
	init(queryString); 
}
</script>
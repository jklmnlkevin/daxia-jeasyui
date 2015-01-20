<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="community"/>

<div class="tab_div">
			<div class="tab_div_div">
				<form id="community_search_form">
                标题: <input name="title" id="title">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="search_form()">查找</a>
                </form>
            </div>
            <table id="community_dg">
                <!-- <thead>
                <tr>
                    <th data-options="field:'id'">Item ID</th>
                    <th data-options="field:'title'">Product</th>
                    <th data-options="field:'content',align:'right'">List Price</th>
                    <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                    <th data-options="field:'attr1',width:240">Attribute</th>
                    <th data-options="field:'status',width:60,align:'center'">Status</th>
                </tr> 
                </thead> -->
            </table>
</div>            

<script>
var community_toolbar = new Array();
community_toolbar.push(toolbar_add('${ctx}/admin/community/detail'));

community_toolbar.push(toolbar_delete('community'));
community_toolbar.push('-');
community_toolbar.push({
    text:'修改',
    iconCls:'icon-edit',
    handler:function(){
    	var ids = getSelectedIds();
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
            href: '${ctx}/admin/community/detail?id=' + ids,
            modal: true,
            buttons: [{
                text:'保存',
                iconCls:'icon-ok',
                handler:function(){
                    $('#ff').form('submit');
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


function init(params) {
	$('#community_dg').datagrid({
	    url:'${ctx }/admin/community/datagrid?1=1&',
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
	    toolbar: community_toolbar,
	    rownumbers: true,
	    pagination: true
	});
}

init();

function getSelectedId() {
	var arr = $('#community_dg').datagrid("getSelections");
	if (arr && arr[0]) {
		return arr[0].id;
	} else {
		return null;
	}
}



function search_form() {
	var form = $('#community_search_form');
	console.log(form);
	var queryString = form.serialize();
	console.log('queryString: ' + queryString);
	
	init(queryString); 
}
</script>
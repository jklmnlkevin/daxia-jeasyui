<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="role"/>

<div class="tab_div">
<div class="easyui-panel" title="角色管理"
	style="width: 900px; height:450px; padding: 10px;">

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true"
			style="width: 440px; padding: 10px; height: 200px;">
			<div class="tab_div_div">
				
            </div>
            <table id="role_dg">
            </table>
         </div>
         
         <div data-options="region:'center'" style="padding: 10px">
			
			<div id="role_detail_p" class="easyui-panel" title="权限"    
			        style="width:400px;padding:10px;height: 440px; background:#fafafa;"  
			        data-options="closable:false,   
			                collapsible:false,minimizable:false,maximizable:false">  
			                
			</div>  
			
		</div>
     </div>       
     </div>       
            
</div>            

<script>

var role_toolbar = new Array();
role_toolbar.push({
    text:'新增',
    iconCls:'icon-add',
    handler:function(){
    	$('#role_detail_p').panel({   
		    href:'${ctx}/admin/role/detail', 
		    onLoad:function(){   
		    }   
		});  
    }
});

role_toolbar.push(toolbar_delete('role'));
role_toolbar.push('-');


function init(params) {
	$('#role_dg').datagrid({
	    url:'${ctx }/admin/role/datagrid?1=1&',
	    method: "POST",
	    queryParams: {
			title: $('#title').val()
		},
	    columns:[[ // field是与json字段对应的，title是显示在表头的
	        {field:'ck', checkbox:'true'},
	        {field:'id',title:'ID',width:100},
	        {field:'name',title:'角色',width:100}
	    ]],
	    toolbar: role_toolbar,
	    rownumbers: false,
	    pagination: true,
	    width:400,
	    singleSelect: true,
	    onSelect: function(rowIndex, rowData) {
	    	$('#role_detail_p').panel({   
			    href:'${ctx}/admin/role/detail?id=' + rowData.id,   
			    onLoad:function(){   
			    }   
			});  
	    }
	    
	    
	});
	
}

init();

function getSelectedId() {
	var arr = $('#role_dg').datagrid("getSelections");
	if (arr && arr[0]) {
		return arr[0].id;
	} else {
		return null;
	}
}



function search_form() {
	var form = $('#role_search_form');
	console.log(form);
	var queryString = form.serialize();
	console.log('queryString: ' + queryString);
	
	init(queryString); 
}



$('#role_detail_p').panel({   
	  width:400,   
	  height:440,   
	  title: '权限',   
	  tools: [{   
	    iconCls:'icon-save',   
	    handler:function(){
	    	var nodes = $('#role_detail_tree').tree('getChecked');
	    	var ids = new Array();
	    	for (var i = 0; i < nodes.length; i++) {
	    		ids.push(nodes[i].id);
	    	}
	    	$.ajax({
	    		url: "${ctx}/admin/role/save",
	    		method: "POST",
	    		data: {
	    			id: $('#role_id').val(),
	    			name: $('#role_name').val(),
	    			authorityIds: ids.join(',')
	    		},
	    		dataType: "json",
	    		success: function(data) {
	    			k_success(data);
	    			$('#role_dg').datagrid('reload');
	    		}
	    	});
	    }   
	  }]   
	}); 
</script>
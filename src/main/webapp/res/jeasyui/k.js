var ctx = "http://localhost:8080/jeasyui-maven";

function k_getTabOptions(title, href) {
	return {
        // title: '学生管理'+index,
        title: title,
        content: '',
        href: href,
        closable: true,
        tools:[{
            iconCls:'icon-reload',
            handler: function() {
            	k_openTab(title, href);
            }
        }]
	};
}

function k_openTab(title, href) {
	console.log(title + ", " + href);
    var tab = $('#tt').tabs('getTab', title);
    if (!tab) {
        $('#tt').tabs('add', k_getTabOptions(title, href));
    } else {
        $('#tt').tabs('select', title);
        $('#tt').tabs('update', {
        	tab: tab,
        	options: k_getTabOptions(title, href)
        });
    }
}

function navTabSearch(form) {
	return false;
}

$('ul.menu li').click(function() {
    k_openTab($(this).attr('title'));
});

function operateSuccess() {
    $.messager.show({
        title:'提示',
        msg:'操作成功',
        showType:'slide',
        timeout:1000,
        style:{
            right:'',
            top:document.body.scrollTop+document.documentElement.scrollTop,
            bottom:''
        }
    });
}

function alert2(msg) {
	 $.messager.alert('提示', msg, 'warning');
}

$.fn.datagrid.defaults.loadMsg = "大侠等等";

function toolbar_add(url) {
	return {
	    text:'新增',
	    iconCls:'icon-add',
	    handler:function(){
	        $('#dd').dialog({
	            title: '新增记录',
	            width: 600,
	            height: 300,
	            closed: false,
	            cache: false,
	            href: url,
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
	};
}

function toolbar_delete(module) {
	return {
	    text:'删除',
	    iconCls:'icon-cut',
	    handler:function(){
	    	var ids = getSelectedIds(module);
	    	if (!!!ids || ids == '') {
	    		alert2('请选择要删除的记录');
	    		return;
	    	} 
	    	
	    	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){   
	    	    if (r){   
	    	        $.ajax({
	    	        	url:ctx + "/admin/" + module + "/delete",
	    	        	data: {
	    	        		ids: ids
	    	        	},
	    	        	dataType: "json",
	    	        	success: function(data) {
	    	        		if (data) {
		    	        		if (data.statusCode == '200') {
			    	        		operateSuccess();
		    		        		$('#' + module + '_dg').datagrid('reload');	
		    	        		} else {
		    	        			alert2(data.message);
		    	        		}
	    	        		}
	    	        	},
	    	        	error: function(data) {
	    	        		$.messager.alert('通知','操作失败');
	    	        	}
	    	        });
	    	    }   
	    	});
	    	
	    }
	};
}

//split with ,
function getSelectedIds(module) {
	var arr = $('#' + module + '_dg').datagrid("getSelections");
	if (arr) {
		var idsArr = new Array();
		for (var i = 0; i < arr.length; i++) {
			idsArr.push(arr[i].id);
		}
		return idsArr.join(',');
	} else {
		return null;
	}
}

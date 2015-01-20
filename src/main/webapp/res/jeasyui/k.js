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


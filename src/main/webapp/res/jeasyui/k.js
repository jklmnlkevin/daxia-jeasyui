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

$.fn.datagrid.defaults.loadMsg = "大侠等等";
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Full Layout - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/res/jeasyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/jeasyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/res/jeasyui/k.css">

    <script type="text/javascript" src="${ctx}/res/jeasyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/res/jeasyui/locale/easyui-lang-zh_CN.js"></script>
    <style>
        .easyui-accordion ul
        {
            list-style-type: none;
            margin: 0px;
            padding: 5px;
        }
        .easyui-accordion ul li
        {
            padding: 0px;
        }
        .easyui-accordion ul li a
        {
            line-height: 24px;
            text-decoration: none;
        }
        .easyui-accordion ul li div
        {
            margin: 2px 0px;
            font-size: 12px;
            padding-top: 2px;
            text-align: center;
            border: 1px dashed #99BBE8;
            background: #E0ECFF;
        }
        .easyui-accordion ul li div:hover
        {
            border: 1px solid #99BBE8;
            background-color: #aac5e7;
            cursor: pointer;
        }
        .easyui-accordion ul li div:hover a
        {
            color: #416AA3;
        }
        .easyui-accordion ul li div .selected
        {
            border: 1px solid #99BBE8;
            background: #E0ECFF;
            cursor: default;
        }
        .easyui-accordion ul li div .selected a
        {
            color: #416AA3;
            font-weight: bold;
        }

    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="" id="north">
    <div class="k_right">
        欢迎 ${currentUser.username } | <a href="${ctx }/j_spring_security_logout">退出</a>
    </div>
</div>

    <div data-options="region:'west',split:true,title:'菜单'" style="width:150px;padding:0px;">
    <!-- menu begin -->
    <c:forEach items="${menus }" var="level0Menu">
    <div class="easyui-accordion" data-options="fit:true,border:false">
		<c:forEach items="${level0Menu.children }" var="level1Menu">
        <div title="${level1Menu.name }" style="overflow:auto; padding: 0px;" icon="icon-edit" data-options="selected: true">
            <div>
                <ul>
                	<c:forEach items="${level1Menu.children }" var="level2Menu">
                    <li>
                        <div onclick="k_openTab('${level2Menu.name }', '${ctx }/${level2Menu.href}')">
                            <a href="javascript:void(0);">${level2Menu.name }</a>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        	</c:forEach>
    </div>
        </c:forEach>
    <!-- menu end -->
</div>


<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>


<div data-options="region:'south',border:false" style="height:20px;background:#A9FACD;padding:0px;"></div>


<div data-options="region:'center',title:''">
    <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tt">
        <div title="About" data-options="closable:false, refresh:true" style="padding:10px">
		        <div id="p" class="easyui-panel" title="Panel Tools" style="width:600px;height:200px;padding:10px;margin-bottom:10px;"
		                data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true">
		            <p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
		            <ul>
		                <li>easyui is a collection of user-interface plugin based on jQuery.</li>
		                <li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
		                <li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
		                <li>complete framework for HTML5 web page.</li>
		                <li>easyui save your time and scales while developing your products.</li>
		                <li>easyui is very easy but powerful.</li>
		            </ul>
		        </div>
		        <div id="p" class="easyui-panel" title="Panel Tools" style="width:600px;height:200px;padding:10px;margin-bottom:10px;"
		                data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true">
		            <p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
		            <ul>
		                <li>easyui is a collection of user-interface plugin based on jQuery.</li>
		                <li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
		                <li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
		                <li>complete framework for HTML5 web page.</li>
		                <li>easyui save your time and scales while developing your products.</li>
		                <li>easyui is very easy but powerful.</li>
		            </ul>
		        </div>
		        <div class="easyui-panel" title="Panel Tools" style="width:600px;height:200px;padding:10px;margin-bottom:10px;"
		                data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true">
		            <p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
		            <ul>
		                <li>easyui is a collection of user-interface plugin based on jQuery.</li>
		                <li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
		                <li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
		                <li>complete framework for HTML5 web page.</li>
		                <li>easyui save your time and scales while developing your products.</li>
		                <li>easyui is very easy but powerful.</li>
		            </ul>
		        </div>
		    
        </div>
    </div>
</div>

<script>
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
            $('#dd').dialog({
                title: 'My Dialog',
                width: 600,
                height: 300,
                closed: false,
                cache: false,
                href: 'get_content.php',
                modal: true,
                buttons: [{
                    text:'Ok',
                    iconCls:'icon-ok',
                    handler:function(){
                        $('#ff').form('submit');
                    }
                },{
                    text:'Cancel',
                    handler:function(){
                        $('#dd').dialog('close');
                    }
                }]

            });
            $('#dd').dialog('refresh', 'detail.html');
        }
    },{
        text:'删除',
        iconCls:'icon-cut',
        handler:function(){alert('cut')}
    },'-',{
        text:'Save',
        iconCls:'icon-save',
        handler:function(){alert('save')}
    }];
</script>
<div id="dd">Dialog Content.</div>
<script>
	
</script>

<script type="text/javascript" src="${ctx }/res/jeasyui/k.js"></script>

</body>
</html>	

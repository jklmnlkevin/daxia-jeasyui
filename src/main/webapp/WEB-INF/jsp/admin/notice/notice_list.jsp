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
                Date From: <input class="easyui-datebox" style="width:180px">
                To: <input class="easyui-datebox" style="width:180px">
                Language:
                <select class="easyui-combobox" panelHeight="auto" style="width:100px">
                    <option value="java">Java</option>
                    <option value="c">C</option>
                    <option value="basic">Basic</option>
                    <option value="perl">Perl</option>
                    <option value="python">Python</option>
                </select>
                <a href="#" class="easyui-linkbutton" iconCls="icon-search">查找</a>
            </div>
            <table id="dg">
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
$('#dg').datagrid({
    url:'${ctx }/admin/notice/datagrid',
    columns:[[
        {field:'id',title:'ID',width:100},
        {field:'title',title:'Title',width:100},
        {field:'price',title:'Price',width:100,align:'right'}
    ]],
    toolbar: ${module}_toolbar,
    rownumbers: true,
    pagination: true
});



var ${module}_toolbar = [{
    text:'新增2',
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
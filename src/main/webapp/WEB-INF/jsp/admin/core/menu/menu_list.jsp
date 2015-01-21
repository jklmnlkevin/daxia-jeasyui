<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="module" value="menu" />

<div class="easyui-panel" title="Nested Panel"
	style="width: 900px; height: 600px; padding: 10px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true"
			style="width: 250px; padding: 10px">
			
			<ul id="menu_tt" class="easyui-tree">
				<c:forEach items="${menus }" var="m">
			    	<c:if test="${m.level eq 1 }">
					<li state="closed" id="${m.id }" level="1">
						<span>${m.name }</span>
						<ul>
							<c:forEach items="${m.children }" var="subMenu">
								<li id="${subMenu.id }">
									<span>
										${subMenu.name }
									</span>
								</li>
							</c:forEach>
						</ul>
					</li>
					</c:if>
				</c:forEach>
			</ul>
			
		</div>

		<div data-options="region:'center'" style="padding: 10px">
		
			<div id="menu_detail_p" class="easyui-panel" title="菜单详情"    
			        style="width:500px;padding:10px;background:#fafafa;"  
			        data-options="iconCls:'icon-save',closable:true,   
			                collapsible:true,minimizable:true,maximizable:true">  
			</div>  
			
		</div>
	</div>
</div>

<script>
	$('#menu_tt').tree({
		onClick: function(node){
			if (node.state == 'open') {
				$('#menu_tt').tree('collapse', node.target);
			} else if (node.state == 'closed') {
				$('#menu_tt').tree('expand', node.target);
			}
			
			$('#menu_detail_p').panel({   
			    href:'${ctx}/admin/menu/detail?id=' + node.id,   
			    onLoad:function(){   
			    }   
			});  
		}
	});
</script>



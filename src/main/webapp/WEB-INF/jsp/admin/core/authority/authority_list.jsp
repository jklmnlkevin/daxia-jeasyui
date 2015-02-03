<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="module" value="authority" />

<div class="easyui-panel" title="Nested Panel"
	style="width: 900px; height: 600px; padding: 10px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true"
			style="width: 250px; padding: 10px">
				
			<div style="margin-bottom: 3px;">
			                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="addauthority();">新增权限</a>
			</div>	
			
			 <ul id="authority_tree" class="">
				<c:forEach items="${authorities }" var="a">
					<li state="closed" id="${a.id }" name="${a.name }">
						<span>${a.name }</span>
						<ul>
							<c:forEach items="${a.children }" var="sub">
								<li id="${sub.id }" ${fn:contains(role.authorityNames, sub.name) ? 'checked' : '' }>
									<span>
										${sub.name }
									</span>
									<ul>
										<c:forEach items="${sub.children }" var="sub2">
										<li id="${sub2.id }" ${fn:contains(role.authorityNames, sub2.name) ? 'checked' : '' }>
											<span>${sub2.name }</span>
										</li>	
										</c:forEach>		
									</ul>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
</ul>
			
		</div>

		<div data-options="region:'center'" style="padding: 10px">
			
			<div id="authority_detail_p" class="easyui-panel" title="菜单详情"    
			        style="width:600px;padding:10px;background:#fafafa;"  
			        data-options="iconCls:'icon-save',closable:false,   
			                collapsible:false,minimizable:false,maximizable:false">  
			</div>  
			
		</div>
	</div>
</div>

<script>

	function findAuthority() {
		var name = $('#name').val();
		var tab = $('#tt').tabs('getSelected');  // 获取选择的面板
		$('#tt').tabs('update', {
			tab: tab,
			options: {
				method:"POST",
				href: '${ctx}/admin/authority/list',  // 新内容的URL
				queryParams: {
					name: name
				}
				
			}
		});

		
	}
	$('#authority_tree').tree({
		onClick: function(node){
			if (node.state == 'open') {
				$('#authority_tt').tree('collapse', node.target);
			} else if (node.state == 'closed') {
				$('#authority_tt').tree('expand', node.target);
			}
			
			$('#authority_detail_p').panel({   
			    href:'${ctx}/admin/authority/detail?id=' + node.id,   
			    onLoad:function(){   
			    }   
			});  
		}
	});
	
	function addauthority() {
		$('#authority_detail_p').panel({   
		    href:'${ctx}/admin/authority/detail',   
		    onLoad:function(){   
		    }   
		}); 
	}
</script>



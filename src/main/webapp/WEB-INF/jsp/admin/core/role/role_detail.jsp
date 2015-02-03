<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<div>
	<input type="hidden" id="role_id" value="${role.id }">
	角色名：<input type="text" value="${role.name }" id="role_name" class="easyui-textbox">
</div>
 <ul id="role_detail_tree" class="">
				<c:forEach items="${authorities }" var="a">
					<li state="closed" id="${a.id }" name="${a.name }">
						<span>${a.name }</span>
						<ul>
							<c:forEach items="${a.children }" var="sub">
								<li id="${sub.id }" ${fn:contains(role.authorityNames, sub.name) ? 'checked' : '' }>
									<span>
										${sub.name }
									</span>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
</ul>

<script>
$('#role_detail_tree').tree({
	checkbox:true,
	animate:true
});
  
</script>
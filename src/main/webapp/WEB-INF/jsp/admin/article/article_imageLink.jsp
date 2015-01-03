<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<div style="overflow: auto;height: 100%;">
	<div>在本文中用到的图片有</div>
	<c:forEach items="${urls }" var="url">
	<div>
		<img src="${url }" height="100" width="200"/><a href="javascript:void(0);" onclick="choose('${url}')">选择</a>
	</div>
	</c:forEach>
	<button class="close hidden" id="close">关闭</button>
</div>
<script>
	function choose(url) {
		$('#picUrl').val(url);
		$('#close').trigger('click');
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<div class="unit" style="margin: 10px;">
	<div class="unit">
		最后更新时间: <fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss"/>
		下载次数：${downloadTimes }次
	</div>
	<div class="unit" style="margin-top: 20px;">
		<a href="${ctx }/admin/apiTest/doc" target="_blank" style="color: blue;">点击下载</a>
	</div>
</div>
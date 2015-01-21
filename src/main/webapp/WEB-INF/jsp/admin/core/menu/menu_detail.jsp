<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="menu"/>

	<form id="ff" method="post">  
	    <div>  
	        <label for="name">Name:</label>  
	        <input class="easyui-textbox" type="text" name="name" />  
	    </div>  
	    <div>  
	        <label for="email">Email:</label>  
	        <input class="easyui-textbox" type="text" name="email" data-options="validType:'email'" />  
	    </div>  
	</form>  
 
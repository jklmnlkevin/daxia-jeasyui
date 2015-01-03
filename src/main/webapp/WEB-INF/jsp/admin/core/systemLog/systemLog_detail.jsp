<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="systemLog"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${systemLog.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户:</label>
				<input type="text" name="user" value="${systemLog.user.username }" size="30" />
			</p>
			<p>
				<label>模块:</label>
				<input type="text" name="module" value="${systemLog.module }" size="30" />
			</p>
			<p>
				<label>操作:</label>
				<input type="text" name="operation" value="${systemLog.operation }" size="30" />
			</p>
			<p>
				<label>参数:</label>
				<input type="text" name="params" value="${systemLog.params }" size="30" />
			</p>
			<p>
				<label>时间:</label>
				<input type="text" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" name="date" value="<fmt:formatDate value="${systemLog.date }" pattern="yyyy-MM-dd HH:mm:ss"/>" size="30" /><a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>url:</label>
				<input type="text" name="url" value="${systemLog.url }" size="30" />
			</p>
			<p>
				<label>IP:</label>
				<input type="text" name="url" value="${systemLog.ip }" size="30" />
			</p>
			
			<p>
				<label>是否成功:</label>
				<input type="text" name="url" value="${n.isSuccess ? '是' : '否' }" size="30" />
			</p>
			<p>
				<label>错误信息:</label>
				<input type="text" name="url" value="${systemLog.errorMessage }" size="30" />
			</p>
			

		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(systemLog.id)}">
						<sec:authorize ifAnyGranted="system.systemLog.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(systemLog.id)}">
						<sec:authorize ifAnyGranted="system.systemLog.update">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

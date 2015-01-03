<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="reply"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			
			
			
			
			
				<p>
					<label>关联id:</label>
					<input type="text" name="refrenceId" value="${n.refrenceId }" size="30" />
				</p>
			
			
			
			
			
			
				<p>
					<label>回复内容:</label>
					<input type="text" name="content" value="${n.content }" size="30" />
				</p>
			
			
			
			
			
			
				<p>
					<label>回复用户:</label>
					<input type="hidden" name="user.id" bringBackName="user.id" value="${n.user.id }" size="30" />
					<input type="text" bringBackName="user.username" value="${n.user.username }" size="30"  lookupGroup="user" readonly="readonly"/>
					<a class="btnLook" href="${ctx }/admin/user/search?onlyFindType=0" lookupGroup="user"></a>
				</p>	
			
			
			
			
			
			
				<p>
					<label>回复时间:</label>
					<input type="text" name="replyTime" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="<fmt:formatDate value='${n.replyTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" size="30" readonly/>
				</p>
			
			
			
			
			
			
				<p>
					<label>是否是物业回复:</label>
					<input type="text" name="isEstate" value="${n.isEstate }" size="30" />
				</p>
			
			
			
			
			
			
				<p>
					<label>引用回复:</label>
					<input type="hidden" name="parentReply.id" bringBackName="parentReply.id" value="${n.parentReply.id }" size="30" />
					<input type="text" bringBackName="parentReply.content" value="${n.parentReply.content }" size="30"  lookupGroup="parentReply" readonly="readonly"/>
					<a class="btnLook" href="${ctx }/admin/parentReply/search?onlyFindType=0" lookupGroup="parentReply"></a>
				</p>	
			
			
			
			
			
			
				<p>
					<label>第几楼:</label>
					<input type="text" name="floor" value="${n.floor }" size="30" />
				</p>
			
			
			
			
			
			
				<p>
					<label>关联类型:</label>
					<input type="text" name="type" value="${n.type }" size="30" />
				</p>
			
			
			
			
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(reply.id)}">
						<sec:authorize ifAnyGranted="reply.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(reply.id)}">
						<sec:authorize ifAnyGranted="reply.update">
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

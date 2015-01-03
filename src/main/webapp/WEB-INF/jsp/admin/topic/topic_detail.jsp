<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="topic"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>小区:</label>
				<input type="text" name="community" value="${n.community }" size="30" />
			</p>
			<p>
				<label>标题:</label>
				<input type="text" name="title" value="${n.title }" size="30" />
			</p>
			<p>
				<label>内容:</label>
				<input type="text" name="content" value="${n.content }" size="30" />
			</p>
			<p>
				<label>发表时间:</label>
				<input type="text" name="createTime" value="${n.createTime }" size="30" />
			</p>
			<p>
				<label>最后回复时间:</label>
				<input type="text" name="lastReplyTime" value="${n.lastReplyTime }" size="30" />
			</p>
			<p>
				<label>点击次数:</label>
				<input type="text" name="clickCount" value="${n.clickCount }" size="30" />
			</p>
			<p>
				<label>回复次数:</label>
				<input type="text" name="replyCount" value="${n.replyCount }" size="30" />
			</p>
			<p>
				<label>发起人:</label>
				<input type="text" name="user" value="${n.user }" size="30" />
			</p>
			<p>
				<label>是否邀请物业回复:</label>
				<input type="text" name="isInviteEstate" value="${n.isInviteEstate }" size="30" />
			</p>
			<p>
				<label>物业是否已经回复:</label>
				<input type="text" name="isEstateReplied" value="${n.isEstateReplied }" size="30" />
			</p>
			<p>
				<label>图片列表:</label>
				<input type="text" name="images" value="${n.images }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(topic.id)}">
						<sec:authorize ifAnyGranted="topic.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(topic.id)}">
						<sec:authorize ifAnyGranted="topic.update">
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

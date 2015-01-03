<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="article"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${article.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>商家:</label>
				<input type="text" name="store.id" value="${keyword.store.id }" size="30" store="true"/>
			</p>
			<p>
				<label>标题:</label>
				<input type="text" name="title" value="${article.title }" size="30" />
			</p>
			<div class="unit">
				<label>描述:</label>
				<textarea rows="5" cols="100" name="description">${article.description }</textarea>
			</div>
			<c:if test="${not empty(article.id) }">
			<p>
				<label>图片链接:</label>
				<input type="text" id="picUrl" name="picUrl" value="${article.picUrl }" size="30" />
				<a class="btnLook" target="dialog" href="${ctx }/admin/article/imageLink?id=${article.id}">上传</a>
			</p>
			</c:if>
			<c:if test="${not empty(article.id) }">
			<p>
				<label>正文链接:</label>
				<input type="text" name="url" value="${article.url }" size="30" readonly/>
			</p>
			</c:if>
			<div class="unit">
				<label>内容:</label>
				<textarea class="editor" cols="150" rows="50" name="content" id="elm2" upImgUrl="${ctx }/admin/image/upload">${article.content }</textarea>
			</div>

		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(article.id)}">
						<sec:authorize ifAnyGranted="article.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(article.id)}">
						<sec:authorize ifAnyGranted="article.update">
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

<script>
/* $('.editor').xheditor({upLinkUrl:"upload.php?immediate=1",upLinkExt:"zip,rar,txt",upImgUrl:"upload.php?immediate=1",upImgExt:"jpg,jpeg,gif,png",upFlashUrl:"upload.php?immediate=1",upFlashExt:"swf",upMediaUrl:"upload.php?immediate=1",upMediaExt:"avi"}); */
</script>
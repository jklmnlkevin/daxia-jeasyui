<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="payRecord"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${n.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<input type="hidden" name="community.id" value="${currentUser.community.id}"/>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户:</label>
				<input type="hidden" name="user.id" bringBackName="user.id" value="${n.user.id }" size="30" />
				<input type="text" bringBackName="user.username" value="${n.user.username }" size="30"  lookupGroup="user" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/user/search?onlyFindType=0" lookupGroup="user"></a>
			</p>
			<p>
				<label>缴费时间:</label>
				<input type="text" name="payTime" class="date" readonly dateFmt="yyyy-MM-dd" value="<fmt:formatDate value='${n.payTime }' pattern='yyyy-MM-dd'/>" size="30" />
			</p>
			<p>
				<label>缴费项目:</label>
				<input type="hidden" name="feeItem.id" bringBackName="feeItem.id" value="${n.feeItem.id }" size="30" />
				<input type="text" bringBackName="feeItem.name" value="${n.feeItem.name }" size="30"  lookupGroup="feeItem" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/feeItem/search" lookupGroup="feeItem"></a>
			</p>
			<p>
				<label>缴费月数:</label>
				<input type="text" name="monthCount" value="${n.monthCount }" size="30" />
			</p>
			<p>
				<label>缴费开始月份:</label>
				<input type="text" name="monthStart" value="<fmt:formatDate value='${n.monthStart }' pattern='yyyy-MM'/>" size="30"  class="date" readonly dateFmt="yyyy-MM"/>
			</p>
			<p>
				<label>缴费结束月份:</label>
				<input type="text" name="monthEnd" value="<fmt:formatDate value='${n.monthEnd }' pattern='yyyy-MM'/>" size="30"  class="date" readonly dateFmt="yyyy-MM"/>
			</p>
			<p>
				<label>支付类型:</label>
				<select name="payType">
					<option value="">---</option>
					<c:forEach items="${payTypes }" var="t">
						<option value="${t.value }" ${t.value eq n.payType ? 'selected' : '' }>${t.remark }</option>
					</c:forEach>
				</select>
			</p>
			<c:if test="${not empty(n.id) }">
			<p>
				<label>创建时间:</label>
				<input type="text" readonly="readonly" name="createTime" value="<fmt:formatDate value='${n.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" size="30" />
			</p>
			</c:if>
			<p>
				<label>金额:</label>
				<input type="text" name="money" value="${n.money }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(payRecord.id)}">
						<sec:authorize ifAnyGranted="payRecord.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(payRecord.id)}">
						<sec:authorize ifAnyGranted="payRecord.update">
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

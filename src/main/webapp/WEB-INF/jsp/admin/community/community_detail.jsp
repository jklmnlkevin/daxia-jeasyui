<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="community"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${community.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>名称:</label>
				<input type="text" name="name" value="${community.name }" size="30" />
			</p>
			<p>
				<label>省份:</label>
				<select name="province.id" id="province" class="combox" ref="city" refUrl="${ctx }/admin/city/jsonList?province.id={value}">
					<option value="" selected>请选择</option>
					<c:forEach items="${provinces }" var="p">
					<option value="${p.id }" ${p.id eq community.province.id ? 'selected' : '' }>${p.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>城市:</label>
				<select name="city.id" id="city" class="combox" ref="district" refUrl="${ctx }/admin/district/jsonList?city.id={value}">
					<c:forEach items="${cities }" var="ci">
					<option value="${ci.id }" ${ci.id eq community.city.id ? 'selected' : '' }>${ci.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>地区:</label>
				<select name="district.id" id="district" class="combox">
					<c:forEach items="${districts }" var="di">
					<option value="${di.id }" ${di.id eq community.district.id ? 'selected' : '' }>${di.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>物业电话:</label>
				<input type="text" name="telephone" value="${community.telephone }" size="30" />
			</p>
			<p>
				<label>物业手机:</label>
				<input type="text" name="mobilephone" value="${community.mobilephone }" size="30" />
			</p>
			<p>
				<label>联系人:</label>
				<input type="text" name="contacter" value="${community.contacter }" size="30" />
			</p>
			<p>
				<label>地址:</label>
				<input type="text" name="address" value="${community.address }" size="30" />
			</p>
			<p>
				<label>经度:</label>
				<input type="text" name="longitude" value="${community.longitude }" size="30" />
			</p>
			<p>
				<label>纬度:</label>
				<input type="text" name="latitude" value="${community.latitude }" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(community.id)}">
						<sec:authorize ifAnyGranted="community.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(community.id)}">
						<sec:authorize ifAnyGranted="community.update">
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

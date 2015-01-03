<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<c:set var="module" value="user"/>

<div class="pageContent">
	<form method="post" action="${ctx }/admin/${module }/save?id=${user.id}&navTabId=${param.navTabId}" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用户类型:</label>
				<select name="userType">
					<option value="">---</option>
					<c:forEach items="${userTypes }" var="t">
						<option value="${t.value }" ${t.value eq user.userType ? 'selected' : '' }>${t.remark }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>用户名:</label>
				<input type="text" name="username" ${not empty(user.id) ? 'readonly' : ''} value="${user.username }" size="30" />
			</p>
			<c:if test="${empty (user.id) }">
				<p>
				<label>密码:</label>
				<input type="password" name="password" size="30" />
			</p>
			</c:if>
			<%-- <p>
				<label>密码:</label>
				<input type="text" name="password" value="${user.password }" size="30" />
			</p> --%>
			
			<p>
				<label>电话:</label>
				<input type="text" name="telephone" value="${user.telephone }" size="30" />
			</p>
			<p>
				<label>省份:</label>
				<select  name="province.id" id="province" class="combox" ref="city" refUrl="${ctx }/admin/city/jsonList?province.id={value}">
					<option value="" selected>请选择</option>
					<c:forEach items="${provinces }" var="p">
					<option value="${p.id }" ${p.id eq user.province.id ? 'selected' : '' }>${p.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>城市:</label>
				<select   name="city.id" id="city" class="combox" ref="district" refUrl="${ctx }/admin/district/jsonList?city.id={value}">
					<c:forEach items="${cities }" var="ci">
					<option value="${ci.id }" ${ci.id eq user.city.id ? 'selected' : '' }>${ci.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>地区:</label>
				<select  name="district.id" id="district" class="combox">
					<c:forEach items="${districts }" var="di">
					<option value="${di.id }" ${di.id eq user.district.id ? 'selected' : '' }>${di.name }</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label>小区:</label>
				<input type="hidden" name="community.id" bringBackName="community.id" value="${user.community.id }" size="30" />
				<input type="text" bringBackName="community.name" value="${user.community.name }" size="30"  lookupGroup="community" readonly="readonly"/>
				<a class="btnLook" href="${ctx }/admin/community/search" lookupGroup="community"></a>
			</p>
			
			<c:if test="${user.userType eq 0}"> <!-- 如果是普通用户，显示更多的字段 -->
				<p>
					<label>身份证号码:</label>
					<input type="text" name="idCard" value="${user.idCard }" size="30" />
				</p>
				<p>
					<label>经度:</label>
					<input type="text" name="longitude" value="${user.longitude }" size="30" />
				</p>
				<p>
					<label>纬度:</label>
					<input type="text" name="latitude" value="${user.latitude }" size="30" />
				</p>
				<p>
					<label>楼号:</label>
					<input type="text" name="building" value="${user.building }" size="30" />
				</p>
				<p>
					<label>门牌号:</label>
					<input type="text" name="doorplate" value="${user.doorplate }" size="30" />
				</p>
				<p>
					<label>业主:</label>
					<input type="text" name="owner" value="${user.owner }" size="30" />
				</p>
				<p>
					<label>业主身份证号:</label>
					<input type="text" name="ownerIdCard" value="${user.ownerIdCard }" size="30" />
				</p>
				<p>
				<label>是否已认证:</label>
				<select name="authenticated">
					<option value="0" ${user.authenticated ? '' : 'selected' }>否</option>
					<option value="0" ${user.authenticated ? 'selected' : '' }>是</option>
				</select>
				</p>
				<p>
					<label>是否接收通知:</label>
					<select name="receiveNotice">
						<option value="0" ${user.receiveNotice ? '' : 'selected' }>否</option>
						<option value="0" ${user.receiveNotice ? 'selected' : '' }>是</option>
					</select>
				</p>
			</c:if>
			
			<%-- <p>
				<label>Push ID:</label>
				<input type="text" name="pushId" value="${user.pushId }" size="30" />
			</p> --%>
			
		</div>
		<div class="formBar">
			<ul>
				<li>
					<c:if test="${empty(user.id)}">
						<sec:authorize ifAnyGranted="user.add">
						<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
						</sec:authorize>
					</c:if>
					<c:if test="${not empty(user.id)}">
						<sec:authorize ifAnyGranted="user.update">
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

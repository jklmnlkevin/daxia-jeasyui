<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="module" value="notice" />

	 <div style="padding:10px 60px 20px 60px">
		<form id="ff" method="post">
			<table cellpadding="5">
				<tr>
					<td>Name:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input class="easyui-textbox" type="text" name="email"
						data-options="required:true,validType:'email'"></input></td>
				</tr>
				<tr>
					<td>Subject:</td>
					<td><input class="easyui-textbox" type="text" name="subject"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>Message:</td>
					<td><input class="easyui-textbox" name="message"
						data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
				<tr>
					<td>Language:</td>
					<td><select class="easyui-combobox" name="language"><option
								value="ar">Arabic</option>
							<option value="bg">Bulgarian</option>
							<option value="ca">Catalan</option>
							<option value="zh-cht">Chinese Traditional</option>
							<option value="cs">Czech</option>
						</select></td>
				</tr>
			</table>
		</form>
		
	</div>

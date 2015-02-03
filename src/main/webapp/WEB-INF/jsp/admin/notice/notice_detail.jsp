<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

	 <div style="padding:10px 60px 20px 60px">
		<form id="notice_detail_form" method="post" class="k-form" action="${ctx }/admin/notice/save?id=${notice.id }">
			<table cellpadding="5" style="margin: auto;" class="k-form-table">
                <tr>
                    <td>标题:</td>
                    <td><input class="easyui-textbox" type="text" name="title" data-options="required:true"></input></td>
                    <td>内容:</td>
                    <td><input class="easyui-textbox" name="message" data-options="multiline:true" style="height:60px"></input></td>
                </tr>
            </table>
		</form>
	</div>

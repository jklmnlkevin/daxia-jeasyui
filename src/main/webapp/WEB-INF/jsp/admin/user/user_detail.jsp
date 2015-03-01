<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

	 <div class="k-form-div">
		<form id="user_detail_form" method="post" class="k-form" action="${ctx }/admin/user/save?id=${model.id }">
			<table cellpadding="5" style="margin: auto;" class="k-form-table">
                <tr>
							
									<td>用户名:</td>
									<td>
									<input type="text" name="username" value="${model.username }" size="30" class="easyui-textbox"/>
									</td>
							
									<td>电话:</td>
									<td>
									<input type="text" name="mobile" value="${model.mobile }" size="30" class="easyui-textbox"/>
									</td>
							</tr>
							<tr>
							
									<td>密码:</td>
									<td>
									<input type="text" name="password" value="${model.password }" size="30" class="easyui-textbox"/>
									</td>
							
									<td>用户类型:</td>
									<td>
									<input type="text" name="userType" value="${model.userType }" size="30" class="easyui-textbox"/>
									</td>
							</tr>
							<tr>
							
									<td>用户头像:</td>
									<td>
									<input type="text" name="headImage" value="${model.headImage }" size="30" class="easyui-textbox"/>
									</td>
							
									<td>性别:</td>
									<td>
									<input type="text" name="sex" value="${model.sex }" size="30" class="easyui-textbox"/>
									</td>
                </tr>
            </table>
		</form>
	</div>
	
<script>
$('#user_detail_form').form({
	success: function(data) {
		var ok = k_success(data);
		if (ok) {
			$('#dd').dialog('close');
		}
	}
});	
</script>	

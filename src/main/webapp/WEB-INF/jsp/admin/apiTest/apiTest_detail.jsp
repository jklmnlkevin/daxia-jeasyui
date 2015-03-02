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
		<form id="apiTest_detail_form" method="post" class="k-form" action="${ctx }/admin/apiTest/save?id=${model.id }">
			<table cellpadding="5" style="margin: auto;" class="k-form-table">
				<tr>
					<td>api模块:</td>
					<td>
					<select name="apiModule.id">
						<c:forEach items="${apiModules}" var="m">
							<option value="${m.id }" ${m.id eq model.apiModule.id ? 'selected' : '' }>${m.name }</option>
						</c:forEach>
					</select>
					</td>
					<td>api名称:</td>
					<td>
						<input type="text" name="name" value="${model.name }" size="30" />
					</td>
				</tr>
				<tr>
					<td>url:</td>
					<td>
						<input class="easyui-textbox" type="text" name="url" value="${model.url }" size="30" placeholder="不要包括${ctx }/"/>
					</td>	
					<td>请求方式:</td>
					<td>
					<select  name="requestMethod">
						<option value="POST" ${model.requestMethod eq 'POST' ? 'selected' : '' }>POST</option>
						<option value="GET" ${model.requestMethod eq 'GET' ? 'selected' : '' }>GET</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>示例url:</td>
					<td>
						<input type="text" name="exampleUrl" value="${model.exampleUrl }" size="30" placeholder="不要包括${ctx }/"/>
					</td>
					<td>描述:</td>
					<td>
					<textarea name="description" style="width:70%; height: 100px;">${model.description }</textarea>
					</td>
				</tr>
				<tr>
					<td>示例返回:</td>
					<td>
						<textarea class="easyui-textbox" name="exampleResponse" style="width:70%; height: 100px;">${model.exampleResponse }</textarea>
					</td>
					<td></td>
					<td></td>
				</tr>
				</table>
			<div class="unit">
				<label>参数:</label>
				<button onclick="return add();">增加参数</button>
				<div>
					<table class="table" width="100%" layoutH="138" nowrapTD="false">
						<thead>
							<tr>
								<th align="center" width="100">
									参数名
								</th>
								<th align="center" width="100">
									是否必须
								</th>
								<th align="center" width="100">
									默认值
								</th>
								<th align="center" width="100">
									说明
								</th>
								<th align="center" width="100">
									操作
								</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<tr id="templateTr">
								<td>
									<input type="text" name="names"/>
								</td>
								<td>
									<select name="requires">
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</td>
								<td>
									<input type="text" name="defaultValues"/>
								</td>
								<td>
									<textarea style="width:100%;" name="descriptions">这个参数主要是</textarea>
								</td>
								<td>
									<button onclick="return _delete(this);">删除</button>
								</td>
							</tr>
							<c:forEach items="${model.apiTestParameters }" var="p">
							<tr>
								<td>
									<input type="text" name="names" value="${p.name }"/>
								</td>
								<td>
									<select name="requires">
										<option value="0" ${p.required ? '' : 'selected'}>否</option>
										<option value="1" ${p.required ? 'selected' : ''}>是</option>
									</select>
								</td>
								<td>
									<input type="text" name="defaultValues" value="${p.defaultValue }"/>
								</td>
								<td>
									<textarea style="width:100%;" name="descriptions">${p.description }</textarea>
								</td>
								<td>
									<button onclick="return _delete(this);">删除</button>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
		</form>
	</div>
	
<script>
$('#apiTest_detail_form').form({
	success: function(data) {
		var ok = k_success(data);
		if (ok) {
			$('#dd').dialog('close');
		}
	}
});	
</script>

<script>
	var templateTr = $('#templateTr').html();
	$('#templateTr').remove();
	function _delete(btn) {
		$(btn).parent().parent().remove();
	}
	
	function add() {
		var tbody = $('#tbody');
		tbody.append('<tr>' + templateTr + '</tr>');
		return false;
	}
	
</script>	

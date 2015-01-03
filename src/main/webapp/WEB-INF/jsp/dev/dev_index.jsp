<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>dev utils</title>
<link href="../res/dev/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="../res/dev/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<style>
	.top-left {
		width:25%;
		float:left;
	}
	
	.top-center {
		width:25%;
		float:left;
	}
	
	.top-center2 {
		width:25%;
		float:left;
	}
	
	.top-right {
		width:25%;
		float:left;
	}
	
	.top-right span {
		width:50%;
	}
</style>
</head>
<body style="width:1300px;margin:auto;">
	<div>
		<div style="border:0px solid #ccc;">
			<div class="top-left">
			基本参数：<br/>
			Model名：<input type="text" name="model" value="" placeholder="大写开头，与数据库表名相同"/><br/>
			Model中文名：<input type="text" name="modelChineseName" value="" placeholder=""/><br/>
			上级菜单：<input type="text" name="parentMenu" value="" placeholder="已有的一级菜单的名字，如系统配置"/><br/>
			<!-- 菜单名：<input type="text" name="menu"/><br/> -->
			<!-- 菜单url：<input type="text" name="menuUrl"/><br/> -->
			
			<input type="radio" name="fromDB" value="0" checked/>不从数据库生成
			<input type="radio" name="fromDB" value="1" onclick="getTableInfo();"/>从数据库生成
			<br/>
			</div>
			<div class="top-center">
			<!-- 上级权限：<input type="text" class="input-small" name="parentAuthorityName"/><br/>
			自己的权限base名称：<input type="text" class="input-small" name="authorityName"/><br/> -->
			<!-- 查看所需权限名称:<input flag="a-list" type="text" class="input-small" name="listAuthority"/><br/>
			新增所需权限名称:<input type="text" class="input-small" name="addAuthority"/><br/>
			编辑所需权限名称:<input type="text" class="input-small" name="editAuthority"/><br/>
			删除所需权限名称:<input type="text" class="input-small" name="deleteAuthority"/><br/> -->
			</div>
			<div class="top-right" id="db">
			</div>
		</div>
		<div style="margin-top:10px;border:1px solid #ccc;">
			<table class="table table-bordered">
				<thead>
					<th>类型</th>
					<th>db字段名</th>
					<th>java字段名</th>
					<th>注释</th>
					<th>短注释</th>
					<th>操作</th>
				</thead>
				<tbody>
					<tr class="firstTr">
						<td>
							<input type="text" field="type" value="java.lang.Long"/>
						</td>
						<td>
							<input type="text" class="input-small" value="id" field="dbName"/>
						</td>
						<td>
							<input type="text" value="id" field="name"  class="input-medium" />
						</td>
						<td>
							<input type="text" value="id" field="comment"  class="input-small" />
						</td>
						<td>
							<input type="text" value="id" field="shortComment"  class="input-small" />
						</td>
						<td>
							<input type="checkbox" name="asQuery" value="id"/>查询
							<input type="checkbox" name="asLikeQuery" value="id"/>模糊
						</td>
						<td>
							<a class="btn" href="###" onclick="addLine(this);">加</a>
							<a class="btn" href="###" onclick="deleteLine(this);">减</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<button class="btn btn-primary" onclick="generate();">点我生成</button>
	</div>
	
	<script src="../res/dwz/js/jquery-1.7.2.min.js"></script>
	<script src="../res/dev/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
	<script src="../res/dev/dev.js" type="text/javascript"></script>
	
</body>
</html>
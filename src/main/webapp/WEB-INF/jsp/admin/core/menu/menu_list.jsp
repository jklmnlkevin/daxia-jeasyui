<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="module" value="menu" />

<div class="easyui-panel" title="Nested Panel"
	style="width: 900px; height: 600px; padding: 10px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true"
			style="width: 250px; padding: 10px">
			<ul id="tt" class="easyui-tree">
				<li><span>Folder</span>
					<ul>
						<li><span>Sub Folder 1</span>
							<ul>
								<li><span><a href="#">File 11</a></span></li>
								<li><span>File 12</span></li>
								<li><span>File 13</span></li>
							</ul></li>
						<li><span>File 2</span></li>
						<li><span>File 3</span></li>
					</ul></li>
				<li><span>File21</span></li>
			</ul>
		</div>

		<div data-options="region:'center'" style="padding: 10px">
			Center Content</div>
	</div>
</div>



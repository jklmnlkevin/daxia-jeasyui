<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <title>API文档</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Bootstrap是Twitter推出的一个用于前端开发的开源工具包。它由Twitter的设计师Mark Otto和Jacob Thornton合作开发，是一个CSS/HTML框架。Bootstrap中文网致力于为广大国内开发者提供详尽的中文文档、代码实例等，助力开发者掌握并使用这一框架。">
<meta name="keywords" content="Bootstrap,CSS,CSS框架,CSS framework,javascript,bootcss,bootstrap开发,bootstrap代码,bootstrap入门">
    <meta name="author" content="Bootstrap中文网">

    <!-- Le styles -->
    <link href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="http://v2.bootcss.com/assets/css/docs.css" rel="stylesheet">
<style>
body{font-family:"ff-tisa-web-pro-1","ff-tisa-web-pro-2","Lucida Grande","Helvetica Neue",Helvetica,Arial,"Hiragino Sans GB","Hiragino Sans GB W3","Microsoft YaHei UI","Microsoft YaHei","WenQuanYi Micro Hei",sans-serif;
	padding-top:0px;
}

.hide {
	display: none;
}

.my_ul {
}

.my_ul li{
	list-style: none;
	padding: 5px;
}
</style>
    <link href="http://v2.bootcss.com/assets/js/google-code-prettify/prettify.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="http://v2.bootcss.com/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="http://v2.bootcss.com/assets/ico/favicon.png">


  </head>

  <body data-spy="scroll" data-target=".bs-docs-sidebar">

    
<header class="jumbotron subhead" id="overview">
  <div class="container">
    <h1>API</h1>
    <p class="lead">对，这里就是API列表。</p>
  </div>
</header>


  <div class="container">

    <!-- Docs nav
    ================================================== -->
    <div class="row">
      <div class="span3 bs-docs-sidebar">
        <ul class="nav nav-list bs-docs-sidenav">
        	<c:forEach items="${apiModules }" var="m">
          		<li onclick="expand(this);"><a href="#section_${m.id }"><i class="icon-chevron-right"></i> ${m.name }</a></li>
          		<li class="my_li">
          			<ul class="my_ul">
          				<c:forEach items="${m.apiTests }" var="api">
          					<li><a href="#api_${api.id}">${api.name }</a></li>
          				</c:forEach>
          			</ul>
          		</li>
          	</c:forEach>
        </ul>
      </div>
      <div class="span9">



        <!-- Dropdowns
        ================================================== -->
      
      <c:forEach items="${apiModules }" var="m">
        
        <section id="section_${m.id }">
          <div class="page-header">
            <h1>${m.name }</h1>
          </div>

		  <!-- 一个api开始 -->
		  <c:forEach items="${m.apiTests }" var="api">
		  <section id="api_${api.id }">	
          <h2>${api.name }</h2>
          <h4>说明</h4>
          <p>${api.description }</p>
          <h4>URL</h4>
		  <p><a href="${ctx }/${api.fullUrl }" target="_blank">${ctx }/${api.fullUrl }</a></p>
		  <h4>请求方式</h4>
		  <p>${api.requestMethod }</p>          
          <h4>参数列表</h4>
          
          <table class="table table-bordered">
          	<thead>
          		<tr>
          			<th>参数名</th>
          			<th>是否必须</th>
          			<th>默认值</th>
          			<th>说明</th>
          		</tr>
          	</thead>
          	<tbody>
          		<c:forEach items="${api.apiTestParameters }" var="p">
	          		<tr>
	          			<td>${p.name }</td>
	          			<td>${p.required ? '是' : '否' }</td>
	          			<td>${p.defaultValue }</td>
	          			<td>${p.description }</td>
	          		</tr>
          		</c:forEach>
          	</tbody>
          </table>
          <h4>请求示例</h4>
          <div class="bs-docs-example">
          <p><a href="${ctx }/${api.exampleUrl }" target="_blank">${ctx }/${api.exampleUrl }</a></p>
          </div>
          <h4>返回示例</h4>
          <div class="bs-docs-example">
            <pre>${api.exampleResponse }</pre>
          </div>
          </section>
          </c:forEach>
		<!-- 一个api结束 -->
         
        </section>
</c:forEach>






      </div>
    </div>

  </div>



    <!-- Footer
    ================================================== -->
    <footer class="footer">
      <div class="container">
        <p>Designed and built with all the love in the world by <a href="http://twitter.com/mdo" target="_blank">@mdo</a> and <a href="http://twitter.com/fat" target="_blank">@fat</a>.</p>
        <p>Code licensed under <a href="http://www.apache.org/licenses/LICENSE-2.0" target="_blank">Apache License v2.0</a>, documentation under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <p><a href="http://glyphicons.com">Glyphicons Free</a> licensed under <a href="http://creativecommons.org/licenses/by/3.0/">CC BY 3.0</a>.</p>
        <ul class="footer-links">
          <li><a href="http://blog.getbootstrap.com">Blog</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twbs/bootstrap/issues?state=open">Issues</a></li>
          <li class="muted">&middot;</li>
          <li><a href="https://github.com/twbs/bootstrap/releases">Changelog</a></li>
        </ul>
      </div>
    </footer>



    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <script src="http://cdn.bootcss.com/holder/2.0/holder.min.js"></script>
    <script src="http://v2.bootcss.com/assets/js/google-code-prettify/prettify.js"></script>

    <script src="http://v2.bootcss.com/assets/js/application.js"></script>
    <script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F3d8e7fc0de8a2a75f2ca3bfe128e6391' type='text/javascript'%3E%3C/script%3E"));
    </script>
	
	<script>
		// $('ul.my_ul').hide();
		$('li.my_li').hide();
		
		function expand(li) {
			$('li.my_li').hide();
			$(li).next('li.my_li').show();
		}
	</script>

  </body>
</html>

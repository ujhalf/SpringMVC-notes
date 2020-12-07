<%--
  Created by IntelliJ IDEA.
  User: 86130
  Date: 2020/12/7
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="hello">入门程序</a></br>
<a href="withParams1?user=Kobe">测试requestMapping注解中的params属性，请求必须带指定参数</a></br>
<a href="withParams2?user=Kobe">测试requestMapping注解中的params属性，参数必须是特定的值</a></br>
<a href="withParams3?user=Kobe">测试requestMapping注解中的params属性，参数不能是特定的值</a></br>

<a href="withHeaders?user=Kobe">测试requestMapping注解中的headers属性，参数必须包含相应的头</a></br>
</body>
</html>

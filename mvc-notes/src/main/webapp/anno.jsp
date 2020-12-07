<%--
  Created by IntelliJ IDEA.
  User: 86130
  Date: 2020/12/7
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常用注解的使用</title>
</head>
<body>
<%--自定义类型转换器--%>
<form action="anno/customConverter" method="post">
    输入日期:<input type="text" name="date"></br>
    <input type="submit" value="提交">
</form>

</body>
</html>

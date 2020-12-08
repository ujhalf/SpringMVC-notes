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

<h3>获取Servlet原生API</h3>
<a href="anno/servletAPI">测试获取原生ServletAPI</a></br>
<a href="anno/requestParam?name=kobe">测试RequestParam</a></br>

<h3>测试RequestBody注解</h3>
<form action="anno/requestBody" method="post">
    姓名:<input type="text" name="uname"></br>
    年龄:<input type="text" name="age"></br>
<input type="submit" value="提交"></br>
</form>
</br>
<a href="anno/path/1305158">测试PathVariable</a></br>
<a href="anno/requestHeader">测试RequestHeader</a></br>
<a href="anno/cookieValue">测试CookieValue</a></br>
<a href="anno/sessionAttributes">测试SessionAttributes存入属性</a></br>
<a href="anno/getSessionAttribute">测试SessionAttributes获取属性</a></br>
<a href="anno/deleteSessionAttribute">测试SessionAttributes删除属性</a></br>
<h3>测试ModelAttribute注解</h3>
<form action="anno/user" method="post">
    姓名:<input type="text" name="uname"></br>
    <input type="submit" value="提交"></br>
</form>
</br>
</body>

</html>

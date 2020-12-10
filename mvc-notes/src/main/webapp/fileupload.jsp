<%--
  Created by IntelliJ IDEA.
  User: 86130
  Date: 2020/12/9
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<h3>文件上传最原始的方式</h3>
<form action="file/upload1" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="upload1"></br>
    <input type="submit" value="提交">
</form>
</hr>

<h3>SpringMVC进行文件上传</h3>
<form action="file/upload2" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="upload2"></br>
    <input type="submit" value="提交">
</form>
</hr>

<h3>SpringMVC跨服务器文件上传</h3>
<form action="file/upload3" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="upload3"></br>
    <input type="submit" value="提交">
</form>
</hr>
</body>
</html>


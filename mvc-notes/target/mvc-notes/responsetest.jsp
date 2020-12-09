<%--
  Created by IntelliJ IDEA.
  User: 86130
  Date: 2020/12/9
  Time: 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Response相关用法展示</title>
    <%--    引入js文件--%>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn1").click(function () {
                alert("hello btn")
            });
        })
    </script>

    <script>
        $(function () {
            $("#btn2").click(function () {
               $.ajax({
                   url:"response/ajax",
                   contentType:"application/json;charset=UTF-8",
                   data:'{"uname":"kobe","age":"40"}',
                   dataType:"json",
                   type:"post",
                   success:function (data) {
                       alert(data.uname)
                       alert(data.age)
                   }

               });
            });
        });
    </script>
<body>
<a href="response/string">测试返回值为String类型,调用后将会将跳入对应页面 并将模型数据填充</a></br>
<a href="response/void">测试返回值为Void类型,调用后默认会将请求路径解析为视图名称</a></br>
<a href="response/voidForward">测试返回值为Void类型,使用转发处理后续逻辑</a></br>
<a href="response/voidRedirect">测试返回值为Void类型,使用重定向处理后续逻辑</a></br>
<a href="response/voidWriter">测试返回值为Void类型,使用response调用输出流直接写回响应</a></br>
<a href="response/modelAndView">测试返回值为ModelAndView</a></br>
<a href="response/forward">测试返回值为String类型,通过使用forward关键字进行转发</a></br>
<a href="response/redirect">测试返回值为String类型,通过使用redirect关键字进行重定向</a></br>

<button id="btn1">发送ajax异步请求</button></br>
<button id="btn2">ajax post请求</button>
</body>
</html>

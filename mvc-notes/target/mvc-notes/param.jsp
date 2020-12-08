<%--
  Created by IntelliJ IDEA.
  User: 86130
  Date: 2020/12/7
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<br>
<h3>RequestMapping注解中的属性使用</h3>
<a href="param/withParams1?user=Kobe">测试requestMapping注解中的params属性，请求必须带指定参数</a></br>
<a href="param/withParams2?user=Kobe">测试requestMapping注解中的params属性，参数必须是特定的值</a></br>
<a href="param/withParams3?user=Kobe">测试requestMapping注解中的params属性，参数不能是特定的值</a></br>
<a href="param/withHeaders?user=Kobe">测试requestMapping注解中的headers属性，参数必须包含相应的头</a></br>
<h3>请求参数绑定</h3>
<a href="param/bindParam?name=Kobe">简单的绑定参数场景</a></br>
</br>
<h3>演示多个参数的绑定 引用类型 以及集合类型</h3>
<form action="param/mutipleParams" method="post">
    姓名:<input type="text" name="username"/></br>
    密码:<input type="text" name="password"/></br>
    工资:<input type="text" name="money"/></br>
    <%--这里使用user.来将引用类型的属性进行绑定--%>
    用户姓名:<input type="text" name="user.uname"/></br>
    用户年龄:<input type="text" name="user.age"/></br>
    <%--list类型参数的绑定--%>
    list列表第一项:<input type="text" name="list[0]"/></br>
    list列表第二项:<input type="text" name="list[1]"/></br>
    list列表第三项:<input type="text" name="list[2]"/></br>
    <%--map类型参数的绑定--%>
    map第一项:<input type="text" name="map['color']"/></br>
    map第二项:<input type="text" name="map['weight']"/></br>
    <input type="submit" value="点击提交">
</form>

</html>

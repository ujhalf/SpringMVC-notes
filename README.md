# SpringMVC-notes

## 1.SpringMVC基本概念

### 1.1三层架构与MVC

#### 1.1.1三层架构

- **表现层**：
	- web层，负责接收客户端请求，并响应结果。通常使用http协议进行。
	- 包括展示层和控制层，控制层负责接收请求，展示层负责展示结果。
	- MVC是表现层的设计模型，与其它层没有关系。
	- 需要依赖业务层，调用业务层进行业务处理并响应结果给客户端
- **业务层**：
	- service层，负责业务处理
	- 自身并不依赖于web层，但web层依赖于业务层
	- 可能会依赖于持久层，数据持久化中的事务一致性由业务层进行控制。
- **持久层**：
	- dao层，负责数据持久化。
	- 包括数据层及数据访问层。
	- 数据层：即数据库，是数据持久化的载体
	- 数据访问层：业务层与持久层进行数据交互的接口，持久层通过数据访问层将数据持久化到数据库中。

#### 1.1.2 MVC模型

​		MVC全称：Model View Controller，是一种用于设计创建web应用程序表现层的模式。

- Model
	- 数据模型，用于封装数据
- View
	- jsp或者是html页面，用于展示数据
	- 视图的创建依赖于模型数据
- Controller
	- 负责程序逻辑的处理

### 1.2 Spring  MVC概述

​		SpringMVC是一种基于Java实现的MVC设计模型的轻量级web框架。通过使用注解让简单的Java类成为请求的控制器，支持Restful编程风格。

![](D:\prj\mvc\SpringMVC-notes\images\image-20201207110937946.png)

#### 1.2.1 Spring MVC优势

- 清晰的角色划分
	- 前端控制器(DispatcherServlet)
	- 映射处理器(HandlerMapping)
	- 处理器适配器(HandlerAdapter)
	- 视图解析器(ViewResolver)
	- 控制器(Controller)
	- 校验器(Validator)
	- 命令对象(Command)
	- 表单对象(Form Object)
- 分工明确，易于扩展
- HandlerAdapter可适配
- 强大的数据校验、数据绑定
- 使用Mock可以非常方便地进行单元测试
- 本地化、主题

#### 1.2.2与Structs2的优劣分析

​	共同点：

- 都是基于MVC模型的表现层框架
- 底层都是ServletAPI
- 处理请求机制都使用一个核心控制器

​      区别：

- SpringMVC的入口是Servlet,Structs2的入口是Filter
- SpringMVC基于方法设计，Structs2基于类设计，前者效率会更高
- SpringMVC更加简洁，支持JSR-303,处理ajax请求更加方便



## 2.SpringMVC入门程序

### 1.在`pom.xml`中引入依赖坐标

```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <!--锁定spring版本-->
        <spring.version>5.0.2.RELEASE</spring.version>
    </properties>

    <dependencies>
        <!--依赖于spring IOC的相关组件-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!--servlet以及 jsp依赖-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>
```

### 2.配置DispatcherServlet

在`web.xml`中配置前端控制器

```xml
<!DOCTYPE web-app PUBLIC
        "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
        "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
    <display-name>Archetype Created Web Application</display-name>
    <!--中文乱码问题 post请求时会出现 -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <!-- 指定字符集-->
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!--前端控制器-->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--启动时加载context配置文件-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <!--启动时即创建 而不是等到第一次请求-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>


</web-app>

```

### 3.请求处理流程

![](D:\prj\mvc\SpringMVC-notes\images\请求处理流程.png)

### 4.SpringMVC组件

- DispatcherServlet:整个请求处理流程的中心，由前端控制器调用其它组件处理请求，解耦了各个组件。
- HandlerMapping:根据请求路径定位到对应的处理器，返回执行链
- Handler：具体业务处理控制器，即controller
- HandlerAdapter:处理器适配器
- ViewResolver：视图解析器，负责将处理结果生成view
- View:包含jsp、jstView、freemarkerView、pdfView等，通过页面标签或者页面模板技术将模型数据呈现给用户

### 5.关于<mvc:annotation-driven>

SpringMVC三大组件:HandlerMapping、HandlerAdapter、ViewResolver

配置该标签将会自动加载`RequestMappingHandlerMapping`、`RequestMappingHandlerAdapter`，在xml文件中配置这个标签替代使用注解配置。

### 6.SpringMVC注解

1. #### `RequestMapping`

	- 注解在类上，方法上
	- 作用:建立请求URL与处理请求方法之间的对应关系
	- params属性，用于限制请求参数。params = {"accountName"}，表示请求参数必须有accountName；params = {"moeny!100"}，表示请求参数中money不能是100。
	- headers属性,用于指定接受的请求头。

2. #### `RequesstParam`

	- 注解在方法上
	- 用于绑定请求参数和controller中方法的形参
	- 当请求参数与默认的形参一样时可以不加此注解
	- required属性表示这个参数是否是必须的，默认是true

3. `RequestBody`

	- 注解在方法上
	- 用于获取请求体中的参数

4. **`PathVariable`**

	- 注解在方法上
	- Restful编程风格中常常使用。Restful：使用名词表示操作的资源，使用http method 动词表示请求方法
	- /path/{id}表示获取请求中的参数

5. `RequestHeader`

	- 用于获取请求头信息
	- @CookieValue("JSESSIONID") 

6. **ModelAttribute**

	- 注解在方法和参数上
	- 注解在方法上:被注解的方法会在控制器中的方法执行前先执行
	- 注解在参数上:获取指定的数据 为参数赋值

7. **SessionAttributes**

	- 只能注解在类上

	



​	
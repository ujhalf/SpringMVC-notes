1. # SpringMVC-notes


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



## 2.SpringMVC基本配置

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

6. `ModelAttribute`

	- 注解在方法和参数上
	- 注解在方法上:被注解的方法会在控制器中的方法执行前先执行
	- 注解在参数上:获取指定的数据 为参数赋值

7. `SessionAttributes`

	- 只能注解在类上
	- 用于在请求中共享数据
	- 通过`Model`对象的Model.addAttribute()添加属性; `ModelMap`对象的`ModelMap.getAttribute()`获取属性;通过`SessionStatus`的`setComplete()`清除所有的属性
	
8. **ResponseBody**

	- 注解在方法上
	- 方法返回值应当作为参数绑定在响应体中



## 3.SpringMVC response

​		依据controller中请求处理方法的返回值,分类如下:

返回字符串类型

- 返回字符串默认为视图的名称，会根据context中配置的视图解析器中的路径去寻找指定名称的视图。

- 可通过使用`Model`对象向request域中传递参数，并传递到视图中。

- ```java
	    /*演示请求处理方法返回值为String类型，
	    这种场景下会根据返回的字符串去依据springmvc.xml中
	    的视图解析器配置去匹配相应的视图*/
	    @GetMapping("/string")
	    public String retStr(Model model) {
	        System.out.println("方法返回值为String类型");
	        //创建一个对象 使用Model存入视图中 并在页面上展示
	        User user = new User();
	        user.setAge(15);
	        user.setUname("kobe");
	        model.addAttribute("user", user);
	        //视图的逻辑名称 会返回这个视图
	        return "response";
	    }
	
```

void类型

- 当controller方法不含返回值时，默认会将请求路径来匹配查询对应的视图

- 为了阻止默认的这种行为，可以使用ServletAPI来自己编写转发和重定向处理后续逻辑

- 转发:1次请求，可以直接请求WEB/INF下的资源，但是自己调用API进行转发时并不会使用视图解析器的配置，因此路径/视图后缀需要写全，如:`request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);`

- 重定向:两次请求，不能直接请求WEB-INF下的资源，路径写法:`response.sendRedirect(request.getContextPath()+"/index.jsp");`

- 使用response调用输出流直接写回响应内容

- ```java
  /**
       * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
       */
      @GetMapping("/void")
      public void retVoid(Model model ) throws ServletException, IOException {
          System.out.println("方法返回值为Void类型");
          //创建一个对象 使用Model存入视图中 并在页面上展示
          User user = new User();
          user.setAge(15);
          user.setUname("kobe");
          model.addAttribute("user", user);
      }
  
      /**
       * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
       * 这时如果不想跳转 可以调用ServletAPI指明处理逻辑 自己调用API进行转发时 并不使用配置的视图解析器 因此路径/文件后缀需要写全
       */
      @GetMapping("/voidForward")
      public void retVoidForward( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          System.out.println("方法返回值为Void类型，使用原生ServletAPI进行转发处理");
          //使用ServletAPI自行处理后续逻辑
          //使用转发 路径写法与重定向有区别  自己调用转发方法时，并不再使用配置的视图解析器对象，因此路径需要自己配置
          //转发可以直接请求WEB/INF下的内容
          request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
          //后续方法不再继续
          return;
      }
  
      /**
       * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
       * 这时如果不想跳转 可以调用ServletAPI指明处理逻辑 自己调用API进行转发时 并不使用配置的视图解析器 因此路径/文件后缀需要写全
       */
      @GetMapping("/voidRedirect")
      public void retVoidRedirect( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          System.out.println("方法返回值为Void类型,使用重定向");
          //创建一个对象 使用Model存入视图中 并在页面上展示
          //使用ServletAPI自己进行后续逻辑的处理
          //使用重定向 路径写法与重定向有区别
          //重定向是两次请求不能直接请求WEB/INF下的内容
          response.sendRedirect(request.getContextPath()+"/index.jsp");
          //后续方法不再继续
          return;
      }
  
      /**
       * 当方法返回值为void类型时，默认会将path中的路径当作视图名称去解析
       * 使用response调用输出流直接进行处理
       */
      @GetMapping("/voidWriter")
      public void retVoidWriter( HttpServletResponse response) throws ServletException, IOException {
          System.out.println("方法返回值为Void类型,使用response输出流直接响应");
          //设置编码 防止中文乱码
          response.setCharacterEncoding("UTF-8");
          //设置请求头
          response.setContentType("text/html;charset=UTF-8");
          //调用输出流 写出响应内容
          response.getWriter().print("Hi this is a message sent from response.getWriter().print()");
          return;
      }
  ```

ModelAndView

- 使用new 创建ModelAndView对象，并可以向该对象填入需要的属性，设置视图的名称。

- 返回String类型解析视图的过程实际也是返回的ModelAndView对象,设置视图名称时无需后缀和具体路径，只需要逻辑名称

- ```java
	    /**
	     * 返回值为ModelAndView类型
	     */
	    @GetMapping("modelAndView")
	    public ModelAndView retModelAndView() {
	        ModelAndView mv = new ModelAndView();
	        //添加属性
	        User user = new User();
	        user.setAge(15);
	        user.setUname("kobe");
	        mv.addObject("user", user);
	        //设置视图名称
	        mv.setViewName("success");
	        return mv;
	    }
	```

使用关键字进行转发和重定向

- 使用forward关键字进行转发:路径仍然需要写到具体路径

- ```java
	    /**
	     * 使用forward关键字进行转发
	     */
	    @GetMapping("/forward")
	    public String retForward() {
	        System.out.println("使用forward关键字进行转发……");
	        return "forward:/WEB-INF/pages/success.jsp";
	    }
	
	```

- 使用redirect关键字进行重定向:路径中无需写项目名

- ```java
	    /**
	     * 使用redirect关键字进行转发
	     */
	    @GetMapping("/redirect")
	    public String retRedirect() {
	        System.out.println("使用redirect关键字进行重定向……");
	        //此处不加项目名称
	        return "redirect:/index.jsp";
	    }
	```

	

使用ResponseBody响应json数据

- ```java
	    /**
	     * 发送ajxa请求，将请求中的数据存入javabean
	     * 需要引入jackson进行类型转换
	     */
	
	    @PostMapping("/ajax")
	    public @ResponseBody User testAjax(@RequestBody User user) {
	        System.out.println("测试ajax请求……");
	        System.out.println(user);
	        //响应
	        user.setUname("lebron");
	        user.setAge(45);
	        return user;
	    }
	```

配置前端控制器不拦截静态资源

- 在springmvc.xml中配置

- ```xml
	  <!--配置前端控制器 不拦截静态资源-->
	    <mvc:resources mapping="/js/**" location="/js/"/>
	```

类型转换

- 引入jackson坐标

- ```xml
	
	        <!--引入jackson 用于类型转换-->
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-annotations</artifactId>
	            <version>2.11.1</version>
	        </dependency>
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-databind</artifactId>
	            <version>2.11.1</version>
	        </dependency>
	        <dependency>
	            <groupId>com.fasterxml.jackson.core</groupId>
	            <artifactId>jackson-core</artifactId>
	            <version>2.11.1</version>
	        </dependency>
	```

	

ajax异步请求

- ```jsp
	
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
	```

	

## 4.Spring MVC文件上传



1.SpringMVC上传文件，form表单配置：

- `enctype`需要设置为`multipart/form-data`(默认值是:`application/x-www-form-urlencoded`,表示键值对的形式传递)
- `method`使用`post`
- `使用<input type="file"/>`来提供文件选择按钮

2.`pom.xml`导入上传文件所需坐标:

```xml
        <!--上传文件所需依赖-->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.3</version>
        </dependency>
```

3.最原始的上传方式

```java
    /*使用原生ServletAPI完成文件上传*/
    @PostMapping("/upload1")
    public String upload1(HttpServletRequest request) throws Exception {
        //设置上传文件的目录
        String path = request.getSession().getServletContext().getRealPath("/upload1/");
        //判断目录是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem item : fileItems) {
            if (item.isFormField()) {

            } else {
                //是上传文件项
                //获取文件名
                String name = item.getName();
                //为文件生成唯一uuid
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                item.write(new File(path, uuid+name));
                //清除中间过程的临时文件
                item.delete();
            }
        }
        return "success";
    }
```

4.使用SpringMVC上传文件

- 原理:由前端控制器调用文件解析器，进行文件解析，返回文件对象。

- 配置文件解析器:

- ```xml
	    <!--文件解析器 由前端控制器调用 解析request为文件返回给后端    -->
	    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	        <!-- 支持的文件大小上限-->
	        <property name="maxUploadSize" value="10485760"/>
	    </bean>
	```

- ```java
	    /**
	     * 使用SpringMVC完成文件上传
	     * 配置文件解析器组件 返回解析后的文件对象
	     */
	    @PostMapping("/upload2")                     //此处形参需要与form表单中属性名一致
	    public String upload2(HttpServletRequest request, MultipartFile upload2) throws Exception {
	        //设置上传文件的目录
	        String path = request.getSession().getServletContext().getRealPath("/upload1/");
	        //判断目录是否存在
	        File file = new File(path);
	        if (!file.exists()) {
	            file.mkdirs();
	        }
	
	        //是上传文件项
	        //获取文件名
	        String name = upload2.getOriginalFilename();
	        //为文件生成唯一uuid
	        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	        upload2.transferTo(new File(path, uuid + name));
	        return "success";
	    }
	```

5.跨服务器文件上传
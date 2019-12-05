# 为什么需要servlet技术

需求：我们希望用户可以发帖，用户还可以回复，这样一些和用户可以交互的功能，用普通的java技术就完成不了，sun就开发了servlet技术供程序员使用

# servlet的介绍

+ servlet其实就是java程序（java类）
+ 该java程序（java类）要遵循servlet**开发规范**
+ servlet是运行在服务器端
+ servlet功能强大，几乎可以完成网站所有功能

+ 是学习jsp基础

# tomcat和servlet在网络中的位置

![image-20191126180848868](C:\Users\Yankfu\AppData\Roaming\Typora\typora-user-images\image-20191126180848868.png)

servlet的快速入门案例

## 开发servlet的三种方法

+ 实现servlet接口
+ 继承Genericservlet接口
+ 继承httpservlet

需求如下：请使用实现接口的方式来开发一个servlet，要求该servlet可以返回显示hello，world，同时显示当前时间



步骤：

1.建立一个web应用servletWeb

2.在servletWeb下建立WEB-INF/web.xml

3.在servletWeb下建立classes目录（servlet就要在该目录下开发）建立 lib文件夹

4.开发MyFirstServlet.java

```java
package com.servletWeb;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

class MyFirstServlet implements Servlet
{
	//该函数用于初始化servlet,就是把该servlet装载到内存中
	//该函数只会被调用一次
	public void init(ServletConfig config) throws ServletException
	{
		
	}
	
	//得到servletonfig对象
	public ServletConfig getServletConfig()
	{
		return null;
	}
	
	//该函数是服务函数，我们的业务逻辑代码就是写在这里的
	//该函数每次都会被调用
	public void service(ServletRequest req,
            ServletResponse res)
     throws ServletException,
            java.io.IOException{
		
	}
	
	//该函数得到servlet配置信息
	public java.lang.String getServletInfo()
	{
		return null;
	}
	
	//销毁该servlet，从内存中清除，该函数被调用一次
	public void destroy() {
		
	}
}
```

5.根据servlet规范，我们还需要部署Servlet



```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
 Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
  version="4.0"
  metadata-complete="true">
 <!-- 根据servlet规范，需要将Servlet部署到web.xml文件 -->
  <servlet>
 <!-- 是将来访问该servlet的资源名，该名字可以自己定义，默认就用该servlet的名字 -->
    <servlet-name>MyFirstServlet</servlet-name>
 <!-- 要指明该servlet放在哪个包下 形式是包/包/.../类 -->
    <servlet-class>com.servletWeb.MyFirstServlet</servlet-class>
  </servlet>
 <!-- servlet的映射 -->
  <servlet-mapping>
 <!-- 这个名字要跟上面那个的名字一样-->
    <servlet-name>MyFirstServlet</servlet-name>
 <!-- url-pattern可以自己定义 -->
      <url-pattern>/ABC</url-pattern>
  </servlet-mapping>
</web-app>

```





如果使用 javac 去编译一个java文件，则需要带命令参数javac -d . java文件

如何不重启tomcat就指定去reload一个web应用

进入到tomcat的manager页面然后找到自己的reload就行





# servlet 生命周期/servlet究竟是怎样工作的



当servlet第一次被调用的时候会触发init函数，该函数会把servlet实力装载到内存，init函数只会被调用一次

然后去调用servlet的service函数

当第二次以后访问servlet就直接调用service函数

当tomcat reload或者关闭tomcat或者关机都会调用destory函数

-----

使用继承httpServlet的方法来开发Servlet

1. 在软件公司 90% 都是通过该方法开发
2. 举例说明，还是显示hello，world 当前日期
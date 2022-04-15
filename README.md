# 博客（MicroBlog）

## 简介：

**博客**是一款基于Servlet/JSP编写的后端应用软件，具备新用户注册、用户登录、发表博客等功能的。**博客**相关功能的实现参考书籍<b>《JSP&Servlet学习笔记（第二版）》</b>，代码遵循Apache v2 License开源协议。

## 屏幕截图

### 用户登录页面

![用户登录页面](screenshots/screenshot1.png "用户登录页面")

### 用户注册页面

![用户注册页面](screenshots/screenshot2.png "用户注册页面")

![用户注册成功](screenshots/screenshot3.png "用户注册成功")

### 用户登录后发表博客页面

![用户登录](screenshots/screenshot4.png "用户登录")

![发表博客](screenshots/screenshot5.png "发表博客")

### 注销用户后展示最近博客

![注销用户后展示最近文章](screenshots/screenshot6.png "注销用户后展示最近文章")

## 开发环境

### 1、Java版本：1.8.0_131

### 2、MySQL版本：5.5.62

### 3、Tomcat版本：8.5.73

## 项目部署到Tomcat（版本：8.0.47）步骤如下：

### 1、下载项目源码，修改项目MicroBlog\web\META-INF\context.xml文件中的数据库连接密码，如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context antiJARLocking="true" path="/MicroBlog">
    <Resource name="jdbc/gossip"
              auth="Container" type="javax.sql.DataSource"
              maxActive="100" maxIdle="30" maxWait="10000" username="root"
              password="你自己的数据库密码" driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/gossip?
useUnicode=true&amp;characterEncoding=UTF-8"/>
    <Realm className="org.apache.catalina.realm.DataSourceRealm"
           localDataSource="true"
           dataSourceName="jdbc/gossip"
           userTable="t_account" userNameCol="name" userCredCol="password"
           userRoleTable="t_account_role" roleNameCol="role"/>
</Context>
```

### 2、编译运行成功后，将项目打包成war包。

### 3、将打包好的war包放到Tomcat安装目录下的webapps目录下，并解压。

### 3、启动tomcat软件，访问http://localhost:8080/MicroBlog即可。

## 许可

> Copyright (C) liuhaiyang, Ganzhou Quxiang Inc. Open source codes for study only. Do not use for commercial purpose.
>
> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
>
> [http://www.apache.org/licenses/LICENSE-2.0](https://gitee.com/link?target=http%3A%2F%2Fwww.apache.org%2Flicenses%2FLICENSE-2.0)
>
> Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


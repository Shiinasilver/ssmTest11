项目简介
基于 Spring Boot + MySQL + Thymeleaf 的用户管理系统，实现完整的用户增删改查功能，包含登录注册、权限控制等模块。

功能特性
用户认证

登录注册功能

安全退出功能

用户管理

用户列表展示

用户信息增删改查

用户搜索功能

用户状态管理

权限控制

管理员 / 普通用户角色区分

页面访问权限控制

操作权限验证

技术栈
后端技术
Spring Boot 3.2.0 - 项目框架

Spring MVC - Web层框架

MyBatis - 数据持久层

MySQL 8.0 - 数据库

Druid - 数据库连接池

Thymeleaf - 模板引擎

前端技术
HTML5 - 页面结构

JavaScript - 交互逻辑

Thymeleaf - 模板语法

项目目录结构
text
smlbmv/
├── src/main/java/com/example/smlbmv/
│   ├── controller/
│   │   ├── LoginController.java
│   │   ├── HomeController.java
│   │   └── UserController.java
│   ├── entity/
│   │   └── SmlbmUdos.java
│   ├── service/
│   │   ├── UserService.java
│   │   └── UserServiceImpl.java
│   ├── mapper/
│   │   └── UserMapper.java
│   └── Application.java
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html
│   │   ├── register.html
│   │   ├── index.html
│   │   └── user/
│   │       ├── list.html
│   │       ├── add.html
│   │       ├── edit.html
│   │       └── view.html
│   ├── mapper/
│   │   └── UserMapper.xml
│   ├── application.properties
│   └── pom.xml

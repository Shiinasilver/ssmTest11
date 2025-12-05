项目简介
基于Spring Boot + MySQL + Thymeleaf的用户管理系统，实现完整的用户增删改查功能，包含登录注册、权限控制等模块。

功能特性
1.用户认证
2.安全退出功能
3.用户管理
4.用户列表展示
5.用户信息增删改查
6.用户搜索功能
7.用户状态管理
8.权限控制
9.管理员/普通用户角色区分
10.页面访问权限控制
11.操作权限验证

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

smbms/
├── src/main/java/com/example/smbms/
│   ├── controller/           # 控制器层
│   │   ├── LoginController.java     # 登录注册控制器
│   │   ├── HomeController.java      # 首页控制器
│   │   └── UserController.java      # 用户管理控制器
│   ├── entity/              # 实体类
│   │   └── SmbmsUser.java          # 用户实体
│   ├── service/             # 服务层
│   │   ├── UserService.java        # 用户服务接口
│   │   └── impl/UserServiceImpl.java # 用户服务实现
│   ├── mapper/              # 数据访问层
│   │   └── UserMapper.java         # 用户Mapper接口
│   └── Application.java     # 应用启动类
├── src/main/resources/
│   ├── templates/           # 页面模板
│   │   ├── login.html              # 登录页面
│   │   ├── register.html           # 注册页面
│   │   ├── index.html              # 首页
│   │   └── user/                   # 用户管理页面
│   │       ├── list.html           # 用户列表
│   │       ├── add.html            # 添加用户
│   │       ├── edit.html           # 编辑用户
│   │       └── view.html           # 用户详情
│   ├── mapper/              # MyBatis映射文件
│   │   └── UserMapper.xml          # 用户SQL映射
│   └── application.properties      # 配置文件
└── pom.xml                 # Maven配置

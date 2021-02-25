

## 项目简介

网上书城，一个基于MVC架构的web项目

具体功能

* 图书模块：主页面展示图书信息，图书的分页、查询功能

* 用户模块：注册、登录、添加图书至购物车、生成订单、查看购物详情
* 管理员模块：图书进行增删改查、订单发货功能

## 开发环境

* JDK版本：1.8
* 服务器：Tomcat
* 数据库管理系统：MySQL

建表语句和表数据导入sql文件即可

## 版本说明

版本v1.0

没有使用框架，采用servlet + JDBC

版本v2.0

在版本v1.0的基础上进行改造，使用maven构建项目，dao层使用mybatis，整合框架使用spring，接收请求仍使用原生servlet

版本v2.0具体更新内容

* dao层使用mybatis，无需手写dao接口实现类，通过dao动态代理实现dao接口中方法的调用
* 对象交给spring容器统一创建，管理
  * 使用druid连接池代替mybaits内置的连接池
  * spring集成mybatis，SqlSessionFactory、dao对象由spring容器创建
* 当web项目启动时，使用监听器创建spring容器并将其存入全局作用域ServletContext中，使得系统运行期间只使用一个spring容器里的对象
* 使用实现aop技术的aspectj框架生成代理对象做事务管理，给所有执行非只读SQL语句的业务方法添加事务管理功能


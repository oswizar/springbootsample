# Spring Boot Sample Project

这是一个基于Spring Boot的示例项目，集成了多种常用技术和框架，用于演示如何在Spring Boot应用中使用各种功能。

## 项目概述

本项目是一个综合性的Spring Boot示例应用，展示了如何集成和使用多种技术栈，包括数据库访问、安全控制、消息队列、缓存等。

## 技术栈

- **核心框架**: Spring Boot 3.5.11
- **编程语言**: Java 17
- **构建工具**: Maven
- **数据库**: MySQL
- **ORM 框架**: MyBatis Plus 3.5.16
- **数据库连接池**: Druid 1.2.16
- **缓存**: Redis + Redisson 4.3.0 (分布式锁)
- **安全框架**: Spring Security + JWT 0.11.5
- **消息队列**: Apache Kafka
- **搜索引擎**: Elasticsearch
- **微服务治理**: Alibaba Sentinel 2025.1.0.0
- **支付集成**: 支付宝 SDK 4.40.674
- **二维码生成**: ZXing 3.3.3
- **其他**: Lombok, AOP, Validation, Hutool 5.8.40, Gson

## 功能模块

### 1. 用户认证与授权
- 基于JWT的用户认证
- Spring Security权限控制
- 自定义过滤器和异常处理器

### 2. 数据访问层
- 使用MyBatis Plus简化数据库操作
- Druid数据库连接池配置
- MySQL数据库集成

### 3. 缓存与分布式锁
- Redis缓存集成
- Redisson实现分布式锁
- 多种Redis工具类封装

### 4. 消息队列
- Kafka消息生产者和消费者示例

### 5. 搜索引擎
- Elasticsearch集成
- 商品实体的全文搜索功能

### 6. 支付功能
- 支付宝支付接口集成
- 二维码生成工具

### 7. 邮件服务
- 邮件发送功能
- 支持HTML模板邮件

### 8. 工具类
- 多种工具类封装，包括日期处理、加密解密、HTTP请求等
- 统一响应结果封装
- 全局异常处理

## 项目结构

```
src/main/java/com/oswizar/springbootsample/
├── config/          # 配置类（安全、Redis、拦截器等）
├── controller/      # 控制器层
├── entity/          # 数据库实体类
├── filter/          # 过滤器
├── mapper/          # 数据访问层接口
├── message/         # 消息队列相关
├── model/           # 数据模型
├── service/         # 业务逻辑层
│   └── impl/        # 业务逻辑实现
└── util/            # 工具类
```

## 环境配置

### 数据库配置
- MySQL 数据库，连接地址：`jdbc:mysql://localhost:3306/test`
- 用户名：`root`
- 密码：`123456`

### Redis 配置
- Redis 服务器地址：`localhost:6379`
- 密码：`123456`
- 连接超时：`1000ms`
- 连接池最大连接数：`200`

### Elasticsearch 配置
- 地址：`http://localhost:9200`
- 连接超时：`1s`
- 读取超时：`30s`

### Kafka 配置
- 服务器地址：`localhost:9092`

### Sentinel 配置
- 控制台地址：`localhost:8080`
- 应用端口：`8719`

### 邮件服务配置
- SMTP 服务器：`smtp-mail.outlook.com`
- 端口：`587`
- 用户名：`oswizar@outlook.com`
- 开启 TLS 加密

## 快速开始

### 环境要求
- Java 17+
- Maven 3.6+
- MySQL 5.7+
- Redis
- Elasticsearch
- Kafka
- Sentinel Dashboard (可选)

### 构建和运行

1. 克隆项目到本地
```bash
git clone <项目地址>
```

2. 创建数据库
```sql
CREATE DATABASE test CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

3. 修改配置文件 `src/main/resources/application.properties` 中的相关配置

4. 使用Maven构建项目
```bash
mvn clean install
```

5. 运行项目
```bash
mvn spring-boot:run
```

或者
```bash
java -jar target/springbootsample.jar
```

## API 接口

项目启动后，可通过以下地址访问 API:

- **基础路径**: `http://localhost:8888/springbootsample`
- **Hello World 示例**: `GET /springbootsample/index`
- **用户认证**: `POST /springbootsample/user/login`
- **图书管理**: `POST /springbootsample/book/findBookById`
- **作者管理**: `POST /springbootsample/author/findAuthorById`
- **Redis 分布式锁测试**: `/springbootsample/redisLockTest`
- **Kafka消息发送**: `POST /springbootsample/api/kafka/send`
- **邮件发送**: `GET /springbootsample/sendMail/simpleMail`
- **支付宝支付**: `/springbootsample/alipay/pagePay`
- **Sentinel 监控**: 需配置 Sentinel Dashboard 访问

## 安全说明

- 系统使用JWT进行身份验证
- 密码使用BCrypt加密存储
- 敏感信息如数据库密码、邮件密码等应通过环境变量配置

## 许可证

本项目仅供学习和参考使用。
[![Build Status](https://travis-ci.org/zhoutaoo/SpringCloud.svg?branch=master)](https://travis-ci.org/zhoutaoo/SpringCloud)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![codecov](https://codecov.io/gh/zhoutaoo/SpringCloud/branch/master/graph/badge.svg)](https://codecov.io/gh/zhoutaoo/SpringCloud)

## 快速开始

### 先决条件

- [git](https://git-scm.com/)
- [java8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
- [maven](http://maven.apache.org/) 
- [mysql]()
- [redis](http://redis.io/download)
- [rabbitmq](http://rabbitmq.io/download)

### 开发环境

1. 克隆代码库： `svn 地址`

2. 生成ide配置： `mvn idea:idea` 并导入对应的ide进行开发,IDE安装lombok插件

### 编译 & 启动

* 1.启动基础服务：`docker-compose -f docker-compose.yml` 或单个启动`docker-compose up 服务名`

|  服务         |   服务名         |  端口     | 备注                                            |
|--------------|-----------------|-----------|------------------------------------------------|
|  数据库       |   mysql          |  3306     |  目前各应用共用1个实例，各应用可建不同的database    |
|  KV缓存       |   redis         |  6379     |  目前共用，原则上各应用单独实例    |
|  消息中间件    |   rabbitmq      |  5672     |  未使用后期可能会用                         |
|  日志收集中间件 |   zipkin-server |  9411     |  未使用后期可能会用                          |
|  搜索引擎中间件 |   elasticsearch |  9200     |  未使用后期可能会用    |
|  日志分析工具   |   kibana        |  5601     |  未使用后期可能会用    |
|  数据可视化工具 |   grafana       |  3000     |  未使用后期可能会用    |

* 2.创建数据库及表

**子项目脚本**

路径一般为：svn


* 3.启动应用： `mvn springboot:run` 

| 服务分类  | 服务名                     |   简介     |  应用地址                | 文档 |
|----------|---------------------------|-----------|-------------------------|------|
|  center  | eureka-server             | 注册中心   |  http://localhost:7070  |      |
|  center  | bus-server                | 消息中心   |  http://localhost:7071  |      |
|  center  | config-server             | 配置中心   |  http://localhost:7072  |  |
|  component| etcdlock                 | 分布式锁   |  jar包引入  |    |
|  component| id-generator             | 主键生成器 |   jar包引入| |
|  component| job                      | 任务调度中心 |  jar包引入              |      |
|  component| rediscache               | redis缓存  |  jar包引入 |      |
|  console  | console                  | 运营平台    |  http://localhost:8081 |      |
|  mock     | bigdata                  | 大数据风控 |  http://localhost:9081 |      |
|  mock     | payment                  | 支付平台 |  http://localhost:9082 |      |
|  portal   | portal                   | 信贷门户 |  http://localhost:8082 |      |
|  service  | account                  | 账户服务 |  http://localhost:8083 |      |
|  service  | borrow                   | 贷前服务 |  http://localhost:8084 |      |
|  service  | collect                  | 信息收集服务 |  http://localhost:8085 |      |
|  service  | lend                     | 贷中贷后服务 |  http://localhost:8086 |      |
|  service  | member                   | 会员服务 |  http://localhost:8087 |      |
|  service  | product                  | 产品服务 |  http://localhost:8088 |      |
|  service  | rule                     | 规则引擎服务 |  http://localhost:8089 |      |

### 测试

运行 `mvn test` 启动测试.


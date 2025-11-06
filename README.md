# 校园选课系统（v1.1.2）

## 一、项目说明
### 1.1 项目背景
本项目是学期贯穿式实践项目的迭代版本，目标是构建一个**单体架构的校园选课与教学资源管理平台**。通过该项目，可掌握Spring Boot开发流程、RESTful API设计规范、数据库交互及容器化部署等技术，为后续扩展为微服务架构奠定基础。

### 1.2 核心功能
项目围绕“课程管理、学生管理、选课管理”三大核心模块展开，覆盖校园选课场景的基础业务需求，具体功能如下：
- **课程管理**：支持课程的查询（所有/单个）、创建、更新、删除操作，维护课程基本信息（编号、名称、教师、上课时间、容量等）。
- **学生管理**：支持学生的查询（所有/单个）、创建、更新、删除操作，自动生成学生唯一ID和创建时间戳，校验学号唯一性与邮箱格式合法性。
- **选课管理**：支持学生选课、退课及选课记录查询（全量/按课程/按学生），并实现课程容量限制、重复选课检查等业务规则。

### 1.3 技术栈
- 核心框架：Spring Boot 3.4.x（Web模块）
- 数据校验：Spring Boot Starter Validation
- 数据存储：
    - 开发环境：H2 内存数据库
    - 生产环境：MySQL 8.0
- API文档：Swagger
- 部署工具：Docker
- 测试工具：Postman/Apifox/curl
- 项目构建：Maven


## 二、运行项目
### 2.1 环境准备
- JDK版本：JDK 17及以上
- Maven版本：Maven 3.8.x及以上
- Docker版本（可选）：20.10.x及以上
- 开发工具（可选）：IntelliJ IDEA/Eclipse

### 2.2 项目获取
从Git仓库克隆项目：
```bash
git clone https://github.com/point423/Schoolproject.git
```

### 2.3 本地运行（非容器化）
#### 2.3.1 开发环境（H2内存数据库）
```bash
# 直接启动（默认开发环境）
mvn spring-boot:run

# 或显式指定环境
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"
```

#### 2.3.2 生产环境（MySQL数据库）
1. 初始化数据库：
```bash
# 创建数据库
CREATE DATABASE IF NOT EXISTS course_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 执行表结构脚本
mysql -u root -p course_db < src/main/resources/db/schema.sql

# 执行测试数据脚本
mysql -u root -p course_db < src/main/resources/db/data.sql
```

2. 修改配置文件`src/main/resources/application-prod.yml`中的MySQL连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/course_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: 你的数据库用户名
    password: 你的数据库密码
```

3. 启动服务：
```bash
# Windows PowerShell
mvn spring-boot:run -D"spring-boot.run.profiles=prod"

# Linux/Mac
mvn spring-boot:run "-Dspring-boot.run.profiles=prod"
```

### 2.4 Docker部署（推荐）
#### 2.4.1 构建Docker镜像
```bash
# 进入项目根目录
cd Schoolproject

# 构建Maven包
mvn clean package -DskipTests

# 构建Docker镜像（标签格式：仓库名:版本）
docker build -t course-system:3.0.0 .
```

#### 2.4.2 启动服务
##### 开发环境（H2数据库）
```bash
docker run -d \
  --name course-service-dev \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  course-system:3.0.0
```

##### 生产环境（MySQL数据库）
```bash
docker run -d \
  --name course-service-prod \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL="jdbc:mysql://mysql-host:3306/course_db?useSSL=false&serverTimezone=UTC" \
  -e SPRING_DATASOURCE_USERNAME=your_username \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  --link mysql-host:mysql \  # 若MySQL在容器中，需关联容器
  course-system:3.0.0
```

### 2.5 系统访问验证
1. **健康检查接口**：
```bash
curl http://localhost:8080/health/db
# 预期响应：{"status":"UP","db_type":"H2/MySQL","message":"数据库连接成功"}
```

2. **Swagger API文档**：
   访问 http://localhost:8080/swagger-ui.html 查看接口列表

3. **H2数据库控制台（仅开发环境）**：
    - 地址：http://localhost:8080/h2-console
    - JDBC URL: `jdbc:h2:mem:course_db`
    - 用户名: `sa`
    - 密码：空


## 三、API接口列表
所有接口均返回**统一JSON格式**，成功响应`code=200`，错误响应包含对应错误码（如400/404）及提示信息。

### 3.1 课程管理API
| 接口功能       | HTTP方法 | 接口路径                | 请求体/参数                | 响应说明                     |
|----------------|----------|-------------------------|----------------------------|------------------------------|
| 查询所有课程   | GET      | /api/courses            | 无                         | 返回所有课程列表（空列表或课程数组） |
| 查询单个课程   | GET      | /api/courses/{id}       | 路径参数：课程ID           | 成功返回课程详情，失败返回404（课程不存在） |
| 创建课程       | POST     | /api/courses            | Content-Type: application/json<br>示例：<br>{<br>"code":"CS101",<br>"title":"计算机科学导论",<br>"instructor":{"id":"T001","name":"张教授","email":"zhang@example.edu.cn"},<br>"schedule":{"dayOfWeek":"MONDAY","startTime":"08:00","endTime":"10:00","expectedAttendance":50},<br>"capacity":60<br>} | 成功返回201（创建成功），失败返回400（参数缺失） |
| 更新课程       | PUT      | /api/courses/{id}       | 路径参数：课程ID<br>请求体：同创建课程（按需修改字段） | 成功返回200（更新后课程），失败返回404（课程不存在） |
| 删除课程       | DELETE   | /api/courses/{id}       | 路径参数：课程ID           | 成功返回204（无内容）或200，失败返回404 |

### 3.2 学生管理API
| 接口功能       | HTTP方法 | 接口路径                | 请求体/参数                | 响应说明                     |
|----------------|----------|-------------------------|----------------------------|------------------------------|
| 创建学生       | POST     | /api/students           | Content-Type: application/json<br>示例：<br>{<br>"studentId":"2024001",<br>"name":"李同学",<br>"major":"计算机科学与技术",<br>"grade":2024,<br>"email":"li@example.edu.cn"<br>} | 成功返回201（含系统生成的id和createdAt），失败返回400（学号重复/邮箱格式错误/参数缺失） |
| 查询所有学生   | GET      | /api/students           | 无                         | 返回所有学生列表（空列表或学生数组） |
| 查询单个学生   | GET      | /api/students/{id}      | 路径参数：学生ID（系统生成的UUID） | 成功返回学生详情，失败返回404（学生不存在） |
| 更新学生       | PUT      | /api/students/{id}      | 路径参数：学生ID<br>请求体：同创建学生（按需修改字段） | 成功返回200（更新后学生），失败返回404（学生不存在）或400（学号重复/邮箱格式错误） |
| 删除学生       | DELETE   | /api/students/{id}      | 路径参数：学生ID           | 成功返回204或200，失败返回404（学生不存在）或400（存在选课记录） |

### 3.3 选课管理API
| 接口功能       | HTTP方法 | 接口路径                | 请求体/参数                | 响应说明                     |
|----------------|----------|-------------------------|----------------------------|------------------------------|
| 学生选课       | POST     | /api/enrollments        | Content-Type: application/json<br>示例：<br>{<br>"courseId":"课程ID",<br>"studentId":"2024001"<br>} | 成功返回201（选课记录），失败返回400（容量已满/重复选课）或404（课程/学生不存在） |
| 学生退课       | DELETE   | /api/enrollments/{id}   | 路径参数：选课记录ID       | 成功返回204或200，失败返回404（选课记录不存在） |
| 查询所有选课记录 | GET      | /api/enrollments        | 无                         | 返回所有选课记录列表         |
| 按课程查询选课记录 | GET    | /api/enrollments/course/{courseId} | 路径参数：课程ID | 返回该课程的所有选课记录，失败返回404（课程不存在） |
| 按学生查询选课记录 | GET    | /api/enrollments/student/{studentId} | 路径参数：学生学号（如2024001） | 返回该学生的所有选课记录，失败返回404（学生不存在） |


## 四、测试说明
### 4.1 测试工具
推荐使用 **Postman** 或 **Apifox**（可视化工具），或通过 `curl` 命令行测试。

### 4.2 核心测试场景
需覆盖以下4类场景，确保功能完整性与业务规则有效性：

#### 场景1：完整课程管理流程
1. 创建课程 → 查询所有课程 → 查询单个课程 → 更新课程 → 删除课程 → 验证删除结果

#### 场景2：选课业务流程
1. 创建容量为2的课程 → 2名学生成功选课 → 第3名学生选课失败（容量满） → 验证选课人数

#### 场景3：学生管理流程
1. 创建学生 → 查询学生 → 更新学生信息 → 验证无效操作（删除有选课记录的学生）

#### 场景4：错误处理验证
1. 查询不存在的资源 → 参数缺失校验 → 无效邮箱格式校验 → 重复学号校验

### 4.3 测试文档提交
需生成测试文档（支持Markdown或HTTP文件），记录请求信息、响应结果及问题解决方案。


## 五、Docker部署补充说明
### 5.1 查看容器日志
```bash
# 实时查看日志
docker logs -f course-service-dev

# 查看最后100行日志
docker logs --tail 100 course-service-prod
```

### 5.2 常见问题排查
# 问题及解决方案

## 1. profile 特定配置文件中配置 spring.profiles.active 不允许
- **问题**：在 profile 特定配置文件（如 application-docker.yml）中配置 `spring.profiles.active`，Spring Boot 会报错。
- **解决方案**：
    1. 删除 application-docker.yml 中的以下配置：
       ```yaml
       spring:
         profiles:
           active: docker  # 删除此行
       ```
    2. 在主配置文件 application.yml 中指定激活环境：
       ```yaml
       spring:
         profiles:
           active: docker  # 激活 docker 环境，自动加载 application-docker.yml
       ```


## 2. 原有 Maven 和 JDK 镜像不可用
- **问题**：`maven:3.9-openjdk-17` 和 `openjdk:17-slim` 镜像无法拉取或使用。
- **解决方案**：替换为可用镜像：
    - Maven 镜像：`maven:3.8-openjdk-17`
    - JDK 镜像：`eclipse-temurin:17-jdk-alpine`


## 3. 修改 application-docker.yml 后未生效
- **问题**：修改 application-docker.yml 后，容器未加载新配置。
- **解决方案**：重新构建镜像并启动服务：
  ```bash
  # 重新构建镜像
  docker compose build
  # 后台启动服务
  docker compose up -d
  ```


## 4. 镜像体积过大（超过 200MB）
- **问题**：构建的 Docker 镜像体积过大。
- **解决方案**：
    1. 避免使用 `distroless` 镜像（因缺少 shell 工具导致 RUN 命令失败）。
    2. 替换为轻量 JRE 镜像：`eclipse-temurin:17-jre-alpine`（体积更小且保留必要工具）。


## 5. 虚拟机磁盘满盘导致开机失败
- **问题**：虚拟机磁盘空间耗尽，无法正常开机。
- **解决方案**：清理 Docker 资源释放空间后重启：
  ```bash
  # 清理无用 Docker 镜像、容器、网络
  sudo docker system prune -a
  # 重启虚拟机
  sudo reboot
  ```
 

## 六、常见问题与解决方法
1. **生产环境数据库初始化**：需提前手动创建数据库并执行脚本，不可省略。
2. **SQL语法兼容**：`schema.sql`优先适配H2语法，MySQL不兼容部分已标注`TODO`，生产时需手动修改。
3. **测试类扫描问题**：`CourseRepositoryTest`需添加注解`@ContextConfiguration(classes = CourseApplication.class)`。
4. **数据库版本查询兼容**：H2使用`SELECT H2VERSION()`，MySQL使用`SELECT VERSION()`，已通过条件判断处理。
5. **字段映射错误**：`Schedule`类的`dayOfWeek`字段需添加`@Column(name = "SCHEDULE_DAY")`明确映射。
6. **Windows环境Maven参数问题**：PowerShell中需用引号包裹含`.`的参数（如`-D"spring-boot.run.profiles=prod"`）。
7. **JPQL查询歧义**：`findByEnrolledLessThanCapacity`方法需通过`@Query`明确字段比较逻辑。
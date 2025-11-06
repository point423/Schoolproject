# 第一阶段：构建阶段（使用Maven镜像编译打包）
FROM maven:3.8-openjdk-17 AS builder
WORKDIR /app
# 复制pom.xml和源代码
COPY pom.xml .
COPY src ./src
# 编译打包（跳过测试，加速构建）
RUN mvn clean package -DskipTests

# 第二阶段：运行环境（改用 alpine JRE，支持 shell 命令）
FROM eclipse-temurin:17-jre-alpine
# 非root用户运行（可选，提升安全性）
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup
USER appuser
# 设置工作目录
WORKDIR /app
# 从构建阶段复制JAR包
COPY --from=builder /app/target/SchoolFunction-1.1.0.jar app.jar
# 暴露应用端口
EXPOSE 8080
# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]

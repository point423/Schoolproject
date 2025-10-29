package com.zjsu.pjt.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据库健康检查接口（用于快速验证数据库连通性）
 */
@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {
    private final JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/db", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Map<String, Object>> checkDbHealth() {
        Map<String, Object> result = new HashMap<>();
        try {
            // 执行简单查询（查询数据库版本）
            // 获取数据库产品名称（H2会返回"H2"，MySQL返回"MySQL"）
            String dbProduct = jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
            String sql = "H2".equals(dbProduct) ? "SELECT H2VERSION()" : "SELECT VERSION()";
            String dbVersion = jdbcTemplate.queryForObject(sql, String.class);
            result.put("status", "UP");
            result.put("db_type", dbProduct); // 新增：添加数据库类型（如 "MySQL" 或 "H2"）
            result.put("db_version", dbVersion);
            result.put("message", "数据库连接成功");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.put("status", "DOWN");
            result.put("message", "数据库连接失败：" + e.getMessage());
            return ResponseEntity.status(503).body(result);
        }
    }
}
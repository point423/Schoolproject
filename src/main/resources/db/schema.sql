-- 课程表（courses）
CREATE TABLE IF NOT EXISTS courses (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    instructor_name VARCHAR(50) NOT NULL,
    instructor_email VARCHAR(100) NOT NULL,
    schedule_day VARCHAR(20) NOT NULL,
    schedule_start_time VARCHAR(10) NOT NULL,
    schedule_end_time VARCHAR(10) NOT NULL,
    capacity INT NOT NULL,
    enrolled INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 学生表（students）
CREATE TABLE IF NOT EXISTS students (
    id VARCHAR(36) PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    major VARCHAR(50) NOT NULL,
    grade INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 选课表（enrollments）- 仅保留表结构和唯一约束，索引单独创建
CREATE TABLE IF NOT EXISTS enrollments (
    id VARCHAR(36) PRIMARY KEY,
    course_id VARCHAR(36) NOT NULL,
    student_id VARCHAR(36) NOT NULL,
    enrolled_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('ACTIVE', 'DROPPED', 'COMPLETED') NOT NULL DEFAULT 'ACTIVE',  -- 改为ENUM类型，枚举值与EnrollmentStatus一致
    -- 唯一约束（已适配H2语法）
    CONSTRAINT uk_course_student UNIQUE (course_id, student_id)
);

-- 单独创建索引（H2无解析冲突）//TODO h2使用以下语句，mysql则去除IF NOT EXISTS 即可成功手动创建表
CREATE INDEX IF NOT EXISTS idx_enrollments_course ON enrollments(course_id);
CREATE INDEX IF NOT EXISTS idx_enrollments_student ON enrollments(student_id);
-- 插入测试课程数据
INSERT INTO courses (id, code, title, instructor_name, instructor_email, schedule_day, schedule_start_time, schedule_end_time, capacity, enrolled)
VALUES
('1', 'CS-101', '计算机基础', '李教授', 'li@school.com', 'MONDAY', '08:30', '10:00', 50, 10),
('2', 'MA-202', '高等数学', '王教授', 'wang@school.com', 'WEDNESDAY', '14:00', '15:30', 40, 8);

-- 插入测试学生数据
INSERT INTO students (id, student_id, name, major, grade, email)
VALUES
('1', '2023001', '张三', '计算机科学', 2023, 'zhangsan@school.com'),
('2', '2023002', '李四', '数学与应用数学', 2023, 'lisi@school.com');

-- 插入测试选课数据
INSERT INTO enrollments (id, course_id, student_id, status)
VALUES
('1', '1', '1', 'ACTIVE'),
('2', '2', '2', 'ACTIVE');
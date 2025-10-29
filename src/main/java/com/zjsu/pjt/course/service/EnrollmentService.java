package com.zjsu.pjt.course.service;

import com.zjsu.pjt.course.common.BusinessException;
import com.zjsu.pjt.course.common.ResourceNotFoundException;
import com.zjsu.pjt.course.enums.EnrollmentStatus;
import com.zjsu.pjt.course.model.Course;
import com.zjsu.pjt.course.model.Enrollment;
import com.zjsu.pjt.course.model.Student;
import com.zjsu.pjt.course.repository.CourseRepository;
import com.zjsu.pjt.course.repository.EnrollmentRepository;
import com.zjsu.pjt.course.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 选课业务逻辑
 */
@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    /**
     * 学生选课（核心业务：校验课程容量、避免重复选课）
     */
    @Transactional
    public Enrollment enrollCourse(Enrollment enrollment) {
        // 1. 校验课程和学生是否存在（直接通过 Repository 实现，不依赖其他 Service）
        Course course = courseRepository.findById(enrollment.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("课程不存在",enrollment.getCourseId()));
        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("学生不存在",enrollment.getCourseId()));

        // 2. 校验是否已选该课程（原逻辑不变）
        if (enrollmentRepository.existsByCourseIdAndStudentId(enrollment.getCourseId(), enrollment.getStudentId())) {
            throw new BusinessException("学生已选该课程", HttpStatus.CONFLICT);
        }

        // 3. 校验课程容量（调用 CourseService 的 incrementEnrolled 方法，这里需要保留对 CourseService 的依赖吗？）
        if (course.getEnrolled() >= course.getCapacity()) {
            throw new BusinessException("课程容量已满", HttpStatus.BAD_REQUEST);
        }
        course.setEnrolled(course.getEnrolled() + 1);
        courseRepository.save(course);

        // 4. 设置选课状态为ACTIVE，保存选课记录
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        return enrollmentRepository.save(enrollment);
    }

    /**
     * 学生退课（核心业务：更新课程已选人数、修改选课状态）
     */
    @Transactional
    public void dropCourse(String courseId, String studentId) {
        // 1. 校验选课记录是否存在（原逻辑不变）
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndStudentId(courseId, studentId)
                .orElseThrow(() -> new BusinessException("选课记录不存在", HttpStatus.NOT_FOUND));

        // 2. 校验是否已退课（原逻辑不变）
        if (enrollment.getStatus() == EnrollmentStatus.DROPPED) {
            throw new BusinessException("学生已退该课程", HttpStatus.BAD_REQUEST);
        }

        // 3. 修改选课状态为DROPPED，课程已选人数自减（直接操作 Repository）
        enrollment.setStatus(EnrollmentStatus.DROPPED);
        enrollmentRepository.save(enrollment);

        Course course = courseRepository.findById(courseId).get(); // 前面已校验过存在，直接get()
        course.setEnrolled(course.getEnrolled() - 1);
        courseRepository.save(course);
    }
    /**
     * 按课程ID查询活跃选课记录（修改后）
     */
    public List<Enrollment> getActiveEnrollmentsByCourseId(String courseId) {
        // 校验课程是否存在：直接通过CourseRepository实现，替代courseService.getCourseById()
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("课程不存在",courseId));
        // 原查询逻辑不变
        return enrollmentRepository.findByCourseIdAndStatus(courseId, EnrollmentStatus.ACTIVE);
    }

    /**
     * 统计课程活跃人数（修改后）
     */
    public Integer countActiveEnrollments(String courseId) {
        // 同样直接通过Repository校验课程存在
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("课程不存在",courseId));
        // 原统计逻辑不变
        return enrollmentRepository.countActiveByCourseId(courseId, EnrollmentStatus.ACTIVE);
    }

    // 校验关联记录的方法（hasEnrollmentsForCourse/Student）不变...

    /**
     * 检查课程是否存在关联的选课记录
     */
    public boolean hasEnrollmentsForCourse(String courseId) {
        return enrollmentRepository.existsByCourseId(courseId);
    }

    /**
     * 检查学生是否存在关联的选课记录
     */
    public boolean hasEnrollmentsForStudent(String studentId) {
        return enrollmentRepository.existsByStudentId(studentId);
    }
}
// BusinessException.java（业务异常，如重复选课、容量不足）
package com.zjsu.pjt.course.common;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}


// ResourceNotFoundException.java（资源不存在异常）
package com.zjsu.pjt.course.common;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String id) {
        super(resource + " not found with id: " + id);
    }
}
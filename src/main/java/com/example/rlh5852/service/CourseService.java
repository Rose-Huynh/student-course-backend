package com.example.rlh5852.service;

import com.example.rlh5852.model.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    void saveCourse(Course course);
    Course getCourseById(long id);
    void deleteCourseById(long id);
    Page<Course> findPaginated(int pageNo, int pageSize, String sortBy, String sortOrder);
}

package com.example.rlh5852.service;

import com.example.rlh5852.model.Course;
import com.example.rlh5852.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    @Override
    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public Course getCourseById(long id) {
        Optional<Course> optional = this.courseRepository.findById(id);
        Course course = null;
        if (optional.isPresent()) {
            course = optional.get();
        } else {
            throw new RuntimeException("Course not found for id:: " + id);
        }
        return course;
    }

    @Override
    public void deleteCourseById(long id) {this.courseRepository.deleteById(id);}
    @Override
    public Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.courseRepository.findAll(pageable);
    }
}

package com.example.rlh5852.repository;

import com.example.rlh5852.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Override
    Optional<Course> findById(Long id);

}

package com.example.rlh5852.controller;

import com.example.rlh5852.model.Course;
import com.example.rlh5852.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseRestController {
    @Autowired
    private CourseService courseService;
    @RequestMapping(value = "/course/", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

}

package com.example.rlh5852.controller;

import com.example.rlh5852.model.Course;
import com.example.rlh5852.model.Student;
import com.example.rlh5852.repository.CourseRepository;
import com.example.rlh5852.repository.StudentRepository;
import com.example.rlh5852.service.CourseService;
import com.example.rlh5852.service.StudentService;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);


    @Autowired
    private CourseService courseService;


    // display list of courses
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());
        return "index";
    }

    @RequestMapping("value=/HelloWorld")
    public String Helloworld(Model model) {
        return "Hello World";
    }

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        // create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save course to database
        courseService.saveCourse(course);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@PathVariable(value ="id") long id, Model model) {
        // get course from the service
        Course course = courseService.getCourseById(id);

        // set course as a model attribute to pre-populate the form
        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id") long id) {
        // call delete course method
        courseService.deleteCourseById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String page(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
                       @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItem", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "index";


    }




}

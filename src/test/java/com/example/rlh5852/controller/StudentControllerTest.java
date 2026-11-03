package com.example.rlh5852.controller;

import com.example.rlh5852.model.Student;
import com.example.rlh5852.repository.StudentRepository;
import com.example.rlh5852.service.CourseService;
import com.example.rlh5852.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {
    @Test
    void addStudentUsesExistingTemplateAndFormModel() {
        StudentController controller = new StudentController();
        ExtendedModelMap model = new ExtendedModelMap();
        assertEquals("new_student", controller.showNewStudentForm(model));
        assertInstanceOf(Student.class, model.get("student"));
    }

    @Test
    void listAndUpdateExposeStudentAndIdForTemplates() {
        StudentRepository repository = mock(StudentRepository.class);
        CourseService courses = mock(CourseService.class);
        Student student = new Student();
        student.setSid(7L);
        when(repository.findAll()).thenReturn(List.of(student));
        when(repository.findById(7L)).thenReturn(Optional.of(student));
        when(courses.getAllCourses()).thenReturn(List.of());
        StudentServiceImpl service = new StudentServiceImpl();
        ReflectionTestUtils.setField(service, "studentRepository", repository);
        StudentController controller = new StudentController();
        ReflectionTestUtils.setField(controller, "studentRepository", repository);
        ReflectionTestUtils.setField(controller, "studentService", service);
        ReflectionTestUtils.setField(controller, "courseService", courses);
        ExtendedModelMap model = new ExtendedModelMap();
        assertEquals("student_list", controller.viewStudentList(model));
        assertEquals(List.of(student), model.get("listStudents"));
        assertEquals("update_student", controller.showStudFormForUpdate(7, model));
        assertSame(student, model.get("student"));
        assertEquals(7L, student.getSid());
        assertEquals(List.of(), model.get("allCourses"));
        assertEquals("redirect:/studentList", controller.saveStudent(student));
        verify(repository).save(student);
        controller.deleteStudent(7);
        verify(repository).deleteById(7L);
    }
}

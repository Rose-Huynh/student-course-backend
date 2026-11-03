package com.example.rlh5852.service;

import com.example.rlh5852.model.Student;
import com.example.rlh5852.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class StudentServiceImplTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void getAllStudents() {
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setStudName("Student " + i);
            repository.save(student);
        }
        List<Student> items = repository.findAll();
        assertEquals(5, items.size());
    }

    @Test
    void testFindOne() {
        Student saved = new Student();
        saved.setStudName("David Peslak");
        saved = repository.saveAndFlush(saved);
        Student student = repository.findById(saved.getSid()).orElseThrow();
        assertEquals("David Peslak", student.getStudName());
    }
}

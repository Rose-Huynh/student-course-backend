package com.example.rlh5852.service;

import com.example.rlh5852.model.Course;
import com.example.rlh5852.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void saveStudent(Student student);
    Student getStudentById(int sid);
    void deleteStudentById(int sid);

    Student getStudentById(long sid);

    void deleteStudentById(long sid);

    Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}

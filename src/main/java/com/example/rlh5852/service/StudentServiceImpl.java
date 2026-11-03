package com.example.rlh5852.service;

import com.example.rlh5852.model.Student;
import com.example.rlh5852.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public Student getStudentById(int sid) {
        return getStudentById((long) sid);
    }

    @Override
    public void deleteStudentById(int sid) {
        deleteStudentById((long) sid);
    }

    @Override
    public Student getStudentById(long sid) {
        Optional<Student> optional = studentRepository.findById(sid);
        Student student = null;
        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new RuntimeException("Student not found for id :: "+ sid);
        }
        return student;
    }

    @Override
    public void deleteStudentById(long sid) {this.studentRepository.deleteById(sid);}

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.studentRepository.findAll(pageable);

    }
}

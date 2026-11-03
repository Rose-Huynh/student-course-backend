package com.example.rlh5852.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "dept")
    private String courseDept;
    @Column(name = "num")
    private String courseNum;
    @Column(name = "name")
    private String courseName;
    @Column(name = "num_studs")
    private String courseStuds;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCourseDept() {
        return courseDept;
    }
    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }
    public String getCourseNum() {
        return courseNum;
    };
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseStuds() {
        return courseStuds;
    }
    public void setCourseStuds(String courseStuds) {
        this.courseStuds = courseStuds;
    }
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}

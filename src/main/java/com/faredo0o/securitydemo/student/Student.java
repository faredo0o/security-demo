package com.faredo0o.securitydemo.student;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;


public class Student {
    private  int studentId;
    private  String studentName;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}

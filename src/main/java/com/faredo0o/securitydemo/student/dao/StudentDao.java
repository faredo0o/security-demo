package com.faredo0o.securitydemo.student.dao;

import com.faredo0o.securitydemo.student.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StudentDao {
    private List<Student> students= new ArrayList<>();
    public StudentDao() {

        students.add(new Student(1,"Essam Fared"));
        students.add(new Student(2,"Omar Essam"));
        students.add(new Student(3,"Ali Essam"));
        students.add(new Student(4,"Malek Essam"));

    }


        public Student findById(int id){
            return students.stream()
                    .filter(student->student.getStudentId()==id)
                    .findFirst()
                    .orElseThrow(()->new IllegalArgumentException("Student "+ id+" not exist"));
        }
        public List<Student> getStudents(){
            return students;
        }
        public void addStudent(Student student){
          Student studentToAdd=new Student(student.getStudentId(),student.getStudentName());
            students.add(studentToAdd);
            System.out.println("sout from add "+student);
        }
        public void deleteStudent(int id){
            Student studentToRemove=students.stream()
                    .filter(student->student.getStudentId()==id)
                    .findFirst()
                    .orElseThrow(()->new IllegalArgumentException("Wrong student id "));
            students.remove(studentToRemove);
        }
}

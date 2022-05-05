package com.faredo0o.securitydemo.student;

import com.faredo0o.securitydemo.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    private StudentDao studentDao;
@Autowired
    public StudentManagementController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
   @GetMapping("/")
    public List<Student> getAllStudents(){
        return studentDao.getStudents();
    }
    @PostMapping("/")
    public void registerStudent(@RequestBody Student student){
        System.out.println(student);
    studentDao.addStudent(student);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
    studentDao.deleteStudent(id);
    }
    @PutMapping("/")
    public void updateStudent(@RequestBody Student student){
        System.out.println(student);
    }
}

package com.faredo0o.securitydemo.student;

import com.faredo0o.securitydemo.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
   @GetMapping
//   @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents(){
        return studentDao.getStudents();
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('student:write')")
    public void registerStudent(@RequestBody Student student){
        System.out.println(student);
    studentDao.addStudent(student);
    }
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable int id){
    studentDao.deleteStudent(id);
    }
    @PutMapping
//    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@RequestBody Student student){
        System.out.println(student);
    }
}

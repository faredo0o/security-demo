package com.faredo0o.securitydemo.student;

import com.faredo0o.securitydemo.student.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController{
    private StudentDao studentDao;
@Autowired
    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @GetMapping(path="{id}")
    public Student getStudent(@PathVariable int id){
       return studentDao.findById(id);

    }

}

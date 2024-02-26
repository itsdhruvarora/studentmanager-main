package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import com.example.demo.model.StudentModel;


@RestController
@RequestMapping("/api/students")

public class StudentController {
    
    private final Map<Integer, StudentModel> studentMap = new HashMap<>();
    private int id = 1;
    
    @PostMapping("/create")
    public int createStudent(@RequestBody StudentModel student) {
        student.setId(id);
        studentMap.put(id, student);
        return id++;
    }

    @GetMapping("/university/{uni}")
    public List<StudentModel> getStudentByUni(@PathVariable String uni){
        List<StudentModel> studentsList = new ArrayList<>();
        for (StudentModel student : studentMap.values()) {
            if (student.getUniversity().equalsIgnoreCase(uni)) {
                studentsList.add(student);
            }
        }
        return studentsList;
    } 

    @GetMapping("/{id}")
    public StudentModel findStudentById(@PathVariable int id) {
        return studentMap.getOrDefault(id, null);
    }
    
}

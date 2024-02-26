package com.example.demo.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    private HashMap<Integer, String[]> studentMap = new HashMap<>();
    private int nextId = 1;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/students")
    public String createStudent(@RequestParam String name, @RequestParam int age, @RequestParam String university) {
        String[] studentData = {name, String.valueOf(age), university};
        int id = nextId++;
        studentMap.put(id, studentData);
        return "student created ";
    }

    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable int id) {
        String[] studentData = studentMap.get(id);
        if (studentData != null) {
            return "student name: " + studentData[0];
        } else {
            return "student not found";
        }
    }

    @PostMapping("/studentsbyuniversity")
    public ArrayList<String> getStudentsByUniversity(@RequestParam String university) {
        ArrayList<String> students = new ArrayList<>();
        for (Map.Entry<Integer, String[]> entry : studentMap.entrySet()) {  
            String[] studentData = entry.getValue();
            if (studentData[2].equals(university)) {
                students.add(studentData[0]);
            }
        }
        return students;
    }
}

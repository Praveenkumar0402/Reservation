package Liquibase.ExcelAndLiquibase.controller;

import Liquibase.ExcelAndLiquibase.entity.Student;
import Liquibase.ExcelAndLiquibase.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/upload-data")
    public ResponseEntity<?> uploadDatabase(@RequestParam("file") MultipartFile file) {
        this.service.saveStudentToDatabase(file);
        return ResponseEntity.ok(Map.of("Message", "Students data uploaded and saved to database successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(service.getStudents(), HttpStatus.FOUND);
    }
}

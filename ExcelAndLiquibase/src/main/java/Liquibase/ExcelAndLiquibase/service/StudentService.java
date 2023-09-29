package Liquibase.ExcelAndLiquibase.service;

import Liquibase.ExcelAndLiquibase.entity.Student;
import Liquibase.ExcelAndLiquibase.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public void saveStudentToDatabase(MultipartFile file) {
        if (ExcelService.isValidExcelFile(file)) {
            try {
                List<Student> students = ExcelService.getStudentDataFromExcel(file.getInputStream());
                this.studentRepository.saveAll(students);
            } catch (IOException e) {
                throw new IllegalArgumentException("This file is not a valid excel file");
            }
        }
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}

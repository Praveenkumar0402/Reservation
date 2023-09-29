package Liquibase.ExcelAndLiquibase.repository;

import Liquibase.ExcelAndLiquibase.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}

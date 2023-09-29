package Liquibase.ExcelAndLiquibase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="students")
public class Student {

    @Id
    private int id;
    private String name;
    private String email;
//    String regex="(0/91)?[7-9][0-9]{9}";
    private long mobile;
    private String course;

}

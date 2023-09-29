package Liquibase.ExcelAndLiquibase.service;

import Liquibase.ExcelAndLiquibase.entity.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Student> getStudentDataFromExcel(InputStream inputStream) {
        List<Student> students = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("sheet2");
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Student student = new Student();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> student.setId((int) cell.getNumericCellValue());
                        case 1 -> student.setName(cell.getStringCellValue());
                        case 2 -> student.setEmail(cell.getStringCellValue());
                        case 3 -> student.setMobile((long) cell.getNumericCellValue());
                        case 4 -> student.setCourse(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                students.add(student);
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return students;
    }
}

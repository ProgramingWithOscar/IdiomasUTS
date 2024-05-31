package com.bolsadeideas.springboot.app.excelReader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import com.bolsadeideas.springboot.app.entity.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ExcelReader {

    public List<Student> readExcel(InputStream fileInputStream) throws IOException {
        List<Student> students = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Omitir la fila de encabezado
            Student student = new Student();

            student.setId(UUID.randomUUID().toString());
            student.setName(getStringCellValue(row.getCell(0)));
            student.setIdStudent(getStringCellValue(row.getCell(1)));
            student.setLevel(getStringCellValue(row.getCell(2)));
            student.setProgram(getStringCellValue(row.getCell(3)));
            student.setNote1(getStringCellValue(row.getCell(4)));
            student.setNote2(getStringCellValue(row.getCell(5)));
            student.setNote3(getStringCellValue(row.getCell(6)));
            student.setFinalGrade(getStringCellValue(row.getCell(7)));
            student.setRemarks(getStringCellValue(row.getCell(8)));

            students.add(student);
        }

        workbook.close();
        fileInputStream.close();

        return students;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "0";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "0";
            default:
                throw new IllegalArgumentException("Unsupported cell type for string value");
        }
    }
}

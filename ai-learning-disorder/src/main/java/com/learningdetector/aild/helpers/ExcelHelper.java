package com.learningdetector.aild.helpers;

import com.learningdetector.aild.entity.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    // Convert Excel file to list of Students
    public static List<Student> excelToStudents(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Student> students = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Student student = new Student();
                student.setName(getCellValueAsString(currentRow.getCell(0)));
                student.setGrade(getCellValueAsString(currentRow.getCell(1)));
                student.setSection(getCellValueAsString(currentRow.getCell(2)));
                student.setGuardianContact(getCellValueAsString(currentRow.getCell(3)));

                students.add(student);
            }

            workbook.close();
            return students;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

    // Utility to safely get cell value as String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Convert numeric to string without decimal
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}

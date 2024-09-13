    package acme.secretSantaGame.utils;

    import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import acme.secretSantaGame.exceptions.SecretSantaException;
import acme.secretSantaGame.model.CompanyEmployee;

    public class CSVReader {

        public static List<CompanyEmployee> readPreviousAssignments(String filePath) throws SecretSantaException {
            List<CompanyEmployee> previousAssignments = new ArrayList<>();
            try (FileInputStream fis = new FileInputStream(filePath);
                    Workbook workbook = new XSSFWorkbook(fis)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getCell(0) != null && row.getCell(1) != null && row.getCell(2) != null
                            && row.getCell(3) != null) {
                        String name = row.getCell(0).getStringCellValue();
                        String email = row.getCell(1).getStringCellValue();
                        String childName = row.getCell(2).getStringCellValue();
                        String childEmail = row.getCell(3).getStringCellValue();

                        CompanyEmployee employee = new CompanyEmployee(name, email);
                        employee.setSecretChild(childName, childEmail);
                        previousAssignments.add(employee);
                    }
                }
            } catch (IOException e) {
                throw new SecretSantaException("Error reading previous assignments: " + e.getMessage());
            }
            return previousAssignments;
        }
    }

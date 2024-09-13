package acme.secretSantaGame.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import acme.secretSantaGame.exceptions.SecretSantaException;
import acme.secretSantaGame.model.CompanyEmployee;

public class EmployeeService {
    private final String employeeFilePath;
    private final String previousYearFilePath;

    public EmployeeService(String employeeFilePath, String previousYearFilePath) {
        this.employeeFilePath = employeeFilePath;
        this.previousYearFilePath = previousYearFilePath;
    }

    public List<CompanyEmployee> loadEmployees() throws SecretSantaException {
        return loadEmployeesFromFile(employeeFilePath);
    }

    public List<CompanyEmployee> loadPreviousAssignments() throws SecretSantaException {
        List<CompanyEmployee> previousAssignments = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(previousYearFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean isHeader = true;

            for (Row row : sheet) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Cell nameCell = row.getCell(0);
                Cell emailCell = row.getCell(1);
                Cell childNameCell = row.getCell(2);
                Cell childEmailCell = row.getCell(3);

                if (nameCell != null && emailCell != null && childNameCell != null && childEmailCell != null) {
                    CompanyEmployee employee = new CompanyEmployee(nameCell.getStringCellValue(),
                            emailCell.getStringCellValue());
                    employee.setSecretChild(childNameCell.getStringCellValue(), childEmailCell.getStringCellValue());
                    previousAssignments.add(employee);
                }
            }
        } catch (IOException e) {
            throw new SecretSantaException("Error reading previous assignments: " + e.getMessage());
        }

        return previousAssignments;
    }

    private List<CompanyEmployee> loadEmployeesFromFile(String filePath) throws SecretSantaException {
        List<CompanyEmployee> employees = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            boolean isHeader = true;
            for (Row row : sheet) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                Cell nameCell = row.getCell(0);
                Cell emailCell = row.getCell(1);

                if (nameCell != null && emailCell != null) {
                    employees.add(new CompanyEmployee(nameCell.getStringCellValue(), emailCell.getStringCellValue()));
                }
            }
        } catch (IOException e) {
            throw new SecretSantaException("Error reading employee list: " + e.getMessage());
        }

        return employees;
    }

    public void validateEmployees(List<CompanyEmployee> employees) throws SecretSantaException {
        Set<String> employeeEmails = new HashSet<>();
        for (CompanyEmployee employee : employees) {
            if (!employeeEmails.add(employee.getEmail())) {
                throw new SecretSantaException("Duplicate employee email found: " + employee.getEmail());
            }
        }
    }
}

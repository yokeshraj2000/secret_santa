package acme.secretSantaGame;

import java.io.IOException;
import java.util.List;

import acme.secretSantaGame.exceptions.SecretSantaException;
import acme.secretSantaGame.model.CompanyEmployee;
import acme.secretSantaGame.service.EmployeeService;
import acme.secretSantaGame.service.SecretSantaService;
import acme.secretSantaGame.utils.CSVWriter;

public class Main {
    public static void main(String[] args) {
        try {
            String employeeFilePath = "src/main/resources/Employee_List.xlsx";
            String previousYearFilePath = "src/main/resources/Secret_Santa_Game_Result_2023.xlsx";
            String outputFilePath = "src/main/resources/Secret_Santa_Game_Result_2024.csv";

            EmployeeService employeeService = new EmployeeService(employeeFilePath, previousYearFilePath);
            List<CompanyEmployee> employees = employeeService.loadEmployees();
            List<CompanyEmployee> previousAssignments = employeeService.loadPreviousAssignments();

            employeeService.validateEmployees(employees);
            SecretSantaService secretSantaService = new SecretSantaService();
            List<CompanyEmployee> result = secretSantaService.assignSecretSantas(employees, previousAssignments);

            CSVWriter.writeResultToCSV(result, outputFilePath);

            System.out.println("Secret Santa assignments generated successfully!");
        } catch (SecretSantaException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}

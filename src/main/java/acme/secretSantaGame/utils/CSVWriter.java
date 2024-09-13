package acme.secretSantaGame.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import acme.secretSantaGame.model.CompanyEmployee;

public class CSVWriter {
    public static void writeResultToCSV(List<CompanyEmployee> result, String outputFilePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            writer.println("Employee_Name,Employee_EmailID,Secret_Child_Name,Secret_Child_EmailID");

            for (CompanyEmployee employee : result) {
                writer.printf("%s,%s,%s,%s%n",
                        employee.getName(),
                        employee.getEmail(),
                        employee.getSecretChildName() != null ? employee.getSecretChildName() : "",
                        employee.getSecretChildEmail() != null ? employee.getSecretChildEmail() : "");
            }
        }
    }
}

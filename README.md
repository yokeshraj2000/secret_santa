# secret_santa

This Java application randomly assigns Secret Santa gift givers and receivers while ensuring no one is assigned to themselves or to someone they had in the previous year.

# Prerequisites
JDK 17
Maven (for dependency management)
Apache POI (for reading Excel files)
# Project Structure
acme.secretSantaGame.exceptions: Custom exception handling.
acme.secretSantaGame.model: Company employee model with attributes.
acme.secretSantaGame.service: Logic for loading employees and assigning Secret Santas.
acme.secretSantaGame.utils: Utilities for reading from and writing to CSV/Excel files.
acme.secretSantaGame.Main: Main class that runs the assignment process.
# How to Use
1.Prepare the Employee Data:

Add employee information in the Employee_List.xlsx file located in src/main/resources/.
The Excel file should have the following columns:
Employee_Name
Employee_EmailID

2.Prepare Previous Assignments Data:

Add previous Secret Santa assignments to the Secret_Santa_Game_Result_2023.xlsx file.
The columns should be:
Employee_Name
Employee_Email
Secret_Child_Name
Secret_Child_EmailID

3.Run the Application:

In the terminal, navigate to the project folder and run the application

cd /path/to/project/secret_santa

4.Expected Output:

The application will generate new Secret Santa assignments and save them to a CSV file: Secret_Santa_Game_Result_2024.csv in the src/main/resources/ folder.
If conflicts arise (e.g., someone is assigned to themselves or a previous year's match), the application will throw an error.

5.Key Functions

EmployeeService: Loads employee data from Excel and validates the data to avoid duplicates.
SecretSantaService: Assigns Secret Santas randomly and checks for self-assignments and previous year conflicts.
CSVWriter: Saves the assignment results to a CSV file.

6.Troubleshooting

Error: Unable to assign Secret Santa without conflicts.
This means the application couldn't assign Santas due to either self-assignment or a match from the previous year. Verify the input data and try again.

Example

Giver: Matthew King (matthew.king.jr@acme.com)
Receiver: Hamish Murray (hamish.murray.sr@acme.com)
Self-assignment: false
Previous assignment conflict: true
Error: Unable to assign Secret Santa without conflicts.


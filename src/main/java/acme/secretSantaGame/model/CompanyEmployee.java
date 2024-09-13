package acme.secretSantaGame.model;

public class CompanyEmployee {
    private String name;
    private String email;
    private String secretChildName;
    private String secretChildEmail;

    public CompanyEmployee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSecretChildName() {
        return secretChildName;
    }

    public String getSecretChildEmail() {
        return secretChildEmail;
    }

    public void setSecretChild(String secretChildName, String secretChildEmail) {
        this.secretChildName = secretChildName;
        this.secretChildEmail = secretChildEmail;
    }
}

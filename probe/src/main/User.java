package main;

public class User {
    private String employeeId;
    private String fullName;
    private String password;
    private String position;
    private String superiorId;
    
    public User(String employeeId, String fullName, String password, String position, String superiorId) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.password = password;
        this.position = position;
        this.superiorId = superiorId;
    }
    
    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getFullName() { return fullName; }
    public String getPassword() { return password; }
    public String getPosition() { return position; }
    public String getSuperiorId() { return superiorId; }
}
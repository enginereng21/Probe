package main;

import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/probe_management_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // Your MySQL password
    
    private static Connection connection = null;
    
    static {
        initializeDatabase();
    }
    
    private static void initializeDatabase() {
        try {
            System.out.println("🔧 Attempting to load MySQL driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver loaded successfully!");
            
            System.out.println("🔧 Attempting to connect to database...");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Database connection established!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found!");
            showErrorDialog("Driver Error", 
                "MySQL JDBC Driver not found!\n\n" +
                "Please add MySQL Connector/J to your build path.");
            
        } catch (SQLException e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            showErrorDialog("Connection Error", 
                "Cannot connect to database!\n\n" +
                "Error: " + e.getMessage() + "\n\n" +
                "Please check:\n" +
                "• MySQL server is running\n" +
                "• Database 'probe_management_system' exists\n" +
                "• Username/password are correct");
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    private static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    // User authentication method
    public static User authenticateUser(String employeeId, String password) {
        if (!isConnected()) {
            return null;
        }
        
        try {
            String sql = "SELECT * FROM users WHERE employee_id = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getString("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("position"),
                    rs.getString("superior_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Authentication error: " + e.getMessage());
        }
        
        return null;
    }
    
    // User registration method
    public static boolean registerUser(String employeeId, String fullName, String password, 
                                     String position, String superiorId) {
        if (!isConnected()) {
            return false;
        }
        
        try {
            String sql = "INSERT INTO users (employee_id, full_name, password, position, superior_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            pstmt.setString(2, fullName);
            pstmt.setString(3, password);
            pstmt.setString(4, position);
            pstmt.setString(5, superiorId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Registration error: " + e.getMessage());
            return false;
        }
    }
    
    // Check if employee ID already exists
    public static boolean userExists(String employeeId) {
        if (!isConnected()) {
            return false;
        }
        
        try {
            String sql = "SELECT employee_id FROM users WHERE employee_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("User check error: " + e.getMessage());
            return false;
        }
    }
    
    // Get all superiors for registration
    public static java.util.List<String> getSuperiors() {
        java.util.List<String> superiors = new java.util.ArrayList<>();
        
        if (!isConnected()) {
            return superiors;
        }
        
        try {
            String sql = "SELECT employee_id, full_name, position FROM users WHERE position IN ('Supervisor', 'Manager', 'Admin')";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                String display = rs.getString("employee_id") + " - " + 
                               rs.getString("full_name") + " (" + 
                               rs.getString("position") + ")";
                superiors.add(display);
            }
            
        } catch (SQLException e) {
            System.err.println("Error loading superiors: " + e.getMessage());
        }
        
        return superiors;
    }
}
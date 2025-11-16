package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private Color primaryColor = new Color(41, 128, 185);
    private Color darkColor = new Color(33, 47, 60);
    private Color backgroundColor = new Color(248, 249, 250);
    private Color accentColor = new Color(52, 152, 219);
    
    private JTextField idField;
    private JPasswordField passwordField;
    
    public LoginForm() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Probe Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        createLoginForm();
    }
    
    private void createLoginForm() {
        // Main panel with background
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        
        // Left side - Branding/Image
        JPanel leftPanel = createLeftPanel();
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        
        // Right side - Login Form
        JPanel rightPanel = createRightPanel();
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        add(mainPanel);
    }
    
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(241, 243, 245));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Brand content
        JPanel brandPanel = new JPanel();
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.Y_AXIS));
        brandPanel.setBackground(new Color(241, 243, 245));
        brandPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Logo/Icon
        JLabel logoLabel = new JLabel("🔬");
        logoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Title
        JLabel titleLabel = new JLabel("Probe Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(darkColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Streamline your probe insertion tracking and maintenance verification");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Features
        JPanel featuresPanel = createFeaturesPanel();
        featuresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        brandPanel.add(logoLabel);
        brandPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        brandPanel.add(titleLabel);
        brandPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        brandPanel.add(subtitleLabel);
        brandPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        brandPanel.add(featuresPanel);
        
        leftPanel.add(brandPanel, gbc);
        
        return leftPanel;
    }
    
    private JPanel createFeaturesPanel() {
        JPanel featuresPanel = new JPanel();
        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));
        featuresPanel.setBackground(new Color(241, 243, 245));
        featuresPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] features = {
            "✓ Insertion Tracking & Monitoring",
            "✓ Maintenance Verification",
            "✓ Excel Data Integration",
            "✓ Real-time Reporting",
            "✓ Multi-user Support"
        };
        
        for (String feature : features) {
            JLabel featureLabel = new JLabel(feature);
            featureLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            featureLabel.setForeground(new Color(80, 80, 80));
            featureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            featuresPanel.add(featureLabel);
            featuresPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        
        return featuresPanel;
    }
    
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setPreferredSize(new Dimension(450, getHeight()));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 50, 60, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        // Header
        JPanel headerPanel = createLoginHeader();
        gbc.insets = new Insets(0, 0, 40, 0);
        rightPanel.add(headerPanel, gbc);
        
        // Form
        JPanel formPanel = createLoginFormPanel();
        gbc.insets = new Insets(0, 0, 20, 0);
        rightPanel.add(formPanel, gbc);
        
        // Footer
        JPanel footerPanel = createLoginFooter();
        gbc.insets = new Insets(20, 0, 0, 0);
        rightPanel.add(footerPanel, gbc);
        
        return rightPanel;
    }
    
    private JPanel createLoginHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel welcomeLabel = new JLabel("Welcome Back");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(darkColor);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel instructionLabel = new JLabel("Enter your credentials to access the system");
        instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instructionLabel.setForeground(new Color(100, 100, 100));
        instructionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        headerPanel.add(welcomeLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        headerPanel.add(instructionLabel);
        
        return headerPanel;
    }
    
    private JPanel createLoginFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // ID Number Field
        formPanel.add(createFormLabel("ID Number"));
        idField = createFormTextField();
        formPanel.add(idField);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Password Field
        formPanel.add(createFormLabel("Password"));
        passwordField = createFormPasswordField();
        formPanel.add(passwordField);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Login Button
        JButton loginButton = createLoginButton();
        formPanel.add(loginButton);
        
        return formPanel;
    }
    
    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(darkColor);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private JTextField createFormTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(350, 45));
        field.setPreferredSize(new Dimension(350, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add focus listener for better UX
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(primaryColor, 2),
                    BorderFactory.createEmptyBorder(11, 14, 11, 14)
                ));
            }
            
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }
        });
        
        return field;
    }
    
    private JPasswordField createFormPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(350, 45));
        field.setPreferredSize(new Dimension(350, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add focus listener
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(primaryColor, 2),
                    BorderFactory.createEmptyBorder(11, 14, 11, 14)
                ));
            }
            
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(12, 15, 12, 15)
                ));
            }
        });
        
        return field;
    }
    
    private JButton createLoginButton() {
        JButton loginButton = new JButton("Login to System");
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setMaximumSize(new Dimension(350, 50));
        loginButton.setPreferredSize(new Dimension(350, 50));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> performLogin());
        
        // Hover effects
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginButton.setBackground(accentColor);
            }
            
            public void mouseExited(MouseEvent evt) {
                loginButton.setBackground(primaryColor);
            }
        });
        
        return loginButton;
    }
    
    private JPanel createLoginFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel registerLabel = new JLabel("Don't have an account? ");
        registerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        registerLabel.setForeground(new Color(100, 100, 100));
        
        JButton registerLink = new JButton("Register Now");
        registerLink.setBorderPainted(false);
        registerLink.setContentAreaFilled(false);
        registerLink.setForeground(primaryColor);
        registerLink.setFont(new Font("Segoe UI", Font.BOLD, 12));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.addActionListener(e -> openRegistrationForm());
        
        footerPanel.add(registerLabel);
        footerPanel.add(registerLink);
        
        return footerPanel;
    }
    
    private void performLogin() {
        String employeeId = idField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        // Validation
        if (employeeId.isEmpty() || password.isEmpty()) {
            showErrorDialog("Please enter both ID number and password.");
            return;
        }
        
        if (!DatabaseManager.isConnected()) {
            showErrorDialog("Database connection failed. Please contact administrator.");
            return;
        }
        
        // Real authentication
        User user = DatabaseManager.authenticateUser(employeeId, password);
        
        if (user != null) {
            loginSuccessful(user.getFullName(), user.getPosition());
        } else {
            showErrorDialog("Invalid ID number or password.");
        }
    }
    
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void loginSuccessful(String userName, String position) {
        JOptionPane.showMessageDialog(this, 
            "Welcome back, " + userName + "!\nPosition: " + position, 
            "Login Successful", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // Open main application
        openMainApplication(userName, position);
    }
    
    private void openRegistrationForm() {
        new RegistrationForm().setVisible(true);
        this.dispose();
    }
    
    private void openMainApplication(String userName, String position) {
        this.dispose();
        new MainApplicationWindow(userName, position).setVisible(true);
    }
    
    public static void main(String[] args) {
        // Initialize database connection
        DatabaseManager.getConnection();
        
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginForm().setVisible(true);
        });
    }
}
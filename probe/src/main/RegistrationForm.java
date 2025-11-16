package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RegistrationForm extends JFrame {
    private Color primaryColor = new Color(41, 128, 185);
    private Color darkColor = new Color(33, 47, 60);
    private Color backgroundColor = new Color(248, 249, 250);
    private Color accentColor = new Color(52, 152, 219);
    
    private JTextField fullNameField;
    private JTextField idField;
    private JComboBox<String> positionComboBox;
    private JComboBox<String> superiorComboBox;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    
    public RegistrationForm() {
        initializeUI();
        loadSuperiors();
    }
    
    private void initializeUI() {
        setTitle("Probe Management System - Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        createRegistrationForm();
    }
    
    private void createRegistrationForm() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        
        // Left side - Branding
        JPanel leftPanel = createLeftPanel();
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        
        // Right side - Registration Form
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
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(241, 243, 245));
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Icon
        JLabel iconLabel = new JLabel("👤");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Title
        JLabel titleLabel = new JLabel("Join Our Team");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(darkColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Create your account to access the Probe Management System");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Benefits
        JPanel benefitsPanel = createBenefitsPanel();
        benefitsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(iconLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(subtitleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        contentPanel.add(benefitsPanel);
        
        leftPanel.add(contentPanel, gbc);
        
        return leftPanel;
    }
    
    private JPanel createBenefitsPanel() {
        JPanel benefitsPanel = new JPanel();
        benefitsPanel.setLayout(new BoxLayout(benefitsPanel, BoxLayout.Y_AXIS));
        benefitsPanel.setBackground(new Color(241, 243, 245));
        benefitsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] benefits = {
            "✓ Access to real-time probe data",
            "✓ Maintenance scheduling tools",
            "✓ Excel import/export capabilities",
            "✓ Comprehensive reporting",
            "✓ Multi-level user permissions"
        };
        
        for (String benefit : benefits) {
            JLabel benefitLabel = new JLabel(benefit);
            benefitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            benefitLabel.setForeground(new Color(80, 80, 80));
            benefitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            benefitsPanel.add(benefitLabel);
            benefitsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        
        return benefitsPanel;
    }
    
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setPreferredSize(new Dimension(500, getHeight()));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        
        // Header
        JPanel headerPanel = createRegisterHeader();
        gbc.insets = new Insets(0, 0, 30, 0);
        rightPanel.add(headerPanel, gbc);
        
        // Form with scroll
        JScrollPane scrollPane = createFormScrollPane();
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        rightPanel.add(scrollPane, gbc);
        
        // Footer
        JPanel footerPanel = createRegisterFooter();
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 0, 0);
        rightPanel.add(footerPanel, gbc);
        
        return rightPanel;
    }
    
    private JPanel createRegisterHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(darkColor);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Fill in your details to create a new account");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        headerPanel.add(subtitleLabel);
        
        return headerPanel;
    }
    
    private JScrollPane createFormScrollPane() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Full Name
        formPanel.add(createFormLabel("Full Name *"));
        fullNameField = createFormTextField();
        formPanel.add(fullNameField);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // ID Number
        formPanel.add(createFormLabel("ID Number *"));
        idField = createFormTextField();
        formPanel.add(idField);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Password
        formPanel.add(createFormLabel("Password *"));
        passwordField = createFormPasswordField();
        formPanel.add(passwordField);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Confirm Password
        formPanel.add(createFormLabel("Confirm Password *"));
        confirmPasswordField = createFormPasswordField();
        formPanel.add(confirmPasswordField);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Position
        formPanel.add(createFormLabel("Position *"));
        positionComboBox = createFormComboBox(new String[]{
            "Select your position", "Technician", "Supervisor", "Manager", "Admin"
        });
        formPanel.add(positionComboBox);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Superior
        formPanel.add(createFormLabel("Superior (Optional)"));
        superiorComboBox = createFormComboBox(new String[]{"Select your superior"});
        formPanel.add(superiorComboBox);
        
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Register Button
        JButton registerButton = createRegisterButton();
        formPanel.add(registerButton);
        
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        return scrollPane;
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
        field.setMaximumSize(new Dimension(400, 45));
        field.setPreferredSize(new Dimension(400, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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
        field.setMaximumSize(new Dimension(400, 45));
        field.setPreferredSize(new Dimension(400, 45));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        
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
    
    private JComboBox<String> createFormComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setMaximumSize(new Dimension(400, 45));
        comboBox.setPreferredSize(new Dimension(400, 45));
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Custom renderer for better appearance
        comboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        });
        
        return comboBox;
    }
    
    private JButton createRegisterButton() {
        JButton registerButton = new JButton("Create Account");
        registerButton.setBackground(primaryColor);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setMaximumSize(new Dimension(400, 50));
        registerButton.setPreferredSize(new Dimension(400, 50));
        registerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> performRegistration());
        
        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                registerButton.setBackground(accentColor);
            }
            
            public void mouseExited(MouseEvent evt) {
                registerButton.setBackground(primaryColor);
            }
        });
        
        return registerButton;
    }
    
    private JPanel createRegisterFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel loginLabel = new JLabel("Already have an account? ");
        loginLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        loginLabel.setForeground(new Color(100, 100, 100));
        
        JButton loginLink = new JButton("Sign In");
        loginLink.setBorderPainted(false);
        loginLink.setContentAreaFilled(false);
        loginLink.setForeground(primaryColor);
        loginLink.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLink.addActionListener(e -> openLoginForm());
        
        footerPanel.add(loginLabel);
        footerPanel.add(loginLink);
        
        return footerPanel;
    }
    
    private void loadSuperiors() {
        if (!DatabaseManager.isConnected()) {
            superiorComboBox.removeAllItems();
            superiorComboBox.addItem("Select your superior");
            return;
        }
        
        List<String> superiors = DatabaseManager.getSuperiors();
        superiorComboBox.removeAllItems();
        superiorComboBox.addItem("Select your superior");
        
        for (String superior : superiors) {
            superiorComboBox.addItem(superior);
        }
    }
    
    private void performRegistration() {
        String fullName = fullNameField.getText().trim();
        String employeeId = idField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String position = (String) positionComboBox.getSelectedItem();
        String superior = (String) superiorComboBox.getSelectedItem();
        
        // Validation
        if (fullName.isEmpty() || employeeId.isEmpty() || password.isEmpty()) {
            showError("Please fill in all required fields.");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }
        
        if (position.equals("Select your position")) {
            showError("Please select a position.");
            return;
        }
        
        // Check if user already exists
        if (DatabaseManager.userExists(employeeId)) {
            showError("ID number already exists. Please use a different ID.");
            return;
        }
        
        // Extract superior ID if selected
        String superiorId = null;
        if (superior != null && !superior.equals("Select your superior")) {
            superiorId = superior.split(" - ")[0];
        }
        
        // Real registration
        boolean success = DatabaseManager.registerUser(employeeId, fullName, password, position, superiorId);
        
        if (success) {
            JOptionPane.showMessageDialog(this,
                "Account created successfully!\n\n" +
                "Name: " + fullName + "\n" +
                "ID: " + employeeId + "\n" +
                "Position: " + position + "\n\n" +
                "You can now login with your credentials.",
                "Registration Successful",
                JOptionPane.INFORMATION_MESSAGE);
            
            openLoginForm();
        } else {
            showError("Registration failed. Please try again.");
        }
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void openLoginForm() {
        new LoginForm().setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new RegistrationForm().setVisible(true);
        });
    }
}
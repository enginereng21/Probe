package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainApplicationWindow extends JFrame {
    private String userName;
    private String position;
    private String employeeId;
    private Color primaryColor = new Color(41, 128, 185);
    private Color darkColor = new Color(33, 47, 60);
    
    private JTabbedPane tabbedPane;
    private JTable probesTable;
    private DefaultTableModel probesTableModel;
    
    public MainApplicationWindow(String userName, String position) {
        this.userName = userName;
        this.position = position;
        this.employeeId = getEmployeeIdFromUser(userName);
        initializeUI();
    }
    
    private String getEmployeeIdFromUser(String userName) {
        // In real app, this would come from User object
        // For now, return a default
        return "TEC001";
    }
    
    private void initializeUI() {
        setTitle("Probe Management System - Welcome " + userName);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        createMainApplication();
        loadProbesData();
    }
    
    private void createMainApplication() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(248, 249, 250));
        
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        tabbedPane.addTab("📊 Dashboard", createDashboardTab());
        tabbedPane.addTab("🔬 Probe Management", createProbeManagementTab());
        tabbedPane.addTab("📝 Insertion Tracking", createInsertionTrackingTab());
        tabbedPane.addTab("🔧 Maintenance", createMaintenanceTab());
        tabbedPane.addTab("📈 Reports", createReportsTab());
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        
        JLabel titleLabel = new JLabel("Probe Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(primaryColor);
        
        JLabel userLabel = new JLabel(userName + " (" + position + ")");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(primaryColor);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> logout());
        
        userPanel.add(userLabel);
        userPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        userPanel.add(logoutButton);
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private JPanel createDashboardTab() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(Color.WHITE);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel welcomeLabel = new JLabel(
            "<html><div style='text-align: center;'>" +
            "<h1 style='color: #2c3e50; font-size: 32px;'>Welcome to Probe Management System</h1>" +
            "<p style='color: #7f8c8d; font-size: 16px; margin-top: 10px;'>" +
            "Logged in as <strong>" + userName + "</strong> with <strong>" + position + "</strong> privileges</p>" +
            "</div></html>"
        );
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel statsPanel = createStatsPanel();
        JPanel activityPanel = createRecentActivityPanel();
        
        dashboardPanel.add(welcomeLabel, BorderLayout.NORTH);
        dashboardPanel.add(statsPanel, BorderLayout.CENTER);
        dashboardPanel.add(activityPanel, BorderLayout.SOUTH);
        
        return dashboardPanel;
    }
    
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        int totalProbes = getTotalProbesCount();
        int activeProbes = getActiveProbesCount();
        int maintenanceDue = getMaintenanceDueCount();
        int insertionsToday = getInsertionsTodayCount();
        
        String[][] stats = {
            {String.valueOf(totalProbes), "Total Probes", "🔬"},
            {String.valueOf(activeProbes), "Active Probes", "✅"},
            {String.valueOf(maintenanceDue), "Maintenance Due", "⏰"},
            {String.valueOf(insertionsToday), "Insertions Today", "📝"}
        };
        
        for (String[] stat : stats) {
            JPanel statCard = createStatCard(stat[0], stat[1], stat[2]);
            statsPanel.add(statCard);
        }
        
        return statsPanel;
    }
    
    private JPanel createRecentActivityPanel() {
        JPanel activityPanel = new JPanel(new BorderLayout());
        activityPanel.setBackground(Color.WHITE);
        activityPanel.setBorder(BorderFactory.createTitledBorder("Recent Activity"));
        
        String[] columns = {"Date", "Activity", "User", "Details"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable activityTable = new JTable(model);
        
        loadRecentActivity(model);
        
        JScrollPane scrollPane = new JScrollPane(activityTable);
        activityPanel.add(scrollPane, BorderLayout.CENTER);
        
        return activityPanel;
    }
    
    private JPanel createProbeManagementTab() {
        JPanel probePanel = new JPanel(new BorderLayout());
        probePanel.setBackground(Color.WHITE);
        probePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbarPanel.setBackground(Color.WHITE);
        
        JButton addProbeButton = new JButton("Add New Probe");
        addProbeButton.setBackground(primaryColor);
        addProbeButton.setForeground(Color.WHITE);
        addProbeButton.addActionListener(e -> showAddProbeDialog());
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadProbesData());
        
        toolbarPanel.add(addProbeButton);
        toolbarPanel.add(refreshButton);
        
        String[] columns = {"Probe ID", "Type", "Manufacturer", "Model", "Serial Number", "Status", "Calibration Due"};
        probesTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        probesTable = new JTable(probesTableModel);
        probesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(probesTable);
        
        probePanel.add(toolbarPanel, BorderLayout.NORTH);
        probePanel.add(scrollPane, BorderLayout.CENTER);
        
        return probePanel;
    }
    
    private JPanel createInsertionTrackingTab() {
        JPanel insertionPanel = new JPanel(new BorderLayout());
        insertionPanel.setBackground(Color.WHITE);
        insertionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel comingSoon = new JLabel("Insertion Tracking - Coming Soon", SwingConstants.CENTER);
        comingSoon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        comingSoon.setForeground(darkColor);
        
        insertionPanel.add(comingSoon, BorderLayout.CENTER);
        return insertionPanel;
    }
    
    private JPanel createMaintenanceTab() {
        JPanel maintenancePanel = new JPanel(new BorderLayout());
        maintenancePanel.setBackground(Color.WHITE);
        maintenancePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel comingSoon = new JLabel("Maintenance Management - Coming Soon", SwingConstants.CENTER);
        comingSoon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        comingSoon.setForeground(darkColor);
        
        maintenancePanel.add(comingSoon, BorderLayout.CENTER);
        return maintenancePanel;
    }
    
    private JPanel createReportsTab() {
        JPanel reportsPanel = new JPanel(new BorderLayout());
        reportsPanel.setBackground(Color.WHITE);
        reportsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel comingSoon = new JLabel("Reports & Analytics - Coming Soon", SwingConstants.CENTER);
        comingSoon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        comingSoon.setForeground(darkColor);
        
        reportsPanel.add(comingSoon, BorderLayout.CENTER);
        return reportsPanel;
    }
    
    // Database methods
    private void loadProbesData() {
        if (!DatabaseManager.isConnected()) return;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT probe_id, probe_type, manufacturer, model, serial_number, status, next_calibration_date FROM probes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            probesTableModel.setRowCount(0);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("probe_id"),
                    rs.getString("probe_type"),
                    rs.getString("manufacturer"),
                    rs.getString("model"),
                    rs.getString("serial_number"),
                    rs.getString("status"),
                    rs.getDate("next_calibration_date")
                };
                probesTableModel.addRow(row);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading probes: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int getTotalProbesCount() {
        if (!DatabaseManager.isConnected()) return 0;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT COUNT(*) as total FROM probes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private int getActiveProbesCount() {
        if (!DatabaseManager.isConnected()) return 0;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT COUNT(*) as active FROM probes WHERE status = 'Active'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt("active");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private int getMaintenanceDueCount() {
        if (!DatabaseManager.isConnected()) return 0;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT COUNT(*) as due FROM probes WHERE status = 'Calibration Due' OR status = 'Maintenance Required'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt("due");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private int getInsertionsTodayCount() {
        if (!DatabaseManager.isConnected()) return 0;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT COUNT(*) as today FROM probe_insertions WHERE DATE(insertion_timestamp) = CURDATE()";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt("today");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private void loadRecentActivity(DefaultTableModel model) {
        if (!DatabaseManager.isConnected()) return;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT 'Probe Added' as activity, created_at as date, created_by as user, probe_id as details FROM probes " +
                        "UNION ALL " +
                        "SELECT 'Insertion Recorded' as activity, insertion_timestamp as date, inserted_by as user, probe_id as details FROM probe_insertions " +
                        "ORDER BY date DESC LIMIT 10";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            model.setRowCount(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, HH:mm");
            
            while (rs.next()) {
                Date date = rs.getTimestamp("date");
                String formattedDate = dateFormat.format(date);
                Object[] row = {
                    formattedDate,
                    rs.getString("activity"),
                    rs.getString("user"),
                    rs.getString("details")
                };
                model.addRow(row);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void showAddProbeDialog() {
        JDialog dialog = new JDialog(this, "Add New Probe", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel formPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField probeIdField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField manufacturerField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField serialField = new JTextField();
        JTextField locationField = new JTextField();
        
        formPanel.add(new JLabel("Probe ID:"));
        formPanel.add(probeIdField);
        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeField);
        formPanel.add(new JLabel("Manufacturer:"));
        formPanel.add(manufacturerField);
        formPanel.add(new JLabel("Model:"));
        formPanel.add(modelField);
        formPanel.add(new JLabel("Serial Number:"));
        formPanel.add(serialField);
        formPanel.add(new JLabel("Location:"));
        formPanel.add(locationField);
        
        JButton saveButton = new JButton("Save Probe");
        saveButton.setBackground(primaryColor);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            saveNewProbe(probeIdField.getText(), typeField.getText(), manufacturerField.getText(), 
                        modelField.getText(), serialField.getText(), locationField.getText());
            dialog.dispose();
        });
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private void saveNewProbe(String probeId, String type, String manufacturer, String model, String serial, String location) {
        if (!DatabaseManager.isConnected()) return;
        
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "INSERT INTO probes (probe_id, probe_type, manufacturer, model, serial_number, location, created_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, probeId);
            pstmt.setString(2, type);
            pstmt.setString(3, manufacturer);
            pstmt.setString(4, model);
            pstmt.setString(5, serial);
            pstmt.setString(6, location);
            pstmt.setString(7, employeeId);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Probe added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadProbesData();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving probe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JPanel createStatCard(String value, String label, String icon) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(236, 240, 241));
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(primaryColor);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel labelLabel = new JLabel(label);
        labelLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelLabel.setForeground(new Color(127, 140, 141));
        labelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(valueLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(iconLabel);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(labelLabel);
        
        return card;
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginForm().setVisible(true);
        }
    }
}
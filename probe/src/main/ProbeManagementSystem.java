package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class ProbeManagementSystem extends JFrame {
    private Color primaryColor = new Color(41, 128, 185);    // Professional blue
    private Color darkColor = new Color(33, 47, 60);         // Dark slate
    private Color lightColor = new Color(236, 240, 241);     // Light background
    private Color accentColor = new Color(52, 152, 219);     // Accent blue
    private Color textColor = new Color(44, 62, 80);         // Dark text
    
    public ProbeManagementSystem() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Probe Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        
        createDashboard();
    }
    
    private void createDashboard() {
        // Main panel with gradient background
        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        
        // Header section
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Center content
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        
        // Main title with subtle shadow effect
        JLabel titleLabel = new JLabel("Probe Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titleLabel.setForeground(darkColor);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Streamline your probe insertion tracking and maintenance verification");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(100, 100, 100));
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        
        return headerPanel;
    }
    
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        // Features panel
        JPanel featuresPanel = createFeaturesPanel();
        gbc.insets = new Insets(0, 0, 40, 0);
        centerPanel.add(featuresPanel, gbc);
        
        // Separator
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(200, 200, 200));
        gbc.insets = new Insets(30, 100, 30, 100);
        centerPanel.add(separator, gbc);
        
        // Get Started button
        JButton getStartedButton = createGetStartedButton();
        gbc.insets = new Insets(20, 0, 0, 0);
        centerPanel.add(getStartedButton, gbc);
        
        return centerPanel;
    }
    
    private JPanel createFeaturesPanel() {
        JPanel featuresPanel = new JPanel(new GridLayout(1, 3, 25, 0));
        featuresPanel.setOpaque(false);
        featuresPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Feature 1: Insertion Tracking
        JPanel trackPanel = createFeaturePanel(
            "📊", 
            "Insertion Tracking", 
            "Monitor and record all probe insertion activities with detailed timestamps and operator information"
        );
        
        // Feature 2: Maintenance Verification
        JPanel maintenancePanel = createFeaturePanel(
            "🔧", 
            "Maintenance Verification", 
            "Schedule and verify maintenance activities with automated reminders and compliance reporting"
        );
        
        // Feature 3: Excel Integration
        JPanel databasePanel = createFeaturePanel(
            "💾", 
            "Excel Integration", 
            "Seamlessly import and export data to Excel for reporting and data analysis"
        );
        
        featuresPanel.add(trackPanel);
        featuresPanel.add(maintenancePanel);
        featuresPanel.add(databasePanel);
        
        return featuresPanel;
    }
    
    private JPanel createFeaturePanel(String icon, String title, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(30, 25, 30, 25)
        ));
        
        // Add subtle shadow effect
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(30, 25, 30, 25)
        ));
        
        // Icon
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(darkColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Description
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descArea.setForeground(new Color(100, 100, 100));
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setBackground(Color.WHITE);
        descArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descArea.setMaximumSize(new Dimension(250, 100));
        
        panel.add(iconLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(descArea);
        
        return panel;
    }
    
    private JButton createGetStartedButton() {
        RoundedButton getStartedButton = new RoundedButton("Get Started");
        getStartedButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        getStartedButton.setBackground(primaryColor);
        getStartedButton.setForeground(Color.WHITE);
        getStartedButton.setPreferredSize(new Dimension(200, 50));
        getStartedButton.setFocusPainted(false);
        getStartedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effects
        getStartedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                getStartedButton.setBackground(accentColor);
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                getStartedButton.setBackground(primaryColor);
            }
        });
        
        // Button action
        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainApplication();
            }
        });
        
        return getStartedButton;
    }
    
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JLabel versionLabel = new JLabel("v1.0 • Professional Edition");
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        versionLabel.setForeground(new Color(150, 150, 150));
        
        footerPanel.add(versionLabel);
        
        return footerPanel;
    }
    
    private void openMainApplication() {
        // Close dashboard and open login form
        dispose();
        
        // Open login form instead of main application directly
        new LoginForm().setVisible(true);
        
        JOptionPane.showMessageDialog(null, 
            "Welcome to Probe Management System!\n\n" +
            "Please login to access the system features:\n" +
            "• Insertion Tracking Module\n" +
            "• Maintenance Verification\n" +
            "• Excel Data Integration",
            "System Ready",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Custom panel for gradient background
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(248, 249, 250);
            Color color2 = new Color(233, 236, 239);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }
    
    // Custom rounded button class
    class RoundedButton extends JButton {
        private int cornerRadius = 25;
        
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (getModel().isPressed()) {
                g2.setColor(getBackground().darker());
            } else if (getModel().isRollover()) {
                g2.setColor(getBackground().brighter());
            } else {
                g2.setColor(getBackground());
            }
            
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            g2.dispose();
            
            super.paintComponent(g);
        }
    }
    
    public static void main(String[] args) {
        // Set professional look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProbeManagementSystem().setVisible(true);
            }
        });
    }
}
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class oop extends JFrame {
    private static final int TITLE_FONT_SIZE = 30;
    private static final int FONT_SIZE = 16;
    private static final Font FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, TITLE_FONT_SIZE);
    private static final Color BUTTON_COLOR = Color.decode("#7F8C8D");
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private patient patientManager; // Instance of patient management class
    private doctor doctorManager; // Instance of doctor management class

    public oop() {
        setTitle("Cloud Care System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        patientManager = new patient(); // Initialize patient management
        doctorManager = new doctor("Ashir"); // Initialize doctor management with example name "Ashir"

        // Home panel
        JPanel homePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Cloud Care System", SwingConstants.CENTER);
        welcomeLabel.setFont(TITLE_FONT);
        homePanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton patientButton = createButton("View as a Patient");
        JButton doctorButton = createButton("View as a Doctor");
        JButton exitButton = createButton("Exit");

        buttonPanel.add(patientButton);
        buttonPanel.add(doctorButton);
        buttonPanel.add(exitButton);
        homePanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(homePanel, "Home");

        // Patient panel
        JPanel patientPanel = createPatientPanel();
        mainPanel.add(patientPanel, "Patient");

        // Doctor panel
        JPanel doctorPanel = createDoctorPanel();
        mainPanel.add(doctorPanel, "Doctor");

        add(mainPanel);
        cardLayout.show(mainPanel, "Home");

        patientButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient"));
        doctorButton.addActionListener(e -> cardLayout.show(mainPanel, "Doctor"));
        exitButton.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(FONT);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        return button;
    }

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Patient Menu", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addPatientButton = createButton("Add Patient");
        JButton viewPatientsButton = createButton("View Patients");
        JButton deletePatientsButton = createButton("Delete All Patients");
        JButton searchPatientButton = createButton("Search Patient");
        JButton backButton = createButton("Back");

        buttonPanel.add(addPatientButton);
        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(deletePatientsButton);
        buttonPanel.add(searchPatientButton);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        addPatientButton.addActionListener(e -> patientManager.add_patient());
        viewPatientsButton.addActionListener(e -> patientManager.view_patients());
        deletePatientsButton.addActionListener(e -> patientManager.deleteAllPatients());
        searchPatientButton.addActionListener(e -> {
            String searchTerm = JOptionPane.showInputDialog(panel, "Enter patient ID or Name to search:");
            if (searchTerm != null) {
                patientManager.search_patient(searchTerm);
            }
        });
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        return panel;
    }

    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Doctor Menu", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton viewDoctorButton = createButton("View Doctor Records");
        JButton backButton = createButton("Back");

        buttonPanel.add(viewDoctorButton);
        buttonPanel.add(backButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        viewDoctorButton.addActionListener(e -> doctorManager.view_Doctor());
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            oop mainFrame = new oop();
            mainFrame.setVisible(true);
        });
    }
}

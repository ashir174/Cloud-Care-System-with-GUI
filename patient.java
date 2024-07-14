import javax.swing.*;
import java.io.*;
import java.awt.*;

public class patient {
    private static final Font FONT = new Font("Arial", Font.PLAIN, 18); // Adjust font size here

    public void add_patient() {
        try {
            JTextField nameField = new JTextField();
            JTextField ageField = new JTextField();
            JTextField genderField = new JTextField();
            JTextField idField = new JTextField();
            JTextField appointmentDateField = new JTextField();

            Object[] fields = {
                "Name:", nameField,
                "Age:", ageField,
                "Gender:", genderField,
                "ID:", idField,
                "Appointment Date (yyyy-mm-dd):", appointmentDateField
            };

            int result = JOptionPane.showConfirmDialog(null, fields, "Add Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                int id = Integer.parseInt(idField.getText());
                String appointmentDate = appointmentDateField.getText();

                if (!isAppointmentAvailable(appointmentDate)) {
                    JOptionPane.showMessageDialog(null, "Appointment date is already booked. Please choose another date.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    FileWriter writer = new FileWriter("add_patients.txt", true);
                    writer.write("Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Appointment Date: " + appointmentDate + "\n");
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Patient information and appointment added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while writing to the file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Age and ID.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean isAppointmentAvailable(String appointmentDate) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("add_patients.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Appointment Date: " + appointmentDate)) {
                    reader.close();
                    return false;
                }
            }

            reader.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while checking the file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    public void view_patients() {
        try {
            JTextArea textArea = new JTextArea(20, 50);
            textArea.setFont(FONT);
            JScrollPane scrollPane = new JScrollPane(textArea);
            BufferedReader reader = new BufferedReader(new FileReader("add_patients.txt"));
            String line;

            textArea.append("Patient Records:\n");
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

            reader.close();
            JOptionPane.showMessageDialog(null, scrollPane, "View Patients", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void deleteAllPatients() {
        try {
            FileWriter writer = new FileWriter("add_patients.txt");
            writer.write("");
            writer.close();
            JOptionPane.showMessageDialog(null, "All patient data has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while deleting patient data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void search_patient(String searchTerm) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("add_patients.txt"));
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Patient ID: " + searchTerm) || line.contains("Name: " + searchTerm)) {
                    JOptionPane.showMessageDialog(null, "Patient Found:\n" + line, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }

            reader.close();

            if (!found) {
                JOptionPane.showMessageDialog(null, "Patient with ID or Name " + searchTerm + " not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

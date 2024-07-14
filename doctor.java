import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;

public class doctor {
    private String name;

    public doctor(String name) {
        this.name = name;
    }

    public void view_Doctor() {
        try {
            JTextArea textArea = new JTextArea(20, 50);
            textArea.setFont(new Font("Arial", Font.PLAIN, 18));
            JScrollPane scrollPane = new JScrollPane(textArea);
            BufferedReader reader = new BufferedReader(new FileReader("add_patients.txt"));
            String line;

            textArea.append("Doctor Records for " + name + ":\n");
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }

            reader.close();
            JOptionPane.showMessageDialog(null, scrollPane, "View Doctor Records", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

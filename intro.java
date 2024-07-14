import java.io.FileWriter;
import java.io.IOException;

public class intro extends patient {

    public void discription(){
        System.out.println("\n\n\t\t\tWelcome to the Cloud Care System\n\n");
    }

    public void add_patient(String name, int age, String gender, int id, String appointmentDate) {
        try {
            FileWriter writer = new FileWriter("add_patients.txt", true);

            writer.write("Patient ID: " + id + ", Name: " + name + ", Age: " + age +
                    ", Gender: " + gender + ", Appointment Date: " + appointmentDate + "\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class WriteToFile {
    WriteToFile(Trainee tn) {
        try {
            FileWriter myWriter = new FileWriter("trainee.txt");
            myWriter.write(tn.getTraineeName());
            myWriter.write(tn.getServicePoints());
            myWriter.write(tn.getDishesServed());
            myWriter.write(tn.getDishesReturned());
            myWriter.write(tn.getNumFed());
            myWriter.write(tn.getCuriousAmbroxians());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          }
          catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
      
      
    }
}

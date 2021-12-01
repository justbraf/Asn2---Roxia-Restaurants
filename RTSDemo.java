import java.util.Scanner;
import java.util.Random;
public class RTSDemo {
    
    public static void seatDiner(char species, Restaurant theDiner) {
        Scanner inputStream = new Scanner(System.in);
        System.out.print("Would you like to seat your guest <S> or turn them away <T>? ");
        char choice = inputStream.next().charAt(0);
        while (Character.toLowerCase(choice) != 's' && Character.toLowerCase(choice) != 't') {
            System.out.print("Invalid Entry!\nWould you like to seat your guest <S> or turn them away <T>? ");
            choice = inputStream.next().charAt(0);
        }
        if (Character.toLowerCase(choice) == 's') {
            System.out.print("Please enter the name of your guest: ");
            String guestName = inputStream.next();
            System.out.print("Please choose a seat from the " + (species == 's'? "Scoraxian":"Zoraxian") + " Zone A table: ");
            int seatPos = inputStream.nextInt();
            while (seatPos < 1 || seatPos > 15) {
                System.out.print("Invalid Entry! Seat numbers 1 to 15 only: ");
                seatPos = inputStream.nextInt();
            }
            if (species == 's') {
                Scoraxian theGuest = new Scoraxian(guestName);
                while (!theDiner.fillSeat((seatPos - 1), theGuest)){
                    System.out.print("That seat is taken. Please try another: ");
                    seatPos = inputStream.nextInt();
                }
            }
            else if (species == 'z') {
                Zoraxian theGuest = new Zoraxian(guestName);
                while (!theDiner.fillSeat((seatPos - 1), theGuest)) {
                    System.out.print("That seat is taken. Please try another: ");
                    seatPos = inputStream.nextInt();
                }
            }
            
        }
        else {
            System.out.println("You turned away the " + (species == 's'? "Scoraxian":"Zoraxian"));
        }

        inputStream.close();
    }

    public static void main(String[] args) {
        new Splash(); // Splash loading screen
        Trainee newTrainee = new Trainee("traineeName"); // create Trainee object with global scope
        Restaurant myDiner = new Restaurant(); // create Restaurant object with global scope
        myDiner.setUpZoneA(); // Generate the eight initial random guest
        View myView = new View(myDiner, newTrainee); // create View object and pass references to the global objects
        new UserForm(newTrainee, myView); //pass reference to the UserForm class
    }
}

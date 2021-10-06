import java.util.Scanner;

import javax.print.attribute.PrintRequestAttributeSet;

public class RTSDemo {
    // Display a user menu.
    public static void printMenu() {
        /*
        Menu options
        -fill/cear a seat
        -display tables
        -exit demo
        */
    }

    public static void userStats(Trainee trainMe) {
        System.out.println("\n\nName: " + trainMe.getTraineeName() + "\n\nService Points: " + trainMe.getServicePoints());
        System.out.println("Deaths: " + trainMe.getNumDeaths() + "\nFed: " + trainMe.getNumFed() + "\n\n");
    }

    public static void main(String[] args) {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Welcome to the Roxia Management System.");
        Restaurant myDiner = new Restaurant();
        String traineeName = inputStream.next();
        Trainee newTrainee = new Trainee(traineeName);
        myDiner.setUpZoneA();
        userStats(newTrainee);
        myDiner.displayTables();


        inputStream.close();
    }
}

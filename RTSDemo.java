import java.util.Scanner;
import java.util.Random;
public class RTSDemo {
    // Display a user menu.
    public static void printMenu() {
        System.out.println("Please Select an option from the menu:\nF - Fill a seat\nC - Clear a seat\nD - Display tables\nE - Exit Demo");
    }

    public static void userStats(Trainee trainMe) {
        System.out.println("\nName: " + trainMe.getTraineeName() + "\nService Points: " + trainMe.getServicePoints());
        System.out.println("Deaths: " + trainMe.getNumDeaths() + "\nFed: " + trainMe.getNumFed() + "\n");
    }
    
    public static void seatDiner(char species, Restaurant theDiner) {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Would you like to seat your guest <S> or turn them away <T>?");
        char choice = inputStream.next().charAt(0);
        if (Character.toLowerCase(choice) == 's') {
            System.out.println("What is the name of your guest?");
            String guestName = inputStream.next();
            System.out.print("Please choose a seat from the " + (species == 's'? "Scoraxian":"Zoraxian") + " Zone A table: ");
            int seatPos = inputStream.nextInt();
            if (species == 's') {
                Scoraxian theGuest = new Scoraxian(guestName);
                if (!theDiner.fillSeat((seatPos - 1), theGuest)) {
                    System.out.println("That seat is taken");
                }
            }
            else if (species == 'z') {
                Zoraxian theGuest = new Zoraxian(guestName);
                if (!theDiner.fillSeat((seatPos - 1), theGuest)) {
                    System.out.println("That seat is taken");
                }
            }
            
        }
        else {
            System.out.println("You turned away the " + (species == 's'? "Scoraxian":"Zoraxian"));
        }

        // inputStream2.close();
    }

    public static void main(String[] args) {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Welcome to the Roxia Management System.");
        Restaurant myDiner = new Restaurant();
        String traineeName = inputStream.next();
        Random newDiner = new Random();
        Trainee newTrainee = new Trainee(traineeName);
        myDiner.setUpZoneA();
        userStats(newTrainee);
        int zoraEC;
        for (int rounds = 1; rounds < 8; rounds++) {
            System.out.println("************** ROUND " + rounds + " ***************");
            myDiner.displayTables();
            // printMenu();
            zoraEC = myDiner.zoraxianEnergyCheck(); // Check for weak Zoraxians and consume them if any are found
            newTrainee.setNumDeaths(newTrainee.getNumDeaths() + zoraEC); // Record the number of deaths
            newTrainee.setServicePoints(newTrainee.getServicePoints() - 3 * zoraEC); // Deduct service points
            //Seating two new guest
            for (int guest = 0; guest < 2; guest++) {
                if (newDiner.nextBoolean()) { //Seat a new Scoraxian diner
                    System.out.println("A Scoraxian is waiting to be seated.");
                    seatDiner('s', myDiner);
                }
                else {
                    System.out.println("A Zoraxian is waiting to be seated.");
                    seatDiner('z', myDiner);
                }
            }
            
            
            if (rounds % 2 == 0) { // Deplete hunger every second round
                for (int sx = 0; sx < myDiner.scoraxTableZoneA.length; sx++) {
                    // Check each seat at the table and deplete hunger by one, if occupied
                    if (myDiner.scoraxTableZoneA[sx] != null) {
                        myDiner.scoraxTableZoneA[sx].setEnergyLevel(myDiner.scoraxTableZoneA[sx].getEnergyLevel() - 1);
                    }
                }
                for (int zx = 0; zx < myDiner.zoraxTableZoneA.length; zx++) {
                    // Check each seat at the table and deplete hunger by one, if occupied
                    if (myDiner.zoraxTableZoneA[zx] != null) {
                        myDiner.zoraxTableZoneA[zx].setEnergyLevel(myDiner.zoraxTableZoneA[zx].getEnergyLevel() - 1);
                    }
                }
                // try {
                //     Thread.sleep(2000);
                // } catch (InterruptedException e) {
                //     System.out.println("Thread is interrupted");
                // }
            }
            
        }
        System.out.println("\n\nFinal");
        myDiner.displayTables();
        userStats(newTrainee);


        inputStream.close();
    }
}

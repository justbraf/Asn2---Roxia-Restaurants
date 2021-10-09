import java.util.Scanner;
import java.util.Random;
public class RTSDemo {
    // Display a user menu.
    public static void printMenu() {
        System.out.println("Please Select an option from the menu:\nF - Fill a seat\nC - Clear a seat\nD - Display tables\nE - Exit Demo");
    }

    public static void userStats(Trainee trainMe) {
        System.out.println("\nName: " + trainMe.getTraineeName() + "\nService Points: " + trainMe.getServicePoints());
        System.out.println("Fed: " + trainMe.getNumFed() + "\nDeaths: " + trainMe.getNumDeaths());
    }
    
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

        // inputStream2.close();
    }

    public static void main(String[] args) {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Welcome to the Roxia Management System.");
        Restaurant myDiner = new Restaurant();
        String traineeName = inputStream.next();
        Random newDiner = new Random();
        int curiousAmbroxians = 0;
        Trainee newTrainee = new Trainee(traineeName);
        myDiner.setUpZoneA();
        userStats(newTrainee);
        int zoraEC;
        for (int rounds = 1; rounds < 8; rounds++) {
            // System.out.print("\033[H\033[2J"); // Clear the console
            // System.out.flush(); // Empty the buffer
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

            // 10% chance an Ambroxian will wander into Zone A
            if (newDiner.nextInt(10) == 4) {
                System.out.println("########### Warning! An Ambroxian has wandered into Zone A ###########");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted");
                }
                int hungriestScoraEnergy = 9999;
                int hungriestScoraPos = 9999;
                int hungriestZoraEnergy = 9999;
                int hungriestZoraPos = 9999;
                for (int idx = 0; idx < myDiner.scoraxTableZoneA.length; idx++) {
                    if (myDiner.scoraxTableZoneA[idx] != null) {
                        if (myDiner.scoraxTableZoneA[idx].getEnergyLevel() < hungriestScoraEnergy) {
                            hungriestScoraEnergy = myDiner.scoraxTableZoneA[idx].getEnergyLevel();
                            hungriestScoraPos = idx;
                        }
                    }
                }
                for (int idx = 0; idx < myDiner.zoraxTableZoneA.length; idx++) {
                    if (myDiner.zoraxTableZoneA[idx] != null) {
                        if (myDiner.zoraxTableZoneA[idx].getEnergyLevel() < hungriestZoraEnergy) {
                            hungriestZoraEnergy = myDiner.zoraxTableZoneA[idx].getEnergyLevel();
                            hungriestZoraPos = idx;
                        }
                    }
                }
                boolean scoraOrZora; //false - Scoraxian eats it; true - Zoraxian eats it;
                if (hungriestScoraEnergy < hungriestZoraEnergy) {
                    //Scora eats it
                    scoraOrZora = false;
                }
                else if (hungriestScoraEnergy > hungriestZoraEnergy) {
                    //Zora eats it
                    scoraOrZora = true;
                }
                else {
                    // randomly pick one if they have the same energy
                    scoraOrZora = newDiner.nextBoolean();
                }
                if (scoraOrZora) {
                    myDiner.zoraxTableZoneA[hungriestZoraPos].setEnergyLevel(myDiner.zoraxTableZoneA[hungriestZoraPos].getEnergyLevel() + 2);
                    System.out.println("Nevermind! A Zoraxian sitting in seat number " + (hungriestZoraPos + 1) + " ate it.");
                    curiousAmbroxians++;
                }
                else {
                    myDiner.scoraxTableZoneA[hungriestScoraPos].setEnergyLevel(myDiner.scoraxTableZoneA[hungriestScoraPos].getEnergyLevel() + 2);
                    System.out.println("Nevermind! A Scoraxian sitting in seat number " + (hungriestScoraPos + 1) + " ate it.");
                    curiousAmbroxians++;
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
                
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
            }
            
        }
        System.out.println("\n\n########## Summary ##########");
        // myDiner.displayTables();
        userStats(newTrainee);
        System.out.println("Curious Ambroxians that met an unfortunate demise: " + curiousAmbroxians);


        inputStream.close();
    }
}

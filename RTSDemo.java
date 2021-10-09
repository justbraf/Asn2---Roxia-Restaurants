import java.util.Scanner;
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
        System.out.println("\nName: " + trainMe.getTraineeName() + "\nService Points: " + trainMe.getServicePoints());
        System.out.println("Deaths: " + trainMe.getNumDeaths() + "\nFed: " + trainMe.getNumFed() + "\n");
    }

    public static void main(String[] args) {
        Scanner inputStream = new Scanner(System.in);
        System.out.println("Welcome to the Roxia Management System.");
        Restaurant myDiner = new Restaurant();
        String traineeName = inputStream.next();
        Trainee newTrainee = new Trainee(traineeName);
        myDiner.setUpZoneA();
        userStats(newTrainee);
        // char sp; int spos;
        int zoraEC;
        for (int rounds = 1; rounds < 8; rounds++) {
            System.out.println("************** ROUND " + rounds + " ***************");
            myDiner.displayTables();
            // sp = inputStream.next().charAt(0);
            // spos = inputStream.nextInt();
            // myDiner.clearSeat(spos-1, sp);
            zoraEC = myDiner.zoraxianEnergyCheck(); // Check for weak Zoraxians and consume them if any are found
            newTrainee.setNumDeaths(newTrainee.getNumDeaths() + zoraEC); // Record the number of deaths
            newTrainee.setServicePoints(newTrainee.getServicePoints() - 3 * zoraEC); // Deduct service points
            userStats(newTrainee);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
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
            
        }
        System.out.println("\n\nFinal");
        myDiner.displayTables();


        inputStream.close();
    }
}

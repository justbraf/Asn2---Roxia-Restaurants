import java.util.Random;

/**
 * Restaurant
 */
public class Restaurant {
    // Data Members
    private int numAmbroxians;
    private int numScoraxians;
    private int numZoraxians;
    Zoraxian[] zoraxTableZoneA; // Used for seating Zoraxians in Zone A.
    Scoraxian[] scoraxTableZoneA; // Used for seating Scoraxians in Zone A.
    Diner[][] tablesZoneB; // Used for seating in Zone B.
    
    // Constructor
    Restaurant() {
        numAmbroxians = 0;
        numScoraxians = 0;
        numZoraxians = 0;
        zoraxTableZoneA = new Zoraxian[15];
        scoraxTableZoneA = new Scoraxian[15];
        tablesZoneB = new Diner[5][6];

    }

    /* Randomly populate seats at tables in Zone A. 
    Each seat has one of three possible states: contain a Zoraxian, a Scoraxian or be empty.*/
    public void setUpZoneA() {
        Random placement = new Random();
        int seatPos = 0;
        boolean dinerType;
        Diner theDiner = new Diner();
        do {
            seatPos = placement.nextInt(15); // Generate a seat position for one of the fifteen seats at either table
            dinerType = placement.nextBoolean(); // Randomly select a type of diner
            if (dinerType) {
                theDiner = new Zoraxian("Zora " + (seatPos + 1));
            }
            else {
                theDiner = new Scoraxian("Scora " + (seatPos + 1));
            }
            // Randomly generate a seat position unil the diner is seated
            while (!fillSeat(seatPos, theDiner)) {
                seatPos = placement.nextInt(15);
            }
        } while ((numScoraxians + numZoraxians) < 8); // Finish zone setup when eight diners are seated
    }

    // Assign a Zoraxian or a Scoraxian to a given seat position and return true, if the diner was assigned to an empty seat.
    public boolean fillSeat(int seatPos, Diner diner) {
        if (diner.getSpeciesCode() == 'z') {
            // Assign a Zoraxian to a seat
            if (zoraxTableZoneA[seatPos] == null) { // seat is empty
                zoraxTableZoneA[seatPos] = (Zoraxian)diner; // therefore fill it
                numZoraxians += 1; // count
                return true;
            }
        }
        else if (diner.getSpeciesCode() == 's') {
            // Assign a Scoraxian to a seat
            if (scoraxTableZoneA[seatPos] == null) { // seat is empty
                scoraxTableZoneA[seatPos] = (Scoraxian)diner; // therefore fill it
                numScoraxians += 1;
                return true;
            }
        }
        // else if (diner.getSpeciesCode() == 'a') {
            // Assign a Ambroxian to a seat
        // }
        return false;
    }

    // Clear a given seat position and species of diner and return true, if there's a match and the diner removed.
    public boolean clearSeat(int seatPos, char species) {
        if (species == 'z') {
            if (zoraxTableZoneA[seatPos] != null) {
                zoraxTableZoneA[seatPos] = null;
                setNumZoraxians(getNumZoraxians() - 1);
                return true;
            }
        }
        else if (species == 's') {
            if (scoraxTableZoneA[seatPos] != null) {
                scoraxTableZoneA[seatPos] = null;
                setNumScoraxians(getNumScoraxians() - 1);
                return true;
            }
        }
        // else if (species == 'a') {
            // remove Ambroxian from a table
            // setNumAmbroxians(getNumAmbroxians()  - 1);
        // }
        return false;
    }

    // Prints the contents of the seating arrangements in a given format.
    public void displayTables() {
        // Print Scoraxian table seating
        System.out.print("Scoraxians:\nSeat #: 1");
        for (int seatNum = 2; seatNum <= 15; seatNum++) {
            if (seatNum < 11)
                System.out.print(" | ");
            else
                System.out.print("| ");
            System.out.print(seatNum);
        }
        System.out.print("\nEnergy: ");
        for (int i = 0; i < scoraxTableZoneA.length; i++) {
            if (scoraxTableZoneA[i] != null){
                System.out.print(scoraxTableZoneA[i].getEnergyLevel());
                if (i < (scoraxTableZoneA.length - 1))
                    if (scoraxTableZoneA[i].getEnergyLevel() < 10)
                        System.out.print(" | ");
                    else
                        System.out.print("| ");
            }
            else
                if (i < (scoraxTableZoneA.length - 1))
                    System.out.print("  | ");
        }
        // Print Zoraxian table seating
        System.out.print("\n\nZoraxians:\nSeat #: 1");
        for (int seatNum = 2; seatNum <= 15; seatNum++) {
            if (seatNum < 11)
                System.out.print(" | ");
            else
                System.out.print("| ");
            System.out.print(seatNum);
        }
        System.out.print("\nEnergy: ");
        for (int i = 0; i < zoraxTableZoneA.length; i++) {
            if (zoraxTableZoneA[i] != null){
                System.out.print(zoraxTableZoneA[i].getEnergyLevel());
                if (i < (zoraxTableZoneA.length - 1))
                    if (zoraxTableZoneA[i].getEnergyLevel() < 10)
                        System.out.print(" | ");
                    else
                        System.out.print("| ");
            }
            else
                if (i < (zoraxTableZoneA.length - 1))
                    System.out.print("  | ");
        }
        System.out.println("\n");
    }

    public int zoraxianEnergyCheck() {
        int totalEaten = 0;
        for (int ndx=0; ndx < zoraxTableZoneA.length; ndx++) {
            if (zoraxTableZoneA[ndx] != null) {
                if (zoraxTableZoneA[ndx].getEnergyLevel() < 3) {
                    if (scoraxTableZoneA[ndx] != null) {
                        // scoraxTableZoneA[ndx].setEnergyLevel(scoraxTableZoneA[ndx].getEnergyLevel() + #);
                        System.out.println("The Zoraxian in Zone A seat " + (ndx + 1) + " was consumed by the Scoraxian in Zone A seat " + (ndx + 1));
                        clearSeat(ndx, 'z');
                        totalEaten++;
                    }
                    else {
                        int ndx2 = ndx + 1;
                        boolean searchingForNextScoraxian = true;
                        while (searchingForNextScoraxian) {
                            if (ndx2 < scoraxTableZoneA.length) {
                                if (scoraxTableZoneA[ndx2] == null) {
                                    ndx2++;
                                }
                                else
                                    searchingForNextScoraxian = false;
                            }
                            else
                                searchingForNextScoraxian = false;
                        }
                        if (ndx2 == 15)
                            System.out.println("No Scoraxians are seated to the right to consume the Zoraxian.");
                        else {
                            System.out.println("The Zoraxian in Zone A seat " + (ndx + 1) + " was consumed by the Scoraxian in Zone A seat " + (ndx2 + 1));
                            clearSeat(ndx, 'z');
                            totalEaten++;
                        }
                    }
                }
            }
        }
        return totalEaten;
    }

    // Mutators and Accessors
    public void setNumAmbroxians(int numAmb) {
        numAmbroxians = numAmb;
    }
    public int getNumAmbroxians() {
        return numAmbroxians;
    }

    public void setNumScoraxians(int numSco) {
        numScoraxians = numSco;
    }
    public int getNumScoraxians() {
        return numScoraxians;
    }

    public void setNumZoraxians(int numZor) {
        numZoraxians = numZor;
    }
    public int getNumZoraxians() {
        return numZoraxians;
    }
       
}

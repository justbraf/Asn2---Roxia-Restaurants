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
        int seatsPlaced = 0;
        int seatPos = 0;
        boolean dinerType;
        Diner theDiner = new Diner();
        do {
            // Generate a seat position for one of the fifteen seats at either table
            seatPos = placement.nextInt(15);
            dinerType = placement.nextBoolean();
            if (dinerType) {
                theDiner = new Zoraxian("Alf");
            }
            else {
                theDiner = new Scoraxian("ET");
            }
            while (!fillSeat(seatPos, theDiner)) {
                seatPos = placement.nextInt(15);
            }
            seatsPlaced += 1;
            System.out.println("Seat Pos: " + (seatPos + 1));
        } while (seatsPlaced < 8);
    }

    // Assign a Zoraxian or a Scoraxian to a given seat position and return true, if the diner was assigned to an empty seat.
    public boolean fillSeat(int seatPos, Diner diner) {
        if (diner.getSpeciesCode() == 'z') {
            // Assign a Zoraxian
            System.out.println(diner.getName() + " " + diner.getEnergyLevel());
            if (zoraxTableZoneA[seatPos] == null) {
                zoraxTableZoneA[seatPos] = (Zoraxian)diner;
                return true;
            }
            else {
                System.out.println("already in use!");
            }
        }
        else {
            // Assign a Scoraxian
            System.out.println(diner.getName() + " " + diner.getEnergyLevel());
            if (scoraxTableZoneA[seatPos] == null) {
                scoraxTableZoneA[seatPos] = (Scoraxian)diner;
                return true;
            }
            else {
                System.out.println("already in use!");
            }
        }
        return false;
    }

    // Clear a given seat position and species of diner and return true, if there's a match and the diner removed.
    public boolean clearSeat(int seatPos, char species) {
        return true;
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
    public int getNumAScoraxians() {
        return numScoraxians;
    }

    public void setNumZoraxians(int numZor) {
        numZoraxians = numZor;
    }
    public int getNumZoraxians() {
        return numZoraxians;
    }
       
}

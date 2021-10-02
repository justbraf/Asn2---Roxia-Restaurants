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
    Scoraxian[] scoraTableZoneA; // Used for seating Scoraxians in Zone A.
    Diner[][] tablesZoneB; // Used for seating in Zone B.
    
    // Constructor
    Restaurant() {}

    /* Randomly populate seats at tables in Zone A. 
    Each seat has one of three possible states: contain a Zoraxian, a Scoraxian or be empty.*/
    public void setUpZoneA() {
        Random placement = new Random();
        // Generate one seat position for the fifteen seats at the table
        int seatPos = placement.nextInt(15) + 1;
    }

    // Assign a Zoraxian or a Scoraxian to a given seat position and return true, if the diner was assigned to an empty seat.
    public boolean fillSeat(int seatPos, Diner diner) {
        return true;
    }

    // Clear a given seat position and species of diner and return true, if there's a match and the diner removed.
    public boolean clearSeat(int seatPos, char species) {
        return true;
    }

    // Prints the contents of the seating arrangements in a given format.
    public void displayTables() {}

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

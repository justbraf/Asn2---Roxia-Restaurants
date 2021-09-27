public class Restaurant {
    private int numAmbroxians;
    private int numScoraxians;
    private int numZoraxians;
    Zoraxian[] zoraxTableZoneA;
    Scoraxian[] scoraTableZoneA;
    Diner[][] tablesZoneB;
    
    Restaurant() {}

    public void setUpZoneA() {}
    public boolean fillSeat(int seatPos, Diner diner) {
        return true;
    }
    public boolean clearSeat(int seatPos, char species) {
        return true;
    }
    public void displayTables() {}
    public void printMenu() {}

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

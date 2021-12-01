/**
 * Trainee
 */
public class Trainee {
    // Data Members
    private String traineeName;
    private int servicePoints;
    private int numDeaths;
    private int numFed;
    private int dishesServed;
    private int dishesReturned;
    private int curiousAmbroxians;
    private String incidentRecords;

    // Constructor
    Trainee(String name) {
        traineeName = name;
        servicePoints = 0;
        numDeaths = 0;
        numFed = 0;
        dishesServed = 0;
        dishesReturned = 0;
        curiousAmbroxians = 0;
        incidentRecords = "";
    }

    // Mutators and Accessors
    public void setTraineeName(String name) {
        traineeName = name;
    }
    public String getTraineeName() {
        return traineeName;
    }

    public void setServicePoints(int sp) {
        servicePoints = sp;
    }
    public int getServicePoints() {
        return servicePoints;
    }

    public void setNumDeaths(int nd) {
        numDeaths = nd;
    }
    public int getNumDeaths() {
        return numDeaths;
    }

    public void setNumFed(int nf) {
        numFed = nf;
    }
    public int getNumFed() {
        return numFed;
    }

    // Increase number of dishes served by one
    public void incDishesServed() {
        dishesServed += 1;
    }
    public int getDishesServed() {
        return dishesServed;
    }

    // Increase number of dishes returned by one
    public void incDishesReturned() {
        dishesReturned += 1;
    }
    public int getDishesReturned() {
        return dishesReturned;
    }

    // Increase the number of curious ambroxians by one
    public void incCuriousAmbroxians() {
        curiousAmbroxians += 1;
    }
    public int getCuriousAmbroxians() {
        return curiousAmbroxians;
    }

    public void setIncidentRecords(String val) {
        incidentRecords = incidentRecords + "\n" + val;
    }
    public String getIncidentRecords() {
        return incidentRecords;
    }
}

/**
 * Trainee
 */
public class Trainee {
    // Data Members
    private String traineeName;
    private int servicePoints;
    private int numDeaths;
    private int numFed;

    // Constructor
    Trainee(String name) {
        traineeName = name;
        servicePoints = 0;
        numDeaths = 0;
        numFed = 0;
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
}

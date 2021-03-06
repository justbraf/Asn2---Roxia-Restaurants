/**
 * Diner
 */
public class Diner {
    // Data members
    private String name;
    private char speciesCode;
    private String species;
    private int energyLevel;

    // Constructor
    Diner() {
        name = "??";
        species = "D";
        speciesCode = 'D';
    }

    // Mutators and Accessors
    public void setName(String theName) {
        name = theName;
    }
    public String getName() {
        return name;
    }

    public void setSpeciesCode(char sCode) {
        speciesCode = sCode;
    }
    public char getSpeciesCode() {
        return speciesCode;
    }

    public void setSpecies(String theSpecies) {
        species = theSpecies;
    }
    public String getSpecies() {
        return species;
    }

    public void setEnergyLevel(int eLevel) {
        energyLevel = eLevel;
    }
    public int getEnergyLevel() {
        return energyLevel;
    }
}
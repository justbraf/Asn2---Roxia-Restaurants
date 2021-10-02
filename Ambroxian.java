/**
 * Ambroxian
 */
public class Ambroxian extends Diner {
    // Data Members
    private boolean isBerserk;

    // Constructor
    Ambroxian(String name) {
        setName(name);
        setSpecies("ambroxian");
        setSpeciesCode('a');
        setEnergyLevel(0);
        setIsBerserk(false);
    }

    // Mutators and Accessors
    public void setIsBerserk(boolean berserk) {
        isBerserk = berserk;
    }
    public boolean getIsBerserk() {
        return isBerserk;
    }
}

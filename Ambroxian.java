public class Ambroxian extends Diner {
    private boolean isBerserk;

    Ambroxian(String name) {
        setName(name);
        setSpecies("ambroxian");
        setSpeciesCode('a');
        setEnergyLevel(0);
        setIsBerserk(false);
    }

    public void setIsBerserk(boolean berserk) {
        isBerserk = berserk;
    }
    public boolean getIsBerserk() {
        return isBerserk;
    }
}

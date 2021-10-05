/**
 * Scoraxian
 */
public class Scoraxian extends Diner {
    // Constructor
    Scoraxian(String name) {
        setName(name);
        setSpecies("scoraxian");
        setSpeciesCode('s');
        setEnergyLevel(0); // random value 5 to 10 inclusive rnd.nextInt(6) + 5
    }
}

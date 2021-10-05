/**
 * Zoraxian
 */
public class Zoraxian extends Diner {
    // Constructor
    Zoraxian(String name) {
        setName(name);
        setSpecies("zoraxian");
        setSpeciesCode('z');
        setEnergyLevel(0);  // random value 4 to 7 inclusive rnd.nextInt(4) + 4)
    }
}

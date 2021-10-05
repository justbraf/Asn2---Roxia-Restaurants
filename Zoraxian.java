import java.util.Random;

/**
 * Zoraxian
 */
public class Zoraxian extends Diner {
    // Constructor
    Zoraxian(String name) {
        setName(name);
        setSpecies("zoraxian");
        setSpeciesCode('z');
        Random generateEnergy = new Random();
        setEnergyLevel(generateEnergy.nextInt(4) + 4);  // Generate a random energy value between 4 and 7 inclusive
    }
}

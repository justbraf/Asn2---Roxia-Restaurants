import java.util.Random;

/**
 * Scoraxian
 */
public class Scoraxian extends Diner {
    // Constructor
    Scoraxian(String name) {
        setName(name);
        setSpecies("scoraxian");
        setSpeciesCode('s');
        Random generateEnergy = new Random();
        setEnergyLevel(generateEnergy.nextInt(6) + 5);  // Generate a random energy value between 5 and 10 inclusive
    }
}

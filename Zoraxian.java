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

    // Manage the energy level of a Zoraxian based on what was eaten
    public void FeedMe (Food dish) throws GreedyGutsException, FullyFedException {
        if (dish.getDishName() == "Scoron")
            setEnergyLevel(getEnergyLevel() + (dish.getDishEnergy() / 2));
        else if (dish.getDishName() == "Elixon")
            setEnergyLevel((int)(getEnergyLevel() * 1.25));
        else
            setEnergyLevel(getEnergyLevel() + dish.getDishEnergy());
        if (getEnergyLevel() > 15)
            throw new GreedyGutsException("death by greed");
        else if (getEnergyLevel() == 15)
            throw new FullyFedException("fully fed");
    }
}

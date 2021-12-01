import java.util.Random;

/**
 * Scoraxian
 */
public class Scoraxian extends Diner {
    // Constructor
    Scoraxian(String name) {
        setName(name);
        setSpecies("Scoraxian");
        setSpeciesCode('s');
        Random generateEnergy = new Random();
        setEnergyLevel(generateEnergy.nextInt(6) + 5);  // Generate a random energy value between 5 and 10 inclusive
    }

    // Manage the energy level of a Scoraxian based on what was eaten
    public void FeedMe (Food dish) throws GreedyGutsException, AllergyException, FullyFedException {
        if (dish.getDishName() == "Zoron")
            throw new AllergyException("death by allergic reaction");
        else if (dish.getDishName() == "Elixon")
            setEnergyLevel((int)(getEnergyLevel() * 0.75));
        else
            setEnergyLevel(getEnergyLevel() + dish.getDishEnergy());
        if (getEnergyLevel() > 20)
            throw new GreedyGutsException("death by greed");
        else if (getEnergyLevel() == 20)
            throw new FullyFedException("fully fed");
    }
}

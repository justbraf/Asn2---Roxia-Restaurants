import java.util.Random;

public class Food {
    private final String[] dishNames = {"Ambron", "Scoron", "Zoron", "Elixon"};
    private String dishName = "";
    private int dishEnergy = 0;

    Food (int val) {
        Random randVal = new Random();
        if (val < 3) {
            dishName = dishNames[val];
        }
        else {
            dishName = dishNames[randVal.nextInt(4)];
        }
        dishEnergy = randVal.nextInt(4) + 2;
    }

    //Accessors
    public String getDishName() {
        return dishName;
    }

    public int getDishEnergy() {
        return dishEnergy;
    }
}

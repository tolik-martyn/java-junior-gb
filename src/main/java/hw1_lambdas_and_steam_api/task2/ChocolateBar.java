package hw1_lambdas_and_steam_api.task2;

/**
 * Шоколадный батончик
 */
public class ChocolateBar implements Snack{
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public boolean getCarbohydrates() {
        return true;
    }

    @Override
    public String getName() {
        return "Шоколадный батончик";
    }
}

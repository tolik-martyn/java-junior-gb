package hw1_lambdas_and_steam_api.task2.thing.food.snack;

/**
 * Чипсы
 */
public class Crisps implements Snack {
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return true;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Чипсы";
    }
}
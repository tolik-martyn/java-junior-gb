package hw1_lambdas_and_steam_api.task2.thing.food.healthy_food;

import hw1_lambdas_and_steam_api.task2.thing.food.healthy_food.HealthyFood;

/**
 * Фрукт
 */
public class Fruit implements HealthyFood {
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
        return "Фрукт";
    }
}

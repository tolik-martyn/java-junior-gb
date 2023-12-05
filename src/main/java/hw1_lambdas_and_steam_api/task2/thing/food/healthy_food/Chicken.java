package hw1_lambdas_and_steam_api.task2.thing.food.healthy_food;

import hw1_lambdas_and_steam_api.task2.thing.food.healthy_food.HealthyFood;

/**
 * Курица
 */
public class Chicken implements HealthyFood {
    @Override
    public boolean getProteins() {
        return true;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Курица";
    }
}

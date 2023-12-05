package hw1_lambdas_and_steam_api.task2.thing.food.healthy_food;

import hw1_lambdas_and_steam_api.task2.thing.food.healthy_food.HealthyFood;

/**
 * Оливковое масло
 */
public class OliveOil implements HealthyFood {
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
        return "Оливковое масло";
    }
}


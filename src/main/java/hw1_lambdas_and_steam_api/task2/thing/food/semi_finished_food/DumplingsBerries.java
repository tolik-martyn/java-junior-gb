package hw1_lambdas_and_steam_api.task2.thing.food.semi_finished_food;

/**
 * Замороженные ягоды
 */
public class DumplingsBerries implements SemiFinishedFood {
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
        return "Замороженные ягоды";
    }
}
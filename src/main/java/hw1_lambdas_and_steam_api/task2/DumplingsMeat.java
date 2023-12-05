package hw1_lambdas_and_steam_api.task2;

/**
 * Пельмени
 */
public class DumplingsMeat implements SemiFinishedFood{
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
        return "Пельмени";
    }
}


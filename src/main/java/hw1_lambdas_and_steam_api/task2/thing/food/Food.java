package hw1_lambdas_and_steam_api.task2.thing.food;

import hw1_lambdas_and_steam_api.task2.thing.Thing;

/**
 * Еда
 */
public interface Food extends Thing {

    /**
     * Получить наличие протеинов в еде
     * @return Наличие протеинов
     */
    boolean  getProteins();

    /**
     * Получить наличие жиров в еде
     * @return Наличие жиров
     */
    boolean getFats();

    /**
     * Получить наличие углеводов в еде
     * @return Наличие углеводов
     */
    boolean getCarbohydrates();

}

package hw1_lambdas_and_steam_api.task2;

import hw1_lambdas_and_steam_api.task2.thing.food.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     *
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        boolean proteins = hasProteins();
        boolean fats = hasFats();
        boolean carbohydrates = hasCarbohydrates();

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        if (!proteins) {
            market.getThings(clazz).stream()
                    .filter(Food::getProteins)
                    .findFirst()
                    .ifPresent(foodstuffs::add);
            proteins = hasProteins();
        }

        if (!fats) {
            market.getThings(clazz).stream()
                    .filter(Food::getFats)
                    .findFirst()
                    .ifPresent(foodstuffs::add);
            fats = hasFats();
        }

        if (!carbohydrates) {
            market.getThings(clazz).stream()
                    .filter(Food::getCarbohydrates)
                    .findFirst()
                    .ifPresent(foodstuffs::add);
            carbohydrates = hasCarbohydrates();
        }

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

    private boolean hasProteins() {
        return foodstuffs.stream().anyMatch(Food::getProteins);
    }

    private boolean hasFats() {
        return foodstuffs.stream().anyMatch(Food::getFats);
    }

    private boolean hasCarbohydrates() {
        return foodstuffs.stream().anyMatch(Food::getCarbohydrates);
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs() {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
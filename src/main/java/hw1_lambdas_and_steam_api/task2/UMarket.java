package hw1_lambdas_and_steam_api.task2;

import hw1_lambdas_and_steam_api.task2.thing.Notebook;
import hw1_lambdas_and_steam_api.task2.thing.Pen;
import hw1_lambdas_and_steam_api.task2.thing.Thing;
import hw1_lambdas_and_steam_api.task2.thing.food.Food;
import hw1_lambdas_and_steam_api.task2.thing.food.healthy_food.Chicken;
import hw1_lambdas_and_steam_api.task2.thing.food.healthy_food.Fruit;
import hw1_lambdas_and_steam_api.task2.thing.food.healthy_food.OliveOil;
import hw1_lambdas_and_steam_api.task2.thing.food.semi_finished_food.Cheburek;
import hw1_lambdas_and_steam_api.task2.thing.food.semi_finished_food.DumplingsBerries;
import hw1_lambdas_and_steam_api.task2.thing.food.semi_finished_food.DumplingsMeat;
import hw1_lambdas_and_steam_api.task2.thing.food.snack.BalykCheese;
import hw1_lambdas_and_steam_api.task2.thing.food.snack.ChocolateBar;
import hw1_lambdas_and_steam_api.task2.thing.food.snack.Crisps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class UMarket {

    private void initializeThings()
    {
        things.add(new Pen());
        things.add(new Notebook());

        things.add(new Chicken());
        things.add(new Fruit());
        things.add(new OliveOil());

        things.add(new BalykCheese());
        things.add(new Crisps());
        things.add(new ChocolateBar());

        things.add(new DumplingsBerries());
        things.add(new DumplingsMeat());
        things.add(new Cheburek());
    }

    //region Поля

    /**
     * Товары в магазине
     */
    private final Collection<Thing> things;

    //endregion


    //region Конструкторы

    public UMarket()
    {
        things = new ArrayList<>();
        initializeThings();
    }

    //endregion

    public <T extends Thing> void printThings(Class<T> clazz){
        /*Integer index = 1;
        for (var thing : things) {
            if (clazz.isInstance(thing)){
                if (Food.class.isAssignableFrom(thing.getClass()))
                {
                    System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, thing.getName(), ((Food)thing).getProteins() ? "Да" : "Нет",
                            ((Food)thing).getFats() ? "Да" : "Нет", ((Food)thing).getCarbohydrates() ? "Да" : "Нет");
                }
                else{
                    System.out.printf("[%d] %s\n", index++, thing.getName());
                }
            }
        }*/

        int[] index = {1};
        things.stream()
                .filter(clazz::isInstance)
                .forEach(thing -> {
                    if (Food.class.isAssignableFrom(thing.getClass())) {
                        Food foodThing = (Food) thing;
                        System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                                index[0]++, thing.getName(),
                                foodThing.getProteins() ? "Да" : "Нет",
                                foodThing.getFats() ? "Да" : "Нет",
                                foodThing.getCarbohydrates() ? "Да" : "Нет");
                    } else {
                        System.out.printf("[%d] %s\n", index[0]++, thing.getName());
                    }
                });
    }

    public <T extends Thing> T getThingByIndex(Class<T> clazz, int index){
        AtomicInteger counter = new AtomicInteger(1);
        return things.stream()
                .filter(clazz::isInstance)
                .filter(thing -> index == counter.getAndIncrement())
                .map(clazz::cast)
                .findFirst()
                .orElse(null);
    }

    public <T extends Thing> Collection<T> getThings(Class<T> clazz)
    {
        return things.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .toList();
    }


}

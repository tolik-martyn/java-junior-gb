package hw2_reflection_api.task1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        Animal[] animals = new Animal[]{
                new Dog("Барбос", 3, 20),
                new Cat("Мурзик", 2, true),
                new Fish("Карась", 1, 1.2),
                new Dog("Рекс", 4, 22),
                new Cat("Барсик", 3, false),
                new Fish("Карп", 2, 2.8),
                new Dog("Шарик", 5, 17),
                new Cat("Васька", 4, true),
                new Fish("Щука", 3, 2.0)
        };

        for (Animal animal : animals) {
            printObjectInfo(animal);
            callMakeSound(animal);
            System.out.println();
        }
    }

    /**
     * Метод для вывода информации об объекте
     *
     * @param obj Объект, информацию о котором необходимо вывести.
     */
    public static void printObjectInfo(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Информация об объекте класса " + clazz.getSimpleName() + ":");

        // Вывод информации о полях объекта и его родителей
        try {
            printFieldsRecursively(clazz, obj);
        } catch (IllegalAccessException e) {
            System.err.println("Возникло исключение в методе \"printObjectInfo\"");
        }
    }

    /**
     * Вспомогательный рекурсивный метод для вывода информации о полях текущего класса и его родителя.
     */
    private static void printFieldsRecursively(Class<?> clazz, Object obj) throws IllegalAccessException {
        if (clazz != null) {

            // Рекурсивный вызов для родителя (поля родительского класса будут выводиться первыми на экран)
            printFieldsRecursively(clazz.getSuperclass(), obj);

            // Вывод информации о полях текущего класса
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(field.getName() + ": " + field.get(obj));
            }
        }
    }

    /**
     * Метод для вызова метода makeSound, если такой существует.
     *
     * @param obj Объект, для которого необходимо вызвать метод makeSound.
     */
    public static void callMakeSound(Object obj) {
        Class<?> clazz = obj.getClass();
        boolean makeSoundMethodExists = false;

        // Проверка наличия метода makeSound() в классе
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals("makeSound")) {
                makeSoundMethodExists = true;
                break;
            }
        }

        if (makeSoundMethodExists) {
            try {
                Method makeSoundMethod = clazz.getMethod("makeSound");
                makeSoundMethod.invoke(obj);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                System.err.println("Возникло исключение в методе \"callMakeSound\"");
            }
        }
    }
}
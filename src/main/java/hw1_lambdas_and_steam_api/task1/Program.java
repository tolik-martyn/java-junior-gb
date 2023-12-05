package hw1_lambdas_and_steam_api.task1;

import java.util.Arrays;
import java.util.List;

/**
 * Программа для вычисления среднего значения четных чисел в списке, используя Stream API.
 */
public class Program {
    public static void main(String[] args) {
        // Пример использования метода averageValueEvenNumbers
        System.out.println(
                averageValueEvenNumbers(
                        Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    /**
     * Вычисляет среднее значение четных чисел в списке.
     *
     * @param numbers Список чисел.
     * @return Среднее значение четных чисел или 0.0, если список пуст.
     */
    public static double averageValueEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);
    }
}
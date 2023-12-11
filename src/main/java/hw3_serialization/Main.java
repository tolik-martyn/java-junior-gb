package hw3_serialization;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        String fileName = "src/main/resources/student.bin";

        Student student = new Student("Вася", 30, 4.2);
        System.out.println("Информация об объекте до сериализации:\n" + student);

        // Сериализуем объект в файл
        serializeStudent(student, fileName);

        // Десериализуем объект из файла
        Student deserializedStudent = deserializeStudent(fileName);

        // Выводим информацию о десериализованном объекте
        System.out.println("Информация об объекте после десериализации:\n" + deserializedStudent);
    }

    /**
     * Сериализация объекта в файл.
     */
    private static void serializeStudent(Student student, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(student);
            System.out.println("Объект сериализован в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Что-то пошло не так при сериализации объекта в файл " + fileName);
        }
    }

    /**
     * Десериализация объекта из файла.
     */
    private static Student deserializeStudent(String fileName) {
        Student student = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            student = (Student) ois.readObject();
            System.out.println("Объект десериализован из файла " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Что-то пошло не так при десериализации объекта из файла " + fileName);
        }
        return student;
    }
}
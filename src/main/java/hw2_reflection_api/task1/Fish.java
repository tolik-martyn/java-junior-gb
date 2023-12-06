package hw2_reflection_api.task1;

public class Fish extends Animal {
    double weight;

    public Fish(String name, int age, double weight) {
        super(name, age);
        this.weight = weight;
    }
}
package hw2_reflection_api.task1;

public class Dog extends Animal implements Sounding {
    private int speed;

    public Dog(String name, int age, int speed) {
        super(name, age);
        this.speed = speed;
    }

    @Override
    public void makeSound() {
        System.out.println("Собака по имени " + name + " гавкает");
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
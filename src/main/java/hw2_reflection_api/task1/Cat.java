package hw2_reflection_api.task1;

public class Cat extends Animal implements Sounding {
    private boolean isMongrel;

    public Cat(String name, int age, boolean isMongrel) {
        super(name, age);
        this.isMongrel = isMongrel;
    }

    @Override
    public void makeSound() {
        System.out.println("Кошка по имени " + name + " мяукает");
    }

    public boolean isMongrel() {
        return isMongrel;
    }

    public void setMongrel(boolean mongrel) {
        isMongrel = mongrel;
    }
}
package ru.job4j.generics;

public class Predator extends Animal {
    private double speed = 100;

    public Predator() {
    }

    public Predator(String name, int age, double speed) {
        super(name, age);
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Predator{"
                + "name='" + super.getName() + '\''
                + ", age=" + super.getAge()
                + ", speed=" + speed
                + '}';
    }
}

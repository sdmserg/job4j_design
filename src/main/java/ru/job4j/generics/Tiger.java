package ru.job4j.generics;

public class Tiger extends Predator {
    private String color = "striped orange and black";

    public Tiger() {
    }

    public Tiger(String name, int age, double speed, String color) {
        super(name, age, speed);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Tiger{"
                + "name='" + super.getName() + '\''
                + ", age=" + super.getAge()
                + ", speed=" + super.getSpeed()
                + "color='" + color + '\''
                + '}';
    }
}

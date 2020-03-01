package HomeWork_Lesson6;

import java.util.Random;

public abstract class Animal {
    private static final double DEVIATION = 0.25;
    private String name;
    private double runDistance;
    private double swimDistance;
    private double jumpHeight;
    private Random random;

    public Animal(String name, double runDistance, double swimDistance, double jumpHeight) {
        random = new Random();
        this.name = name;
        this.runDistance = getIndividualCharacteristic(runDistance);
        this.swimDistance = getIndividualCharacteristic(swimDistance);
        this.jumpHeight = getIndividualCharacteristic(jumpHeight);
    }

    private double getIndividualCharacteristic(double value) {
        return Math.max(value * (1 + DEVIATION * (1 - random.nextDouble() * 2)), 0);
    }

    protected void run(double distance) {
        System.out.printf("%s бежит %.1f м: %s", name, distance, runDistance >= distance ? "одолел\n" :
                String.format("не одолел, хватило сил на %.1f м\n", runDistance));
    }

    protected void swim(double distance) {
        System.out.printf("%s плывёт %.1f м: %s", name, distance, swimDistance >= distance ? "одолел\n" :
                String.format("не одолел, хватило сил на %.1f м\n", swimDistance));
    }

    protected void jump(double height) {
        System.out.printf("%s прыгает %.1f м: %s", name, height, jumpHeight >= height ? "одолел\n" :
                String.format("не одолел, хватило сил на %.1f м\n", jumpHeight));
    }

    public String getName() {
        return name;
    }
}

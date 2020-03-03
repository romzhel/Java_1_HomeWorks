package HomeWork_Lesson6;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = new Animal[]{
                new Cat("Барсик"),
                new Cat("Мурзик"),
                new Cat("Васька"),
                new Dog("Шарик"),
                new Dog("Бобик"),
                new Dog("Тузик")
        };

        Random random = new Random();
        for (Animal animal : animals) {
            animal.run(random.nextDouble() * 600);
            animal.swim(random.nextDouble() * 13);
            animal.jump(random.nextDouble() * 2.5);
        }
    }
}

package HomeWork_Lesson6;

public class Cat extends Animal {
    public Cat(String name) {
        super(name, 200, 0, 2);
    }

    @Override
    protected void swim(double distance) {
        System.out.printf("%s плывёт %.1f м: %s", super.getName(), distance, "успели откачать - коты не плавают )))\n");

    }
}

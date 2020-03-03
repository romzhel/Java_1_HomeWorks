package HomeWork_Lesson7;

import java.util.Random;

public class Main {
    private static final String SEPARATOR = "....................... %s .......................";
    private static Random random = new Random();

    public static void main(String[] args) {
        Cat[] cats = initCats(10, 50);
        Plate[] plates = initAndFillPlates(10, 50, 200);

        System.out.println(String.format(SEPARATOR, "Кормим котов"));
        for (Cat cat : cats) {
            cat.eat(plates);
        }
    }

    public static Cat[] initCats(int count, int maxCatAppetite) {
        System.out.println(String.format(SEPARATOR, "Заводим голодных котиков"));
        String[] names = {"Авалон", "Адажио", "Аполлон", "Арахис", "Бандит", "Борис", "Винсент", "Гомер", "Зорро", "Леон"};
        Cat[] cats = new Cat[count];

        for (int i = 0; i < count; i++) {
            String catName = i < names.length ? names[i] : names[i % names.length] + " (" + i / names.length + ")";
            cats[i] = new Cat(catName, random.nextInt(maxCatAppetite) + 1);
            cats[i].displayInfo();
        }

        return cats;
    }

    public static Plate[] initAndFillPlates(int count, int maxPlateSize, int foodForFilling) {
        System.out.println(String.format(SEPARATOR, "Наполняем тарелки имеющимся кормом"));
        Plate[] plates = new Plate[count];

        for (int i = 0; i < plates.length; i++) {
            plates[i] = new Plate(String.valueOf(i + 1), random.nextInt(maxPlateSize) + 1);
            foodForFilling -= plates[i].addFood(foodForFilling);

            plates[i].displayInfo();
        }

        return plates;
    }
}

package HomeWork_Lesson7;

public class Plate {
    private String name;
    private int maxFood;
    private int food;

    public Plate(String name, int maxFood) {
        this.name = name;
        this.maxFood = maxFood;
    }

    public int addFood(int addedFood) {
        int empty = maxFood - food;
        int usedFood = Math.min(addedFood, empty);
        food += usedFood;
        return usedFood;
    }

    public int provideFood(int neededFood) {
        int usedFood = Math.min(food, neededFood);
        food -= usedFood;
        return usedFood;
    }

    @Override
    public String toString() {
        return food > 0 ?
                String.format("Тарелка %s ёмкостью %d наполнена на %.0f %%", name, maxFood, (double) food / maxFood * 100) :
                String.format("Тарелка %s ёмкостью %d пуста", name, maxFood);
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    public boolean isEmpty() {
        return food == 0;
    }

    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }
}

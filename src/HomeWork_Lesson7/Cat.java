package HomeWork_Lesson7;

public class Cat {
    private String name;
    private int appetite;
    private int satiety;
    private boolean canEatFromSeveralPlates;

    public Cat(String name, int appetite, boolean canEatFromSeveralPlates) {
        this.name = name;
        this.appetite = appetite;
        this.canEatFromSeveralPlates = canEatFromSeveralPlates;
    }

    public void eat(Plate plate) {
        System.out.print(String.format("Кот %s подходит поесть из тарелки %s.... ", name, plate.getName()));
        int neededFood = appetite - satiety;
        if (canEatFromSeveralPlates || plate.getFood() > neededFood) {
            satiety += plate.provideFood(neededFood);
        } else {
            System.out.print(String.format("Не стал есть, мало еды, хотел %d, а было %d.... ", neededFood, plate.getFood()));
        }
    }

    public void eat(Plate[] plates) {
        int plateIndex = 0;

        while (!isSatisfied() && plateIndex < plates.length) {
            if (!plates[plateIndex].isEmpty()) {
                eat(plates[plateIndex]);
                displayInfo();
                plates[plateIndex].displayInfo();
            }
            plateIndex++;
        }
    }

    public boolean isSatisfied() {
        return satiety == appetite;
    }

    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return satiety > 0 ?
                String.format("Кот %s с аппетитом %d сыт на %.0f %%", name, appetite, (double) satiety / appetite * 100) :
                String.format("Кот %s с аппетитом %d абсолютно голоден", name, appetite);
    }

    public String getName() {
        return name;
    }
}

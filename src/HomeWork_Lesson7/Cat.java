package HomeWork_Lesson7;

public class Cat {
    private String name;
    private int appetite;
    private int satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        System.out.print(String.format("Кот %s ест из тарелки %s.... ", name, plate.getName()));
        satiety += plate.provideFood(appetite - satiety);
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

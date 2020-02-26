package HomeWork_Lesson5;

public class Main {

    public static void main(String[] args) {
        Employee[] employees = new Employee[]{
                new Employee("Petrov Petya", "Engineer", "25@mail.ru", "89262500000", 25000, 25),
                new Employee("Ivanov Vanya", "Engineer", "30@mail.ru", "89263000000", 30000, 30),
                new Employee("Sidorov Kolya", "Engineer", "35@mail.ru", "89263500000", 35000, 35),
                new Employee("Pupkin Sergey", "Engineer", "40@mail.ru", "89264000000", 40000, 40),
                new Employee("Smirnov Maxim", "Engineer", "45@mail.ru", "89264500000", 45000, 45)
        };

        for (Employee employee : employees) {
            if (employee.getAge() > 40) {
                System.out.println(employee.toString());
            }
        }
    }
}

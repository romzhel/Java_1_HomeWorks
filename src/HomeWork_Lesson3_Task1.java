import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 1. Программа, которая загадывает случайное число от 0 до 9, и пользователю дается 3
 * попытки угадать это число. При каждой попытке компьютер должен сообщить больше ли
 * указанное пользователем число, чем загаданное, или меньше. После победы или проигрыша
 * выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
 */

public class HomeWork_Lesson3_Task1 {

    public static void main(String[] args) {
        do {
            gameSession(9, 3);
        }
        while (getUserNumber("Повторить игру еще раз? Введите 1 – да / 0 – нет ", 1) == 1);

        System.out.println("\nРады были Вас видеть, приходите снова!!!");
    }

    static void gameSession(int maxNumber, int attemptsCount) {
        int secretNumber = new Random().nextInt(maxNumber);
        int[] arrUsedNumbers = new int[attemptsCount + 1];
        Arrays.fill(arrUsedNumbers, -1);
        int userNumber;

        System.out.printf("Угадайте число от 0 до %d с %d-х попыток", maxNumber, attemptsCount);

        int attempt;
        for (attempt = 1; attempt <= attemptsCount; attempt++) {
            do {
                userNumber = getUserNumber(String.format("\nПопытка %d из %d", attempt, attemptsCount), maxNumber);
            }
            while (isNumberUsed(userNumber, arrUsedNumbers));                   //проверяем, чтобы число не использовалось

            if (userNumber == secretNumber) {
                System.out.printf("Чтобы угадать число Вам потребовалось попыток: %d !\n\n", attempt);
                break;
            } else {
                System.out.println("Загаданное число " + (userNumber > secretNumber ? "меньше" : "больше"));
                arrUsedNumbers[attempt] = userNumber;
            }
        }

        if (attempt > attemptsCount) {
            System.out.printf("Было загадано число: %d\n\n", secretNumber);
        }
    }

    static int getUserNumber(String message, int highLimit) {
        Scanner sc = new Scanner(System.in);
        String filter = String.format("[0-%d]+", highLimit);
        String userInput;
        int userNumber = 0;

        do {
            System.out.print(message + " ");
            userInput = sc.next();

            if (userInput.matches(filter) && (userNumber = Integer.parseInt(userInput)) <= highLimit) {
                break;
            }
            System.out.println("Необходимо ввести число от 0 до " + highLimit);
        } while (true);

        return userNumber;
    }

    static boolean isNumberUsed(int number, int[] arr) {
        for (int i : arr)
            if (i == number) {
                System.out.printf("Число %d уже было, введите другое число\n", number);
                return true;
            }
        return false;
    }
}
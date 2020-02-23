import java.util.Random;
import java.util.Scanner;

/**
 * 2. Отгадывание слов
 */


public class HomeWork_Lesson3_Task2 {

    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        String secretWord = words[new Random().nextInt(words.length)];
        StringBuilder mask = new StringBuilder("###############");
        String userInput;

        while (!mask.toString().contains(secretWord)) {
            userInput = getUserInput(mask.toString());

            for (int charIndex = 0; charIndex < userInput.length() && charIndex < secretWord.length(); charIndex++) {
                if (userInput.charAt(charIndex) == secretWord.charAt(charIndex)) {
                    mask.setCharAt(charIndex, secretWord.charAt(charIndex));
                }
            }
        }

        System.out.printf("Поздравляем!!! Вы угадали слово %s\n", secretWord);
    }

    static String getUserInput(String message) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String filter = "[a-zA-Z]*";

        do {
            System.out.print(message + ", введите название овоща/фрукта/ягоды на английском\n");
            if ((userInput = sc.next()).matches(filter)) {
                break;
            }
            System.out.println("Необходимо ввести название на английском");
        } while (true);

        return userInput.toLowerCase();
    }
}

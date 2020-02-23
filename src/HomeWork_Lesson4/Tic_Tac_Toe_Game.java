package HomeWork_Lesson4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Игра Крестики-Нолики, Роман Желудков, март 2018
 */
public class Tic_Tac_Toe_Game {
    public static final char DOT_EMPTY = 183;               //значок свободного места
    public static final char DOT_X = 'X';                   //значок Крестик
    public static final char DOT_O = 'O';                   //значок Нолик
    public static char userDOT;                             //чем играет пользователь
    public static char pcDOT;                               //чем играет компьютер
    public static char[][] map;                             //двумерный массив поля
    public static int[][] mapAnalysis;                      //поле для расчётов ИИ
    public static Scanner scan = new Scanner(System.in);    //объект для ввода хода
    public static int mapSize;                              //размер игрового поля
    public static int lineLengthToWin = 3;                  //непрерывная последовательность для выигрыша

    public static void main(String[] args) {                //основной метод
        boolean isUserWin = false;                            //флаг выигрыша пользователя
        boolean isPcWin = false;                              //флаг выигрыша компьютера

        getUserDOT();

        mapSize = getDigitalValue("Введите размер игрового поля (от 3 до 9)", "[3-9]");
        if (mapSize > 3) {
            lineLengthToWin = getDigitalValue("Введите длину непрерывной комбинации для выигрыша (от 3 до " +
                    mapSize + ")", "[3-" + mapSize + "]");
        }

        initMap();                                          //иницианизируем игровое поля для отображения
        initCalcMap();                                      //иницианизируем поле расчётов ИИ
        printMap();                                         //выводим игровое поле в консоль

        while (true) {                                      //непрерывный цикл игры до победы или окончания ходов

            if (!isFreePosition()) {
                break;                                      //если нет ходов, то выходим из цикла
            }
            humanTurn();                                    //ход пользователя
            isUserWin = checkLinesWin(userDOT);             //проверяем выиграл ли пользователь и анализируем его ход
            printCalculatedMap("ИИ анализирует ход игрока");    //вывод поля расчётов ИИ в консоль

            if (!isFreePosition()) {
                break;                                      //если нет ходов, то выходим из цикла
            }
            checkLinesWin(pcDOT);                           //анализируем поле на выгодные шаги для компьютера
            printCalculatedMap("ИИ анализирует свой ход");       //вывод поля расчётов ИИ в консоль
            ioTurn();                                       //ход компьютера
            isPcWin = checkLinesWin(pcDOT);                 //проверяем выиграл ли компьютер

            printMap();                                     //выводим игровое поле в консоль
            clearCalcMap();                                 //сбрасываем поле расчётов ИИ

            if (isUserWin || isPcWin) break;                //если кто-то выиграл, то выходим из цикла
        }

        endTheGame(isUserWin, isPcWin);
    }

    /**
     * Метод запроса пользователя чем он будет играть Х или О
     */
    public static void getUserDOT() {
        String userInput;

        while (true) {
            System.out.print("Чем будете играть (введите X или O) ? ");
            userInput = scan.nextLine();

            if (userInput.matches("[оОoO]+")) {
                userDOT = DOT_O;
                pcDOT = DOT_X;
                break;
            } else if (userInput.matches("[хХxX]+")) {
                userDOT = DOT_X;
                pcDOT = DOT_O;
                break;
            }
        }
    }

    /**
     * Метод, выводящий сообщение и возвращащий цифровые значения, удовлетворяющие переданному регулярному выражению filter
     */
    public static int getDigitalValue(String message, String filter) {
        String userInput;

        while (true) {
            System.out.print(message + " ");
            userInput = scan.nextLine();

            if (userInput.matches(filter)) {
                return Integer.parseInt(userInput);
            }
        }
    }

    /**
     * Метод, инициализирующий игровое поле
     */
    public static void initMap() {                          //Инициализация поля, заполнение
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Метод, выводящий игровое поле в консоль
     */
    public static void printMap() {                         //Вывод поля в консоль
        System.out.print("\n  ");
        for (int i = 1; i <= mapSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < mapSize; i++) {
            System.out.print((i + 1) + " ");                //вывод в консоль номера строки
            for (int j = 0; j < mapSize; j++) {
                System.out.print(map[i][j] + " ");          //вывод в консоль значка
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Метод, получающий от пользователя ввод координат
     */
    public static void humanTurn() {
        int x;
        int y;
        String[] coordinates;

        while (true) {
            System.out.printf("Введите координаты в формате 'X Y' (два числа от 1 до %d через пробел) ", mapSize);
            coordinates = scan.nextLine().trim().split("\\s");

            if (coordinates.length > 1) {
                if (isInputValuesDigital(coordinates[0], coordinates[1])) {        //проверка, что введены цифры
                    x = Integer.parseInt(coordinates[0]);                          //координата X - int
                    y = Integer.parseInt(coordinates[1]);                          //координата Y - int

                    if (isCellValid(x, y)) {                                       //проверка ячейки
                        map[y - 1][x - 1] = userDOT;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Метод, возвращающий TRUE, если переданные значения цифровые
     */
    public static boolean isInputValuesDigital(String... input) {          //метод проверяющий что введены цифры
        boolean isAllDigits = true;

        for (String symbol : input) {
            isAllDigits &= symbol.matches("[\\d]+");
        }

        return isAllDigits;
    }

    /**
     * Метод, возвращающий TRUE, если переданные координаты свободны для хода
     */
    public static boolean isCellValid(int x, int y) {                               //метод проверяющий ячейку
        if (x < 1 || x > mapSize || y < 1 || y > mapSize) {                               //
            System.out.println("Значения не должны выходить за пределы поля (1..." + mapSize + ")");
            return false;
        } else if (map[y - 1][x - 1] != DOT_EMPTY) {
            System.out.println("Данная позиция уже занята, выберите другую");
            return false;
        }
        return true;
    }

    /**
     * Метод, инициализирующий поле расчётов ИИ
     */
    public static void initCalcMap() {
        mapAnalysis = new int[mapSize][mapSize];
    }

    /**
     * Метод, сбрасывающий значения поля расчётов ИИ
     */
    public static void clearCalcMap() {
        for (int i = 0; i < mapSize; i++) {
            Arrays.fill(mapAnalysis[i], 0);
        }
    }

    /**
     * Метод, выводящий в консоль поле расчётов ИИ
     */
    public static void printCalculatedMap(String comment) {                         //Вывод поля в консоль
        System.out.println("\n" + comment);
        System.out.print("   ");
        for (int j = 0; j < mapSize; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i <= mapSize; i++) {
            System.out.print("--");
        }
        System.out.println();
        for (int i = 0; i < mapSize; i++) {
            System.out.print((i + 1) + " |");                //вывод в консоль номера строки
            for (int j = 0; j < mapSize; j++) {
                System.out.print(map[i][j] != DOT_EMPTY ? "* " : mapAnalysis[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Метод расчёта ходов пользователя и оптимальных ходов компьютера по переданной линии поля (ИИ здесь)
     */
    public static void treatLine(String line, char dotType, int x, int y, String lineType) {      //
        char[] arrCharTemplate;
        String template;
        final int MIN_TEMPLATE_SIZE = 2;
        int correction1;
        int correction2;

        //рассматриваем только линии, имеющие достаточную длину и содержащие значок игрока
        if (line.length() >= lineLengthToWin && line.contains(Character.toString(dotType))) {

            for (int templateSize = MIN_TEMPLATE_SIZE; templateSize <= line.length(); templateSize++) {   //цикл наращивания длины шаблона
                //в зависимости от размера поля
                arrCharTemplate = new char[templateSize];                                               //создаём массив для шаблона
                for (int currTemplateSize = 0; currTemplateSize < templateSize; currTemplateSize++) {
                    Arrays.fill(arrCharTemplate, dotType);                                              //наполняем шаблон значками игрока
                    arrCharTemplate[currTemplateSize] = DOT_EMPTY;                                      //изменяем положение свободного места в шаблоне
                    template = String.valueOf(arrCharTemplate);                                         //преобразовываем шаблон в String

                    correction1 = 0;                                                //поправка для линий, в которой есть значки соперника
                    correction2 = 0;                                                //поправка для своих выигрышных комбинаций
                    int position = line.indexOf(template);                          //проверяем, содержится ли в линии шаблон значков
                    int emptyDotPosition = template.indexOf(DOT_EMPTY);             //положение значка свободного места в шаблоне
                    char apponentDOT = dotType == DOT_X ? DOT_O : DOT_X;            //определяем значок соперника
                    int apponentDotPosition = line.indexOf(apponentDOT);            //определяем есть значок соперника в линии

                    if (apponentDotPosition >= 0 && Math.abs(position + emptyDotPosition - apponentDotPosition) < lineLengthToWin)
                        correction1 = 4;                                            //если в линии есть значки соперника, то применяем поправку
//                        continue;
                    //если есть выигрышная позиция для компьютера, то выигрываем )))
                    if (dotType == pcDOT && currTemplateSize == lineLengthToWin - 1) correction2 = 50;

                    if (position >= 0) {                                            //линия содержит шаблон
                        switch (lineType) {                                         //выбираем изменяемую ячейку в зависимости от типа линии
                            case "horizontal":
                                mapAnalysis[y][x + x + position + emptyDotPosition] += templateSize * templateSize;

                                if (y == 0 || y == mapSize - 1)                   //занимаем углы - защита от вилки на маленьких полях (3х3)
                                    if ((line.indexOf(DOT_EMPTY) == 0 || line.indexOf(DOT_EMPTY) == mapSize - 1)) {
                                        mapAnalysis[y][0] += 5;
                                        mapAnalysis[y][mapSize - 1] += 5;
                                    }

                                break;
                            case "vertical":
                                mapAnalysis[y + position + emptyDotPosition][x] +=
                                        templateSize * templateSize - correction1 + correction2;
                                break;
                            case "diagonal1":
                                mapAnalysis[y + position + emptyDotPosition][x + position + emptyDotPosition] +=
                                        templateSize * templateSize - correction1 + correction2;
                                break;
                            case "diagonal2":
                                mapAnalysis[y - position - emptyDotPosition][x + position + emptyDotPosition] +=
                                        templateSize * templateSize - correction1 + correction2;
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Метод перебора горизонтальных линий поля и передачи их для анализа, возвращает TRUE в случае выигрыша переданного методу значка
     */
    public static boolean checkHorizontalLinesWins(char dotType, String winTemplate) {
        String strLine;
        for (int y = 0; y < mapSize; y++) {                       //проверяем все горизонтальные линии

            strLine = "";
            for (int j = 0; j < mapSize; j++) {
                strLine += map[y][j];                              //формируем строку поля в String
            }

            if (strLine.contains(winTemplate)) {
                return true;                                        //проверка на условие выигрыша
            }

            treatLine(strLine, dotType, 0, y, "horizontal"); //передача линии поля на анализ
        }
        return false;
    }

    /**
     * Метод перебора вертикальных линий поля и передачи их для анализа, возвращает TRUE в случае выигрыша переданного методу значка
     */
    public static boolean checkVerticalLinesWin(char dotType, String winTemplate) {
        String strLine;
        for (int x = 0; x < mapSize; x++) {                       //проверяем все вертикальные линии

            strLine = "";
            for (int j = 0; j < mapSize; j++) {
                strLine += map[j][x];                               //формируем строку поля в String
            }

            if (strLine.contains(winTemplate)) {
                return true;                                        //проверка на условие выигрыша
            }

            treatLine(strLine, dotType, x, 0, "vertical");//передача линии поля на анализ
        }
        return false;
    }

    /**
     * Метод перебора диагональных линий поля (слева вниз) и передачи их для анализа, возвращает TRUE в случае выигрыша переданного методу значка
     */
    public static boolean checkDiagonalLeftDownLinesWin(char dotType, String winTemplate) {
        String strLine;

        for (int x = 0; x < mapSize; x++) {//диагональ 1 (слева сверху вправо вниз), вдоль оси Х, Y=0
            strLine = "";
            for (int i = x, y = 0; i < mapSize && y < mapSize; i++, y++) {
                strLine += map[y][i];  //формируем строку поля в String
            }
            if (strLine.contains(winTemplate)) {
                return true;//проверка на условие выигрыша
            }
            treatLine(strLine, dotType, x, 0, "diagonal1");//передача линии поля на анализ
        }

        for (int y = 1; y < mapSize; y++) {//диагональ 1 (слева сверху вправо вниз) вдоль оси Y, X=0
            strLine = "";
            for (int x = 0, i = y; x < mapSize && i < mapSize; x++, i++) {
                strLine += map[i][x];//формируем строку поля в String
            }
            if (strLine.contains(winTemplate)) {
                return true;//проверка на условие выигрыша
            }
            treatLine(strLine, dotType, 0, y, "diagonal1");//передача линии поля на анализ
        }

        return false;
    }

    /**
     * Метод перебора диагональных линий поля (слева вверх) и передачи их для анализа, возвращает TRUE в случае выигрыша переданного методу значка
     */
    public static boolean checkDiagonalLeftUpLinesWin(char dotType, String winTemplate) {
        String strLine;

        for (int y = 0; y < mapSize; y++) { //диагональ 2 (слева снизу вправо вверх) по оси Y, X=0
            strLine = "";
            for (int x = 0, i = y; x < mapSize && i >= 0; x++, i--) {
                strLine += map[i][x];    //формируем строку поля в String
            }
            if (strLine.contains(winTemplate)) {
                return true;//проверка на условие выигрыша
            }
            treatLine(strLine, dotType, 0, y, "diagonal2");//передача линии поля на анализ
        }

        for (int x = 1; x < mapSize; x++) {//диагональ 2 (слева снизу вправо вверх) по оси X, Y=0
            strLine = "";
            for (int i = x, y = mapSize - 1; i < mapSize && y >= 0; i++, y--) {
                strLine += map[y][i];//формируем строку поля в String
            }
            if (strLine.contains(winTemplate)) {
                return true;//проверка на условие выигрыша
            }
            treatLine(strLine, dotType, x, mapSize - 1, "diagonal2");//передача линии поля на анализ
        }

        return false;
    }

    /**
     * Метод перебора всех линий поля для передачи их в метод treatLine для анализа, возвращает TRUE в случае выигрыша переданных методу значков
     */
    public static boolean checkLinesWin(char dotType) {
        String winTemplate = "";

        for (int winLine = 0; winLine < lineLengthToWin; winLine++) {
            winTemplate += String.valueOf(dotType);                         //формируем шаблон для проверки выигрыша
        }

        return checkHorizontalLinesWins(dotType, winTemplate) || checkVerticalLinesWin(dotType, winTemplate) ||
                checkDiagonalLeftDownLinesWin(dotType, winTemplate) || checkDiagonalLeftUpLinesWin(dotType, winTemplate);
    }

    /**
     * Метод, осуществляющий ход компьютера (ИИ) на основе расчётного поля
     */
    public static void ioTurn() {
        int maxX = 0;
        int maxY = 0;
        int maxValue = 0;

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if (mapAnalysis[y][x] > maxValue) {
                    maxValue = mapAnalysis[y][x];       //находим максимальное значение поля
                    maxX = x;                           //и его координаты
                    maxY = y;
                }
            }
        }

        map[maxY][maxX] = pcDOT;                        //помещаем значок хода компьютера
    }

    /**
     * Метод, возвращающий TRUE если на игровом поле ещё остались ходы
     */
    public static boolean isFreePosition() {
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if (map[y][x] == DOT_EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод, завершающий игру
     */
    public static void endTheGame(boolean isUserWin, boolean isPcWin) {
        if (isUserWin && isPcWin || !isUserWin && !isPcWin) {       //если оба победили одновременно или нет ходов - ничья
            System.out.println("\nНичья!!!");
        } else if (isUserWin) {                                     //победил пользователь
            System.out.println("\nВы победили!!!");
        } else {                                                    //победил компьютер
            System.out.println("\nНичего, в следующий раз повезёт!");
        }

        System.out.println("\nКонец игры");
        scan.close();
    }
}
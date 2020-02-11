import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //1. Задать целочисленный массив, состоящий из элементов 0 и 1. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        System.out.println("------1. Задаём целочисленный массив и заменяем его значения------------------------------");
        int[] arr = {0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0};
        System.out.println("Начальный массив:  " + Arrays.toString(arr));  //выводим в консоль для проверки
        //вариант 1 по заданию - с условием
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] == 0) {
//                arr[i] = 1;
//            } else {
//                arr[i] = 0;
//            }
//        }
        //вариант 2 - альтернатива без условия
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 - arr[i];
        }
        System.out.println("Изменённый массив: " + Arrays.toString(arr));  //выводим в консоль для проверки

        //2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его
        //значениями 0 3 6 9 12 15 18 21;
        System.out.println("------2. Задаём пустой целочисленный массив и заполняем его значения----------------------");
        final int ARRAY_SIZE = 8;
        int[] arr2 = new int[ARRAY_SIZE];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i * 3;
        }
        System.out.println(Arrays.toString(arr2));  //выводим в консоль для проверки

        //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6
        //умножить на 2
        System.out.println("------3. Задаём целочисленный массив и числа, меньшие 6, умножаем на 2--------------------");
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Начальный массив:  " + Arrays.toString(arr3));  //выводим в консоль для проверки
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) {
                arr3[i] = arr3[i] * 2;
            }
        }
        System.out.println("Изменённый массив: " + Arrays.toString(arr3));  //выводим в консоль для проверки

        //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов
        //одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        System.out.println("------4. Задаём квадратный двумерный массив и заполняем диагонали-------------------------");
        int size = 9;                   // размер массива
        int[][] arr4 = new int[size][size];
        for (int i = 0; i < size; i++) {
            arr4[i][i] = 1;             //диагональ слева направо
            arr4[i][size - i - 1] = 1;  //диагональ справа налево
        }
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(arr4[i]));  //выводим в консоль для проверки
        }

        //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без
        //помощи интернета);
        System.out.println("------5. Задаём одномерный массив и находим в нём мин и макс элементы---------------------");
        int[] arr5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int arr5Min;
        int arr5Max;
        arr5Max = arr5Min = arr5[0];
        System.out.println("Массив:  " + Arrays.toString(arr5));    //выводим массив в консоль
        for (int i = 1; i < arr5.length; i++) {
            if (arr5[i] < arr5Min) {
                arr5Min = arr5[i];
            } else if (arr5[i] > arr5Max) {
                arr5Max = arr5[i];
            }
        }
        System.out.println("Мин. элемент массива = " + arr5Min + ", макс. элемент массива = " + arr5Max); //выводим в консоль для проверки

        //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
        //метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части
        //массива равны.
        System.out.println("------6. Определяем равновесное место в массиве------------------------------------------");
        System.out.println(isArraySidesEqual(new int[]{1, 2, 3, 7}));//выводим в консоль для проверки
        System.out.println(isArraySidesEqual(new int[]{6, 1, 2, 3}));//выводим в консоль для проверки
        System.out.println(isArraySidesEqual(new int[]{1, 1, 1, 2, 1}));//выводим в консоль для проверки
        System.out.println(isArraySidesEqual(new int[]{2, 1, 1, 2, 1}));//выводим в консоль для проверки
        System.out.println(isArraySidesEqual(new int[]{10, 10}));//выводим в консоль для проверки
        System.out.println(isArraySidesEqual(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10}));//выводим в консоль для проверки
        System.out.println(isArraySidesEqual(new int[]{1}));//выводим в консоль для проверки

        //7. Написать метод, которому на вход подаётся одномерный массив и число n (может быть
        //положительным, или отрицательным), при этом метод должен сместить все элементы
        //массива на n позиций.
        System.out.println("------7. Смещаем элементы массива--------------------------------------------------------");
        int[] arr70 = {1, 2, 3, 4, 5, 6};
        System.out.println("Начальный массив:   " + Arrays.toString(arr70));  //выводим в консоль для проверки

        int[] tempArr;                                                        //демонстрация работы
        for (int i = 1; i > -2; i -= 2) {
            for (int k = 0; k < 12; k++) {
                tempArr = Arrays.copyOf(arr70, arr70.length);
                deposeArray(tempArr, k * i);
            }
        }
    }

    //процедура нахождения места равносесия левой и правой сторон массива-----------------------------------------------
    static boolean isArraySidesEqual(int[] arr) {
        if (arr.length < 2) {                                           //должно быть минимум 2 элемента массива
            System.out.print("Массив " + Arrays.toString(arr) + " маловат --> ");
            return false;
        }

        int arrLeftSideSum = 0;
        int arrRightSideSum = 0;                                        //сумма левой и правой частей массива
        int arrLeftPosition = 0;
        int arrRightPosition = 0;                                       //текущие элементы левой и правой частей массива

        while (arrLeftPosition + arrRightPosition < arr.length) {       //вычисляем сумму частей массива
            if (arrLeftSideSum > arrRightSideSum) {
                arrRightSideSum += arr[arr.length - 1 - arrRightPosition++];
            } else {
                arrLeftSideSum += arr[arrLeftPosition++];
            }
        }

        //визуализируем место равновесия--------------------------------------------------------------------------------
        System.out.print("[");
        for (int i = 0; i < arrLeftPosition - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arrLeftPosition - 1] + (arrLeftSideSum == arrRightSideSum ? " || " : " ** "));
        for (int i = arrLeftPosition; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1] + "] --> ");
        //--------------------------------------------------------------------------------------------------------------

        return arrLeftSideSum == arrRightSideSum;                           //возвращаем результат
    }

    //метод смещающий элементы массива----------------------------------------------------------------------------------
    static int[] deposeArray(int[] arr, int offset) {
        int rawOffset = offset;                                             //исх. смещение для отображения в консоли
//
//        if (offset < 0)
//            offset = arr.length + offset % arr.length;                    //преобр. отриц. смещение (кольцевая структура массива при смещении)
//        offset %= arr.length;                                             //приводим смещение к размеру массива, целесообр. если offset > arr.length - 1

        offset = (arr.length + offset % arr.length) % arr.length;           //вычисляем соотношение смещения и массива

        if (offset != 0) {                                                  //если смещение = 0, то массив не меняется
            int buffer;
            int offsetValue;
            int nextPosition = 0;
            int cyclePosition = 0;                                          //cyclePosition - положение элемента, на
            //котором смещение может зациклиться

            offsetValue = arr[0];                                           //начинаем с 0-го элемента
            for (int i = 0; i < arr.length; i++) {                          //перебираем все элементы массива
                nextPosition = (nextPosition + offset) % arr.length;        //положение следующего заменяемого элемента

                if (nextPosition == cyclePosition && i < arr.length - 1) {  //*предотвращаем зациклинивание по элементам массива
                    arr[nextPosition] = offsetValue;                        //например, массив [1,2,3,4] и смещение 2 приведёт к изменению
                    nextPosition = ++cyclePosition;                         //только 0 и 2 элементов массива, т.е. 1 и 3
                    offsetValue = arr[nextPosition];
                } else {                                                    //*изменение цепочкой
                    buffer = arr[nextPosition];                             //запоминаем изменяемый элемент (буфер)
                    arr[nextPosition] = offsetValue;                        //меняем на значение смещаемого элемента
                    offsetValue = buffer;                                   //передаём значение для записи в след. элемент
                }

//            System.out.println("Пошагово:  " + Arrays.toString(arr) + ", смещение = " + rawOffset);  //выводим в консоль для проверки
            }
        }

        System.out.println("Изменённый массив:  " + Arrays.toString(arr) + ", смещение = " + rawOffset + ", факт. смещение = " + offset);  //выводим в консоль для проверки

        return arr;
    }
}

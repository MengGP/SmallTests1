package com.menggp.citecktets.task3;

/*
    Задача:
        Написать алгоритм, как в целом числе самый правый ноль превратить в единицу не используя циклы и рекурсию.

    План реализациия:
         1. Преобразуем число в строку
         2. Находим индекс крайнего правого 0
         3. Заменяем символ по полученному индексу на 1
         4. Преобразуем строку обратно в число

 */
public class RightZeroIncrement {

    private static final int START_NUM_1=20480961;  // общий случай
    private static final int START_NUM_2=248961;    // без 0
    private static final int START_NUM_3=24809610;  // 0 в начале

    /*
        Метод первращает самый правый ноль в целом числе в единицу
     */
    private static int rightZeroInc(int num) {
        // Преобразуем в строку
        String str = String.valueOf( num );

        // Находим индекс крайнего правого 0
        int rightZeroInd = str.lastIndexOf('0');

        // Меняем 0 на 1 в строке по указанному индексу
        //      индекс должен быть > 0, т.к. 0 в числе не может стоять на первой позиции, -1 если 0 не найден
        if ( rightZeroInd>0 )
            str = str.substring(0, rightZeroInd) + "1" + str.substring(rightZeroInd+1);
        else
            return num;

        // Преобразуем полученную строку в число
        return Integer.parseInt(str);
    }

    /*
        Точка входа
     */
    public static void main(String[] args) {

        // Преобразование для числа #1
        System.out.println("Число #1: ");
        System.out.println("\t" + START_NUM_1 );
        System.out.println("\t" + rightZeroInc(START_NUM_1) );

        // Преобразование для числа #2
        System.out.println("Число #2: ");
        System.out.println("\t" + START_NUM_2 );
        System.out.println("\t" + rightZeroInc(START_NUM_2) );

        // Преобразование для числа #3
        System.out.println("Число #3: ");
        System.out.println("\t" + START_NUM_3 );
        System.out.println("\t" + rightZeroInc(START_NUM_3) );


    }

}

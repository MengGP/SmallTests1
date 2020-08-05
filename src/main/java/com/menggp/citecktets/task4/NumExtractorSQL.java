package com.menggp.citecktets.task4;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/*
    Задание:
        Имеется таблица с 1 полем, заполненная числами по порядку: {1,2,4,7,8,11..}.
        Написать SQL Запрос который делает выборку следующего вида (2 столбца): {{3,1},{5,2},...},
        Т.е. в первом поле идет число, с которого начинается пропуск, во втором количество пропущенных чисел

    План решения:
        1. Подготовка начальных данных:
            - Инициализируем БД - используем БД "h2" в режиме in-memory
            - Зписываем в БД данные - значения от 1
        2. Извлечение данных из БД
            - извлекаем 2 списка:
                1. числа - после которых начинается пропуск
                2. числа - которыми заканчивается пропуск
            - на их основе получем зачения чисел:
                * число с которого начинается пропуск
                * количество пропущенных чисел

 */
public class NumExtractorSQL {

    public static final int SIZE = 10;
    public static final int MIN = 1;
    public static final int MAX = 100;


    /*
        Метод записывает в таблицу БД начальные данные
     */
    private static void initStartData(DateBaseHelper dbHelper, int size, int min, int max) throws SQLException {
        Set<Integer> set = new TreeSet<>();

        // Минимальное значение не может быть меньше 1
        min = min>0 ?min : 1;
        // Генерируем данные в TreeSet<>
        for (int i=0; i<size; i++)
            set.add( getIntRnd(min, max) );



        // Переносим данные в БД - в TreeSet<> данные упорядочены и исключены повторы
        for (int num : set )
            dbHelper.create( num );
    }

    /*
        Метод генерирует случайное целое число в заданном диапазоне
     */
    private static int getIntRnd(int min, int max) {
        return min + (int)Math.round( Math.random()*(max-min) );
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Инициализация и подключение к БД
        DateBaseHelper dbHelper = new DateBaseHelper();
        // Заполнение БД данными
        initStartData(dbHelper, SIZE, MIN, MAX);


        List<Integer> numList = dbHelper.readAll();
        System.out.print("[ ");
        for (int num : numList )
            System.out.print( num + "  ");
        System.out.println("]");

        List<MissNum> missList = dbHelper.readMissingNum();
        for (MissNum missNum : missList) System.out.println(missNum);

        // Закрываем подключение к БД
        dbHelper.close();
    }

}

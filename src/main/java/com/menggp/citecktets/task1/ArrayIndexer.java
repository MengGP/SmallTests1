package com.menggp.citecktets.task1;

import java.util.*;

/*
    Задание:
        Имеется массив чисел,
        получить список вида {число, количество вхождений числа в массив},
        список должен быть отсортирован по количеству вхождений, внутри по возрастания числа.

    План реализации:
        1. Подготовка начальных данных
            генерируем массиы целых чисел заданного размера, в заданном диапазоне чисел, параметры в константах
                SIZE    - размер массива
                MIN     - минимальное значение элемента
                MAX     - максимальное значение элемента
        2. Индексация массива - т.е. подосчет вхождений каждого элемента в массив
            в итоге получаем список List<> элементов  тип {ключ, значение}, где
                    ключ - элемент массива (исходного)
                    значение - количество вхождений элемента
             для индексации будем использовать структуру HashMap<>
        3. Сортировка
                отсортируем полученный список:
                    - по количеству вхождений - по убыванию
                    - для равных значений количества, по значению числа - по возрастанию
                определим для элементов списка {ключ, значение} компаратор реализующий условие сравнения
 */
public class ArrayIndexer {

    private final static int SIZE = 1000;
    private final static int MIN = 0;
    private final static int MAX = 100;


    // Начальный массив чисел
    private static List<Integer> sourceArray;
    // Конечный список элементов
    private static List<IndexEntry> indexArray;

    /*
        Метод возвращает массив случайных чисел
            параметры:
                - размер массива
                - минимальное значение числа
                - максимальное значение числа
    */
    private static List<Integer> initSourceArray(int size, int min, int max) {
        List<Integer> array = new ArrayList<>();
        for (int i=0; i<size; i++)
            array.add( getIntRnd(min, max) ) ;
        return array;
    }

    /*
        Метод генерирует случайное целое число в заданном диапазоне
     */
    private static int getIntRnd(int min, int max) {
        return min + (int)Math.round( Math.random()*(max-min) );
    }

    /*
        Метод возвращаем структуру вида: {число - количество вхождений}, для заданного массива
     */
    private static Map<Integer, Integer> arrayIndex(List<Integer> array) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int element: array) {
            if ( !indexMap.containsKey(element) )
                indexMap.put(element, 1);
            else
                indexMap.put(element, indexMap.get(element)+1) ;
        }

        return indexMap;
    }

    /*
        Метод возвращает список List<> элементов  тип {ключ, значение}, где
            ключ - элемент массива (исходного)
            значение - количество вхождений элемента
     */
    private static List<IndexEntry> indexArray(List<Integer> array) {
        List<IndexEntry> resultArray = new ArrayList<>();

        // получем из входного массива карту проиндексированных значений
        Map<Integer, Integer> indexMap = arrayIndex( array );

        // Преобразуем карту в список объектов типа IndexEntry
        for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
            resultArray.add( new IndexEntry( entry.getKey(), entry.getValue() ) );
        }

        return resultArray;
    }

    /*
        Точка входа
     */
    public static void main(String[] args) {

        // Генерация исходных данных
        sourceArray = initSourceArray(SIZE, MIN, MAX);

        // Индексация массива
        indexArray = indexArray( sourceArray );

        // Сортировка массива индексов
        indexArray.sort(IndexEntry.indexEntryComparator);

        // Печатаем результат
        for (IndexEntry entry : indexArray) {
            System.out.println(entry.toString());
        }

    }


}





package com.menggp.citecktets.task2;

import java.util.Stack;

/*
    Задание
        написать алгоритм проверки корректности регулярного выражения, которое включает [,],(,)
        т.е., например ([][[]()]) - правильно, ([][]()]) – неправильно

    План реализации:
        1. Уточнение условия:
            - в качестве условия корректности выражения примем ТОЛЬКО условие согласованности в нем скобок,
                прочие условия НЕ проверяем (# недопустимые символы, несогласованные операторы и.д.)
            - положим что могут встречаться ТОЛЬКО следующие виды скобок: (), [], {}, <>
        2. Входные данные: зададим выражения в константах:
                REG_EXP_SAMPLE_1
                REG_EXP_SAMPLE_2
                REG_EXP_SAMPLE_3
        3. Разбор выражения и анализ согласованости скобок:
            - посимвольно читаем строку с выражением
            - в процессе разбора строки проверяем согласованность скобок с помошью стека:
                * если встречаем открывающую скобку - ложим в стек
                * если встречаем закрывающую скобку - проверяем вершину стека:
                    если скобка того же типа - извлекаем вершину
                    иначе - выражение не верно
              возможны 2 варианты несогласованности:
                * открывающая скобка без закрывающей - по окончанию разбора строки стек не пуст
                * несогласованная закрвающая скобка - при разборке строки найдена несогласованная (с вершиной стека) скобка
 */
public class RegExpChecker {


    private final static String REG_EXP_SAMPLE_1 = ",(,[,],+[,[2,],3(,,%),&],)";
    private final static String REG_EXP_SAMPLE_2 = "(<[]>[<>]()[])}";
    private final static String REG_EXP_SAMPLE_3 = "([]1[z](b*)t%{er^2{])";

    /*
        Метод проверяет корректность регулярного выражения (согласованность скобок)
     */
    private static boolean  checkRegExp(String exp) {
        // Преобразуем выражение в массив символов
        char[] expChArray = exp.toCharArray();

        Stack<Character> expStack = new Stack<>();

          // Разбор массива
        for (char expCh : expChArray) {
            if ( expCh=='(' || expCh=='[' || expCh=='{' || expCh=='<' ) {
                 // Если открывающая скобка - ложим в стек
                 expStack.push(expCh);
            } else if (expCh==')' || expCh==']' || expCh=='}' || expCh=='>') {
                // Если закрывающая скобка - обрабатываем
                //  если скобка совпадает по типу с вершиной стека - извлекаем вершину
                //  иначе - возвращаем FALSE - выражение не согласуется
                //  если стек пуст - также возвращаем FALSE - несогласованная закрывающая скобка
                if ( expStack.isEmpty()) return false;
                else if ( expCh == ')' && expStack.peek() == '(' ) expStack.pop();
                else if ( expCh == ']' && expStack.peek() == '[' ) expStack.pop();
                else if ( expCh == '}' && expStack.peek() == '{' ) expStack.pop();
                else if ( expCh == '>' && expStack.peek() == '<' ) expStack.pop();
                else return false;
            }
        }
        // Если стек пуст - возвращаем TRUE, иначе есть несогласованная открывающая скобка - FALSE
        return expStack.isEmpty();
    }


    /*
        Точка входа
     */
    public static void main(String[] args) {

        // Проверка выражения 1
        if ( checkRegExp(REG_EXP_SAMPLE_1) ) System.out.println("Выражение: " + REG_EXP_SAMPLE_1 + " - корректно" );
        else  System.out.println("Выражение: " + REG_EXP_SAMPLE_1 + " - имеет ошибки" );

        // Проверка выражения 2
        if ( checkRegExp(REG_EXP_SAMPLE_2) ) System.out.println("Выражение: " + REG_EXP_SAMPLE_2 + " - корректно" );
        else  System.out.println("Выражение: " + REG_EXP_SAMPLE_2 + " - имеет ошибки" );

        // Проверка выражения 3
        if ( checkRegExp(REG_EXP_SAMPLE_3) ) System.out.println("Выражение: " + REG_EXP_SAMPLE_3 + " - корректно" );
        else  System.out.println("Выражение: " + REG_EXP_SAMPLE_3 + " - имеет ошибки" );

    }


}

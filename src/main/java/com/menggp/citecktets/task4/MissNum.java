package com.menggp.citecktets.task4;

import com.menggp.citecktets.task1.IndexEntry;

import java.util.Comparator;

/*
    Класс для хранения пропущенных числе и интервалов пропуска
        ключ - первое пропущенное число
        значение - количество пропущенных чисел до следующег в существующей записи
 */
public class MissNum {
    private int key;
    private int value;

    public MissNum(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key + " : " + this.value;
    }
}

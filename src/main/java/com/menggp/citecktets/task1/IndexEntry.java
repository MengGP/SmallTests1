package com.menggp.citecktets.task1;

import java.util.Comparator;

public class IndexEntry {
    private int key;
    private int value;

    // Компаратор - для сравнения элементов типа IndexEntry
    public static Comparator<IndexEntry> indexEntryComparator = new Comparator<IndexEntry>() {
        @Override
        public int compare(IndexEntry entry1, IndexEntry entry2) {
            // Сравниваем значения (количество вхождений) - по убыванию
            int valRes = entry2.getValue().compareTo( entry1.getValue() );
            if ( valRes > 0 ) return 1;
            else if ( valRes < 0 ) return -1;

            // Сравниваем ключи (значения элементов) - если значения совпали - по возрастанию
            int keyRes = entry1.getKey().compareTo( entry2.getKey() );
            if ( keyRes > 0 ) return 1;
            else if ( keyRes < 0 ) return -1;

            return 0;
        }
    };

    public IndexEntry(Integer key, Integer value) {
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

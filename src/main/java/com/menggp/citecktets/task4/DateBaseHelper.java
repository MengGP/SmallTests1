package com.menggp.citecktets.task4;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/*
    Класс отвечает логику работы с БД
 */
public class DateBaseHelper {

    private static final String DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:dbSample";

    // Таблица и поля
    private static final String TABLE = "numtab";
    private static final String ID = "id";
    private static final String NUM = "num";


    private static Connection connection;

    /*
        Конструктор - инициализирует БД
     */
    public DateBaseHelper() throws ClassNotFoundException, SQLException {
        // Подключение драйвера
        Class.forName(DRIVER);
        // создание подключения к БД
        connection = DriverManager.getConnection(DB_URL);

        // Создаем в БД таблицу
        createTable();
    }

    /*
        Метод создает таблицу в БД
     */
    private static void createTable() throws SQLException {
        Statement state = connection.createStatement();
        String query = "CREATE TABLE " + TABLE  +
                "(" +
                    ID + " BIGINT AUTO_INCREMENT, " +
                    NUM + " INTEGER NOT NULL" +
                ")";

        // исполнение запроса
        state.execute(query);
    }

    public void close() throws SQLException {
        connection.close();
    }

    /*
        Метод создания записи в БД - Create (Crud)
    */
    public void create(int num) throws SQLException {
        Statement state = connection.createStatement();
        String query = "INSERT INTO " + TABLE +
                    "("+NUM+") " +
                    "VALUES ('"+num+"')";
        state.executeUpdate(query);
    }

    /*
        Метод чтения всех записей из БД - Read (cRud)
     */
    public List<Integer> readAll() throws SQLException {
        List<Integer> resultList = new ArrayList<>();

        Statement state = connection.createStatement();
        String query = "SELECT * FROM " + TABLE ;
        ResultSet rs = state.executeQuery(query);

        while ( rs.next() ) {
            resultList.add( rs.getInt(NUM));
        }

        return resultList;
    }

    /*
        Метод читает записи из БД со следующей логикой:
            - данные таблицы: поле с отсортированныем по возрастанию целыми числами
            - результат: список {ключ, значение}, где
                ключ - пропущенное число из множества натуральных чисел
                значение - количество прощенных чисел до следующего в имеющейся записи
     */
    public List<MissNum> readMissingNum() throws SQLException {
        List<MissNum> resultList = new ArrayList<>();

        Statement state = connection.createStatement();

        // числа, после которых начитается пропуск
        String query1 = "SELECT " + NUM + " FROM " + TABLE +
                    " WHERE (" + NUM + "+1) NOT IN " +
                        "(SELECT "+NUM+" FROM "+TABLE+" ORDER BY "+NUM+")";
        ResultSet rs1 = state.executeQuery(query1);
        List<Integer> listRs1 = new ArrayList<>();
        while ( rs1.next() ) {
            listRs1.add( rs1.getInt(NUM));
        }

        // последние числа в пропущенном диапазоне
        String query2 = "SELECT (" + NUM + "-1) as "+NUM+" FROM " + TABLE +
                " WHERE (" + NUM + "-1) NOT IN " +
                "(SELECT "+NUM+" FROM "+TABLE+" ORDER BY "+NUM+" DESC)";
        ResultSet rs2 = state.executeQuery(query2);
        List<Integer> listRs2 = new ArrayList<>();
        while ( rs2.next() ) {
            listRs2.add( rs2.getInt(NUM));
        }

        int skipStart = 0;
        int skipCounter = 0;
        int indexRs1 = 0;
        int indexRs2 = 0;

        // Обрабатываем первое число отдельно, в зависимости от наличия 1 в начале
        if ( listRs2.get(0)==0) {
            // Единица есть
            skipStart = listRs1.get(0) + 1;
            skipCounter = listRs2.get(1) - skipStart+1;
            resultList.add( new MissNum( skipStart, skipCounter ));
            indexRs1++;
            indexRs2 += 2;
        } else {
            // Единицы нет
            skipStart = 1;
            skipCounter = listRs2.get(0) - skipStart+1;
            resultList.add( new MissNum( skipStart, skipCounter ));
            indexRs2++;
        }

        for (int i=indexRs2; i<listRs2.size(); i++) {
            skipStart = listRs1.get(indexRs1) + 1;
            skipCounter = listRs2.get(indexRs2) - skipStart+1;
            resultList.add( new MissNum( skipStart, skipCounter ));
            indexRs1++;
            indexRs2++;
        }

        return resultList;
    }

}

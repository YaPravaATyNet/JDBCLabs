package com.company;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        System.out.println("======================MySQL=========================");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow", "root", "password")) {
            print(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("======================H2=========================");
        try (Connection conn1 = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/store", "sa", "")) {
            print(conn1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(Connection conn) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        System.out.println(dbmd.getDriverName());
        System.out.println(dbmd.getUserName());
        ItemDAO dao = new ItemDAO(conn);
        System.out.println(dao.searchById(1));
        System.out.println(dao.searchById(2));
        System.out.println(dao.searchByKeyWord("Get"));
        System.out.println(dao.searchByKeyWord("All"));
        System.out.println(dao.create(new MusicItem(4, "Drive My Car", "The Beatles")));
    }
}

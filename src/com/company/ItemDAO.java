package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public Connection conn;

    public ItemDAO(Connection conn) {
        this.conn = conn;
    }

    public MusicItem searchById(int id) {
        String sql = "SELECT * FROM items WHERE item_id = " + id;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return new MusicItem(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MusicItem> searchByKeyWord(String key) {
        ArrayList<MusicItem> result = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE title LIKE '%" + key + "%'";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                result.add(new MusicItem(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public int create(MusicItem item) {
        String sql = "INSERT INTO items VALUES(" + item.getId() + ", '" + item.getName() + "', '" + item.getAuthor() + "');";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            return stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}

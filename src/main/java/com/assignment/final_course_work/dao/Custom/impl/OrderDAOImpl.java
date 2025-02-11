package com.assignment.final_course_work.dao.Custom.impl;

import com.assignment.final_course_work.dao.Custom.OrderDAO;
import com.assignment.final_course_work.dao.SQLUtil;
import com.assignment.final_course_work.entity.Order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIDIndex = i + 1;
            return String.format("O%03d", newIDIndex);
        }
        return "O001";
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getID(String name) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public Order getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
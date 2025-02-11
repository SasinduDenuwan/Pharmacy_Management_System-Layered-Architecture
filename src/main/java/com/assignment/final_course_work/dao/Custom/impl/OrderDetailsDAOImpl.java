package com.assignment.final_course_work.dao.Custom.impl;

import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.Custom.OrderDetailsDAO;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    MedicineDAO medicineDAO = new MedicineDAOImpl();
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
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
    public OrderDetail getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetail> orderDetailDTOs, Connection connection) throws SQLException, ClassNotFoundException {

        for(OrderDetail orderDetail : orderDetailDTOs) {

            if(!(saveOrderDetailsAndMore(orderDetail, connection))){
                return false;
            }
        }
        return true;
    }

    boolean saveOrderDetailsAndMore(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO order_detail VALUES (?,?,?,?,?,?)");

        preparedStatement.setString(1, orderDetail.getOrderID());
        preparedStatement.setString(2, orderDetail.getMedicineId());
        preparedStatement.setInt(3, orderDetail.getMedicineQty());
        preparedStatement.setInt(4, orderDetail.getPackageQty());
        preparedStatement.setDouble(5, orderDetail.getMedicinePrice());
        preparedStatement.setDouble(6, orderDetail.getPackagePrice());

        if (preparedStatement.executeUpdate() > 0){

            if (medicineDAO.reduceMedicine(orderDetail, connection)){
                System.out.println("Medicine reduce successful");
                return true;
            } else {
                System.out.println("Medicine reduce unsuccessful");
                return false;
            }

        } else{
            System.out.println("Failed to insert order details.");
            return false;
        }
    }
}
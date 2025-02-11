package com.assignment.final_course_work.bo;

import com.assignment.final_course_work.dto.OrderDTO;
import com.assignment.final_course_work.dto.OrderDetailDTO;
import com.assignment.final_course_work.entity.Order;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDTO order) throws SQLException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException;
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String getID(String name) throws SQLException, ClassNotFoundException;
    OrderDTO getDetails(String id) throws SQLException, ClassNotFoundException;
}

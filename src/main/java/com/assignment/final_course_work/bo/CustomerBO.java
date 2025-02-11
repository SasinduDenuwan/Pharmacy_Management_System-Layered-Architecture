package com.assignment.final_course_work.bo;

import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    String generateNewID() throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String getID(String name) throws SQLException, ClassNotFoundException;
    CustomerDTO getDetails(String id) throws SQLException, ClassNotFoundException;
}

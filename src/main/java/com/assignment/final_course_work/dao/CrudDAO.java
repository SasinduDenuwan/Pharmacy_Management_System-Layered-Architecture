package com.assignment.final_course_work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(T dto) throws SQLException, ClassNotFoundException;
    public boolean update(T dto) throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String getID(String name) throws SQLException, ClassNotFoundException;
    T getDetails(String id) throws SQLException, ClassNotFoundException;


}
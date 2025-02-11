package com.assignment.final_course_work.bo;
import com.assignment.final_course_work.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException;
//    public boolean save(UserDTO dto) ;
    boolean save(UserDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException;
    public UserDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String getID(String name) throws SQLException, ClassNotFoundException;
    UserDTO getDetails(String id) throws SQLException, ClassNotFoundException;
    public boolean chooseThePage() throws SQLException, ClassNotFoundException;
    public boolean checkUserAndPW(String userName, String pw) throws SQLException, ClassNotFoundException;


}
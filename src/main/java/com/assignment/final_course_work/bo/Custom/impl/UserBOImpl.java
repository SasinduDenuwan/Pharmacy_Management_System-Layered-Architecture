package com.assignment.final_course_work.bo.Custom.impl;
import com.assignment.final_course_work.bo.UserBO;
import com.assignment.final_course_work.dao.Custom.UserDAO;
import com.assignment.final_course_work.dao.DAOFactory;
import com.assignment.final_course_work.dto.UserDTO;
import com.assignment.final_course_work.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewID();
    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserID(), dto.getUserName(), dto.getUserPW(), dto.getUserMail()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
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
    public UserDTO getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean chooseThePage() throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean checkUserAndPW(String userName, String pw) throws SQLException, ClassNotFoundException {
        return userDAO.checkUserAndPW(userName, pw);
    }
}

package com.assignment.final_course_work.dao.Custom.impl;
import com.assignment.final_course_work.dao.Custom.UserDAO;
import com.assignment.final_course_work.dao.SQLUtil;
import com.assignment.final_course_work.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    public boolean chooseThePage() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM users");
        return (rs.next());
    }

    @Override
    public boolean checkUserAndPW(String userName, String pw) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT user_name, user_pw FROM users WHERE user_name =? AND user_pw =?",userName,pw);
        return (rst.next());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIDIndex = i + 1;
            return String.format("U%03d", newIDIndex);
        }
        return "U001";
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO users (user_id, user_name, user_pw, user_mail) VALUES (?,?,?,?)", generateNewID(), dto.getUserName(), dto.getUserPW(), dto.getUserMail());
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
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
    public User getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

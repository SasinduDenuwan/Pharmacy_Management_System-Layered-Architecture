package com.assignment.final_course_work.dao.Custom;
import com.assignment.final_course_work.dao.CrudDAO;
import com.assignment.final_course_work.dto.UserDTO;
import com.assignment.final_course_work.entity.User;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO <User> {

    public boolean chooseThePage() throws SQLException, ClassNotFoundException;
    public boolean checkUserAndPW(String userName, String pw) throws SQLException, ClassNotFoundException;

}

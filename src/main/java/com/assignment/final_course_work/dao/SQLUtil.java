package com.assignment.final_course_work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.assignment.final_course_work.db.DBConnection;

public class SQLUtil {
    public static <T>T execute(String sql,Object... obj) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        for (int i=0;i<obj.length;i++){
            pst.setObject((i+1),obj[i]);
        }

        if (sql.startsWith("select") || sql.startsWith("SELECT")){
            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;
        }else {
            int i = pst.executeUpdate();

            boolean isSaved = i >0;
            return (T) ((Boolean) isSaved);
        }
    }
}

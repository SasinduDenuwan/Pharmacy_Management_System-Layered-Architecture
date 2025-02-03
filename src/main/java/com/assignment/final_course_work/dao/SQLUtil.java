package com.assignment.final_course_work.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.assignment.final_course_work.db.DBConnection;

public class SQLUtil {
    public SQLUtil() {
    }

    public static <T> Object execute(String sql, Object... objects) throws SQLException, ClassNotFoundException, SQLException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for(int i = 0; i < objects.length; ++i) {
            pstm.setObject(i + 1, objects[i]);
        }

        return sql.startsWith("SELECT") ? (T) pstm.executeQuery() : pstm.executeUpdate() > 0;
    }
}

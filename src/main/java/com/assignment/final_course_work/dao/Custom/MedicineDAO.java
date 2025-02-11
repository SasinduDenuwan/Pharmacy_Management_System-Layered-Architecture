package com.assignment.final_course_work.dao.Custom;

import com.assignment.final_course_work.dao.CrudDAO;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;

public interface MedicineDAO extends CrudDAO<Medicine> {

    public boolean reduceMedicines(OrderDetail dto, Connection connection) throws SQLException, ClassNotFoundException ;
    String[] getMedicineInfo(String medicineName) throws SQLException, ClassNotFoundException;
    String getMedicineID(String medicineName) throws SQLException, ClassNotFoundException;
    public boolean reduceMedicine(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException;
//    boolean reduceMedicine()

}

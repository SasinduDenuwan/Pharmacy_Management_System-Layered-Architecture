package com.assignment.final_course_work.bo;

import com.assignment.final_course_work.dao.CrudDAO;
import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dto.MedicineDTO;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface MedicineBO extends SuperBO {
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public ArrayList<MedicineDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(MedicineDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(MedicineDTO dto) throws SQLException, ClassNotFoundException;
    public MedicineDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String getID(String name) throws SQLException, ClassNotFoundException;
    MedicineDTO getDetails(String id) throws SQLException, ClassNotFoundException;
    public boolean reduceMedicines(OrderDetail dto, Connection connection) throws SQLException, ClassNotFoundException ;
    String[] getMedicineInfo(String medicineName) throws SQLException, ClassNotFoundException;
    String getMedicineID(String medicineName) throws SQLException, ClassNotFoundException;
    public boolean reduceMedicine(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException;
}

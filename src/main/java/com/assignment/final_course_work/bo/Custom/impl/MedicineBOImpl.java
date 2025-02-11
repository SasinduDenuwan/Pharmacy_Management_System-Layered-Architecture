package com.assignment.final_course_work.bo.Custom.impl;

import com.assignment.final_course_work.bo.BOFactory;
import com.assignment.final_course_work.bo.MedicineBO;
import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.DAOFactory;
import com.assignment.final_course_work.dto.MedicineDTO;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicineBOImpl implements MedicineBO {

    private MedicineDAO medicineDAO = (MedicineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEDICINE);
    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return medicineDAO.generateNewID();
    }

    @Override
    public ArrayList<MedicineDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Medicine> all = medicineDAO.getAll();
        ArrayList<MedicineDTO> dtos = new ArrayList<>();
        for (Medicine medicine : all) {
            dtos.add(new MedicineDTO(medicine.getMedicine_id(), medicine.getMedicine_name(), medicine.getMedicine_unit_price(), medicine.getMedicine_pack_price(), medicine.getMedicine_unt_qty(), medicine.getMedicine_pack_qty()));
        }
        return dtos;
    }

    @Override
    public boolean save(MedicineDTO dto) throws SQLException, ClassNotFoundException {
        return medicineDAO.save(new Medicine(dto.getMedicineID(), dto.getMedicineName(), dto.getUnitPrice(), dto.getPackPrice(), dto.getUnitQty(), dto.getPackQty()));
    }

    @Override
    public boolean update(MedicineDTO dto) throws SQLException, ClassNotFoundException {
        return medicineDAO.update(new Medicine(dto.getMedicineID(), dto.getMedicineName(), dto.getUnitPrice(), dto.getPackPrice(), dto.getUnitQty(), dto.getPackQty()));
    }

    @Override
    public MedicineDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        return medicineDAO.getAllName();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return medicineDAO.delete(id);
    }

    @Override
    public String getID(String name) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public MedicineDTO getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean reduceMedicines(OrderDetail dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String[] getMedicineInfo(String medicineName) throws SQLException, ClassNotFoundException {
        return medicineDAO.getMedicineInfo(medicineName);
    }

    @Override
    public String getMedicineID(String medicineName) throws SQLException, ClassNotFoundException {
        return medicineDAO.getMedicineID(medicineName);
    }

    @Override
    public boolean reduceMedicine(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }
}
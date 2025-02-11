package com.assignment.final_course_work.dao.Custom.impl;

import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.SQLUtil;
import com.assignment.final_course_work.entity.Medicine;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {

    public boolean reduceMedicines(OrderDetail dto, Connection connection) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute(
//                "UPDATE medicines SET quantity_in_stock_single = quantity_in_stock_single - ?, quantity_in_stock_packs = quantity_in_stock_packs - ? WHERE medicine_id = ?", dto.getMedicineQty(),dto.getPackageQty(),dto.getMedicineID());
        return false;
    }

    @Override
    public String[] getMedicineInfo(String medicineName) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT medicine_name,medicine_unit_price, medicine_pack_price, medicine_unt_qty, medicine_pack_qty FROM medicine WHERE medicine_name = ? ", medicineName);
        String[] medicineInfo = new String[0];
        while (rst.next()) {
            medicineInfo = new String[]{rst.getString("medicine_name"), rst.getString("medicine_unit_price"), rst.getString("medicine_pack_price"), rst.getString("medicine_unt_qty"), rst.getString("medicine_pack_qty")};
        }
        return medicineInfo;
    }

    @Override
    public String getMedicineID(String medicineName) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT medicine_id FROM medicine WHERE medicine_name = ?", medicineName);
        String str = "";

        while (rst.next()) {
            str = rst.getString("medicine_id");
        }
        return str;
    }

    public String getID(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT medicine_id FROM medicine WHERE medicine_name = ?",name);
        String str  = "";

        while (rst.next()){
            str = rst.getString("medicine_name");
        }
        return str;
    }

    public Medicine getDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM medicine WHERE medicine_name =?", id);
        Medicine medicine = new Medicine();
        while (rs.next()){
            medicine.setMedicine_id(rs.getString("medicine_id"));
            medicine.setMedicine_name(rs.getString("medicine_name"));
            medicine.setMedicine_unit_price(rs.getDouble("medicine_unit_price"));
            medicine.setMedicine_pack_price(rs.getDouble("medicine_pack_price"));
            medicine.setMedicine_unt_qty(rs.getInt("medicine_unt_qty"));
            medicine.setMedicine_pack_qty(rs.getInt("medicine_pack_qty"));
        }
        return medicine;
    }

    public ArrayList<Medicine> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Medicine> medicines = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM medicine");

        while (rst.next()) {

            medicines.add(new Medicine(
                    rst.getString("medicine_id"),
                    rst.getString("medicine_name"),
                    rst.getDouble("medicine_unit_price"),
                    rst.getDouble("medicine_pack_price"),
                    rst.getInt("medicine_unt_qty"),
                    rst.getInt("medicine_pack_qty")
            ));
        }
        return medicines;
    }

    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        ArrayList<String> medicationIDs = new ArrayList<String>();
        ResultSet rs = SQLUtil.execute("SELECT medicine_name FROM medicine");

        while (rs.next()){
            medicationIDs.add(rs.getString("medicine_name"));
        }
        return medicationIDs;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT medicine_id FROM medicine ORDER BY medicine_id DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIDIndex = i + 1;
            return String.format("M%03d", newIDIndex);
        }
        return "M001";
    }

    @Override
    public Medicine search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM medicine WHERE medicine_id = ?", id);

    }

    @Override
    public boolean save(Medicine dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute(
                "INSERT INTO medicine (medicine_id, medicine_name, medicine_unit_price, medicine_pack_price, medicine_unt_qty, medicine_pack_qty) VALUES (?,?,?,?,?,?)"
                , dto.getMedicine_id()
                , dto.getMedicine_name()
                , dto.getMedicine_unit_price()
                , dto.getMedicine_pack_price()
                , dto.getMedicine_unt_qty()
                , dto.getMedicine_pack_qty()
        );
    }

    @Override
    public boolean update(Medicine dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE medicine SET medicine_name = ?, medicine_unit_price = ?, medicine_pack_price = ?, medicine_unt_qty  = ?, medicine_pack_qty = ? WHERE medicine_id = ?"
                , dto.getMedicine_name()
                , dto.getMedicine_unit_price()
                , dto.getMedicine_pack_price()
                , dto.getMedicine_unt_qty()
                , dto.getMedicine_pack_qty()
                , dto.getMedicine_id()
        );
    }

    @Override
    public boolean reduceMedicine(OrderDetail orderDetail, Connection connection) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE medicine SET medicine_unt_qty = medicine_unt_qty - ?, medicine_pack_qty = medicine_pack_qty - ? WHERE medicine_id = ?");
        preparedStatement.setInt(1, orderDetail.getMedicineQty());
        preparedStatement.setInt(2, orderDetail.getPackageQty());
        preparedStatement.setString(3, orderDetail.getMedicineId());

        return preparedStatement.executeUpdate() == 1;
    }
}

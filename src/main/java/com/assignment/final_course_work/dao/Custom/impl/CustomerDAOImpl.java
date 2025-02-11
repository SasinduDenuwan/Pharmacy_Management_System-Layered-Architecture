package com.assignment.final_course_work.dao.Custom.impl;

import com.assignment.final_course_work.dao.Custom.CustomerDAO;
import com.assignment.final_course_work.dao.SQLUtil;
import com.assignment.final_course_work.entity.Customer;
import com.assignment.final_course_work.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
        if (rst.next()) {
            String lastID = rst.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIDIndex = i + 1;
            return String.format("C%03d", newIDIndex);
        }
        return "C001";
    }

    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {

            customers.add(new Customer(
                    rst.getString("customer_id"),
                    rst.getString("customer_name"),
                    rst.getString("customer_email"),
                    rst.getString("customer_address"),
                    rst.getString("customer_contact")));
        }
        return customers;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "INSERT INTO customer (customer_id,customer_name,customer_email,customer_address,customer_contact) VALUES (?,?,?,?,?)"
                , dto.getCusID()
                , dto.getCusName()
                , dto.getCusEmail()
                , dto.getCusAddress()
                , dto.getCusPhone()
        );
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE customer SET customer_name = ?, customer_email = ?, customer_address  = ?, customer_contact = ? WHERE customer_id = ?"
                , dto.getCusName()
                , dto.getCusEmail()
                , dto.getCusAddress()
                , dto.getCusPhone()
                , dto.getCusID()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM customer WHERE customer_id = ?", id);
    }



    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        ArrayList<String> names = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT customer_name FROM customer");

        while (resultSet.next()) {
            names.add(resultSet.getString("customer_name"));
        }
        return names;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id =?", id);
        Customer customer = new Customer();

        while (resultSet.next()) {
            customer.setCusID(resultSet.getString("customer_id"));
            customer.setCusName(resultSet.getString("customer_name"));
            customer.setCusEmail(resultSet.getString("customer_email"));
            customer.setCusAddress(resultSet.getString("customer_address"));
            customer.setCusPhone(resultSet.getString("customer_contact"));
        }
        return customer;
    }


    public String getID(String name) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT customer_id FROM customer WHERE customer_name = ?", name);
        String str = "";

        while (rst.next()) {
            str = rst.getString("customer_id");
        }
        return str;
    }

    public Customer getDetails(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute("SELECT * FROM medicines WHERE customer_id = ?", id);
        Customer customer = new Customer();
        while (rs.next()) {
            customer.setCusID(rs.getString("medicine_id"));
            customer.setCusName(rs.getString("customer_name"));
            customer.setCusEmail(rs.getString("customer_email"));
            customer.setCusAddress(rs.getString("customer_address"));
            customer.setCusPhone(rs.getString("customer_contact"));
        }
        return customer;
    }
}
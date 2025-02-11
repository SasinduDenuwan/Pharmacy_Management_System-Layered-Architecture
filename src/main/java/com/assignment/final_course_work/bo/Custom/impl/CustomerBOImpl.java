package com.assignment.final_course_work.bo.Custom.impl;

import com.assignment.final_course_work.bo.CustomerBO;
import com.assignment.final_course_work.dao.Custom.CustomerDAO;
import com.assignment.final_course_work.dao.DAOFactory;
import com.assignment.final_course_work.dto.CustomerDTO;
import com.assignment.final_course_work.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : all) {
            customerDTOS.add(new CustomerDTO(customer.getCusID(), customer.getCusName(), customer.getCusEmail(), customer.getCusAddress(), customer.getCusPhone()));
        }
        return customerDTOS;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCusID(), dto.getCusName(), dto.getCusEmail(), dto.getCusAddress(), dto.getCusPhone()));
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCusID(), dto.getCusName(), dto.getCusEmail(), dto.getCusAddress(), dto.getCusPhone()));
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        Customer search = customerDAO.search(id);
        return new CustomerDTO(search.getCusID(), search.getCusName(), search.getCusEmail(), search.getCusAddress(), search.getCusPhone());
    }

    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllName();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String getID(String name) throws SQLException, ClassNotFoundException {
        return customerDAO.getID(name);
    }

    @Override
    public CustomerDTO getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

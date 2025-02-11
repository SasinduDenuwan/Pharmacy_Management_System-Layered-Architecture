package com.assignment.final_course_work.dao;

import com.assignment.final_course_work.dao.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;}

    public enum DAOType{
        CUSTOMER, MEDICINE, ORDER, ORDERDETAIL, USER, QUERY
    }

    public SuperDAO getDAO(DAOType type){
        switch (type){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailsDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}

package com.assignment.final_course_work.bo;

import com.assignment.final_course_work.bo.Custom.impl.CustomerBOImpl;
import com.assignment.final_course_work.bo.Custom.impl.MedicineBOImpl;
import com.assignment.final_course_work.bo.Custom.impl.OrderBOImpl;
import com.assignment.final_course_work.bo.Custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){return boFactory == null ? boFactory = new BOFactory() : boFactory;}

    public enum BOType{
        CUSTOMER, MEDICINE, ORDER, USER
    }

    public SuperBO getBO(BOFactory.BOType type){
        switch (type) {
            case CUSTOMER :
                return new CustomerBOImpl();
            case MEDICINE :
                return new MedicineBOImpl();
            case ORDER :
                return new OrderBOImpl();
            case USER :
                return new UserBOImpl();
            default:
                return null;
        }
    }
}

package com.assignment.final_course_work.dao.Custom;

import com.assignment.final_course_work.dao.CrudDAO;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO <OrderDetail> {

    boolean saveOrderDetails(ArrayList<OrderDetail> orderDetailDTOs, Connection connection) throws SQLException, ClassNotFoundException;
}

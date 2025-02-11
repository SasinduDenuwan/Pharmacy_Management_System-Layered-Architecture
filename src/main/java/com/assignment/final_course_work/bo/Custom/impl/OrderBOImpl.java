package com.assignment.final_course_work.bo.Custom.impl;

import com.assignment.final_course_work.bo.OrderBO;
import com.assignment.final_course_work.dao.Custom.MedicineDAO;
import com.assignment.final_course_work.dao.Custom.OrderDAO;
import com.assignment.final_course_work.dao.Custom.OrderDetailsDAO;
import com.assignment.final_course_work.dao.Custom.impl.MedicineDAOImpl;
import com.assignment.final_course_work.dao.Custom.impl.OrderDetailsDAOImpl;
import com.assignment.final_course_work.dao.DAOFactory;
import com.assignment.final_course_work.db.DBConnection;
import com.assignment.final_course_work.dto.OrderDTO;
import com.assignment.final_course_work.dto.OrderDetailDTO;
import com.assignment.final_course_work.entity.Order;
import com.assignment.final_course_work.entity.OrderDetail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {

    private OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAIL);
    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);

    public boolean placeOrder(OrderDTO order) throws SQLException {

        Connection connection = null;

        try {
            connection = DBConnection.getDbConnection().getConnection();

            connection.setAutoCommit(false);

            PreparedStatement pst = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?)");
            pst.setString(1, order.getOrderID());
            pst.setString(2, order.getCustomerID());
            pst.setDate(3, Date.valueOf(order.getOrderDate()));
            pst.setDouble(4, order.getTotalPrice());

            if (pst.executeUpdate() == 1) {

                ArrayList<OrderDetail> orderDetails =  new ArrayList<>();

                for (OrderDetailDTO orderDetailDTO : order.getOrderDetailDTOs()) {

                    OrderDetail orderDetail = new OrderDetail();

                    orderDetail.setOrderID(order.getOrderID());
                    orderDetail.setMedicineId(orderDetailDTO.getMedicineId());
                    orderDetail.setPackageQty(orderDetailDTO.getPackageQty());
                    orderDetail.setMedicineQty(orderDetailDTO.getMedicineQty());
                    orderDetail.setMedicinePrice(orderDetailDTO.getMedicinePrice());
                    orderDetail.setMedicinePrice(orderDetailDTO.getMedicinePrice());

                    orderDetails.add(orderDetail);
                }

                boolean isOrderDetailSaved = orderDetailsDAO.saveOrderDetails(orderDetails, connection);

                if (isOrderDetailSaved){
                    connection.commit();
                    connection.setAutoCommit(true);

                    System.out.println("Order saved Successfully !!!");

                    return true;

                }else {
                    System.out.println("Order saved Failure");
                }

            } else {
                connection.rollback();
                connection.setAutoCommit(true);
                System.out.println("Error in Order table");
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllName() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getID(String name) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDTO getDetails(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

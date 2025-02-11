package com.assignment.final_course_work.entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Order implements Serializable {
    private String orderID;
    private String orderDate;
    private String customerID;
    private double totalPrice;

    private ArrayList<OrderDetail> orderDetailDTOs;
}

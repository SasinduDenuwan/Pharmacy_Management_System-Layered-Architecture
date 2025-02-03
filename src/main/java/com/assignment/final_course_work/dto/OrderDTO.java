package com.assignment.final_course_work.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDTO {
    private String orderID;
    private String orderDate;
    private String customerID;
    private double totalPrice;

    private ArrayList<OrderDetailDTO> orderDetailDTOs;
}

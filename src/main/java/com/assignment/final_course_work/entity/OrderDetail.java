package com.assignment.final_course_work.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetail implements Serializable {
    private String orderID;
    private String medicineId;
    private int packageQty;
    private int medicineQty;
    private double packagePrice;
    private double medicinePrice;
}

package com.assignment.final_course_work.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailDTO {
    private String orderID;
    private String medicineId;
    private int packageQty;
    private int medicineQty;
    private double packagePrice;
    private double medicinePrice;
}

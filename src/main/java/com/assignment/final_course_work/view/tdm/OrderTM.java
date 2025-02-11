package com.assignment.final_course_work.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderTM {
    private String medicineID;
    private String medicineName;
    private int packQty;
    private double packPrice;
    private int unitQty;
    private double unitPrice;
    private double totalPrice;
}

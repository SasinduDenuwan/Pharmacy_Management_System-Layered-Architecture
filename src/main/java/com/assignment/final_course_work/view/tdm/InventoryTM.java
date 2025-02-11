package com.assignment.final_course_work.view.tdm;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString 

public class InventoryTM {
    private String medicineID;
    private String medicineName;
    private double unitPrice;
    private double packPrice;
    private int unitQty;
    private int packQty;
}

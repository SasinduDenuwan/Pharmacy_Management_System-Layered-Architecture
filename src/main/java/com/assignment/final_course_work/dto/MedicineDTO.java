package com.assignment.final_course_work.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MedicineDTO {
    private String medicineID;
    private String medicineName;
    private double unitPrice;
    private double packPrice;
    private int unitQty;
    private int packQty;
}

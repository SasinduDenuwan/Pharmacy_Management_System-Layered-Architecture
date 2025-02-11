package com.assignment.final_course_work.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Medicine {
    private String medicine_id;
    private String medicine_name;
    private double medicine_unit_price;
    private double medicine_pack_price;
    private int medicine_unt_qty;
    private int medicine_pack_qty;
}

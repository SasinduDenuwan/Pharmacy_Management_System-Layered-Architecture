package com.assignment.final_course_work.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer implements Serializable {
    private String cusID;
    private String cusName;
    private String cusEmail;
    private String cusAddress;
    private String cusPhone;
}

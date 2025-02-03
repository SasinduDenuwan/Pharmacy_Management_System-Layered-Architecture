package com.assignment.final_course_work.dto;

import java.io.Serializable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerDTO implements Serializable {
    private String cusID;
    private String cusName;
    private String cusEmail;
    private String cusAddress;
    private String cusPhone;
}

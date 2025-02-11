package com.assignment.final_course_work.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User implements Serializable {
    private String userID;
    private String userName;
    private String userPW;
    private String userMail;
}

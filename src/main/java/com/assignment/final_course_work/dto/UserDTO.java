package com.assignment.final_course_work.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserDTO {
    private String userID;
    private String userName;
    private String userPW;
    private String userMail;
}

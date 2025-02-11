package com.assignment.final_course_work.util;

public class SaveUser {

    private static SaveUser instance;
    private String name;

    private SaveUser() {}

    private SaveUser(String name) {
        this.name = name;
    }

    public static SaveUser getInstance() {
        if (instance == null) {
            instance = new SaveUser();
        }
        return instance;
    }

    public String getUserID() {
        return name;
    }

    public void setUserID(String userID) {
        this.name = userID;
    }
}

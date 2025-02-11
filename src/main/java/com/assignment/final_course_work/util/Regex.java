package com.assignment.final_course_work.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public boolean isTextValid(String text, String type) {
        String field = "";

        String textField = switch (type) {
            case "name" -> "^[A-Za-z ]+$";
            case "phone" -> "^[0]{1}[7]{1}[01245678]{1}[0-9]{7}$";
            case "email" -> "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            case "address" -> "^\\S+$";
            case "userName" -> "^[A-Za-z][A-Za-z0-9_]{5,15}$";
            case "NIC" -> "^[0-9]{9}[vVxX]||[0-9]{12}$";
            case "password" -> "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,20}$";
            case "qty" -> "^(?:0|[1-9]{0,2}|1000)$";
            case "amount" -> "^(?:\\$)?(?!0\\d)(\\d+|\\d+\\.\\d{1,2})$";
            case "price" -> "^(\\\\d+)||((\\\\d+\\\\.)(\\\\d){2})$";
            case "otp" -> "^\\d{5}$";
            case "customerID" -> "C\\d{3}";
            case "payment" -> "^(\\d+)(\\.\\d{1,2})?$";
            default -> "";
        };

        Pattern pattern = Pattern.compile(textField);

        if (text != null) {
            if (text.trim().isEmpty()) {
                return false;
            }
        }else{
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}

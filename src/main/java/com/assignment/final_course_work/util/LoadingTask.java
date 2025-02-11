package com.assignment.final_course_work.util;

import javafx.concurrent.Task;

public class LoadingTask extends Task<Integer> {

    @Override
    protected Integer call() throws Exception {
        for (double i = 0; i <= 90; i++) {
            updateProgress(i, 90.00);
            Thread.sleep(10);
        }
        return 100;
    }
}

package ua.kiev.prog.spring.sample1;

import java.util.Date;

public class DatePreprocessor implements Preprocessor {
    public String prepare(String msg) {
        return "[" + new Date() + "] " + msg;
    }
}


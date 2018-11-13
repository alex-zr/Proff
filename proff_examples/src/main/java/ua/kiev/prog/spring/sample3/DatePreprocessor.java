package ua.kiev.prog.spring.sample3;

import org.springframework.stereotype.Component;

import java.util.Date;

public class DatePreprocessor implements Preprocessor {
    public String prepare(String msg) {
        return "[" + new Date() + "] " + msg;
    }
}


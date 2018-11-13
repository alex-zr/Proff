package ua.kiev.prog.spring.sample2;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DatePreprocessor implements Preprocessor {
    public String prepare(String msg) {
        return "[" + new Date() + "] " + msg;
    }
}


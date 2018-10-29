package com.gmail.vladgural.controller;

import java.util.LinkedList;
import java.util.List;

public class LogPassList {
    private static final LogPassList lpList = new LogPassList();

    private final List<LogPass> list = new LinkedList<>();

    public static LogPassList getInstance(){
        return lpList;
    }

    private LogPassList(){
        list.add(new LogPass("Vlad","123"));
        list.add(new LogPass("admin", "admin"));
        list.add(new LogPass("Yura","321"));
    }

    public List<LogPass> getList(){
        return list;
    }

    public LogPass checkLogin(String login){
        for(LogPass lp:list){
            if(lp.getLogin().equals(login)&&lp.isAccess())
                return lp;
        }
        return null;
    }
}

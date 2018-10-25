package com.gmail.vladgural.controller;

import java.util.LinkedList;
import java.util.List;

public class Users {
    private List<String> users;

    public Users(){
        users = new LinkedList<>();
    }

    public void addUser(String user){
        users.add(user);
    }

    public List<String> getList(){
        return this.users;
    }
}

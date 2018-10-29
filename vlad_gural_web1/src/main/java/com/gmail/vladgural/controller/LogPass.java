package com.gmail.vladgural.controller;

public class LogPass {
    private String login;
    private String passWord;
    private boolean access;
    private int numberOfReadMessages;

    public LogPass(String login, String passWord){
        this.login = login;
        this.passWord = passWord;
        this.access = false;
        this.numberOfReadMessages = 0;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public int getNumberOfReadMessages() {
        return numberOfReadMessages;
    }

    public void setNumberOfReadMessages(int numberOfReadMessages) {
        this.numberOfReadMessages = numberOfReadMessages;
    }
}

package com.gmail.vladgural.controller;


import com.gmail.vladgural.client.Message;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(List<Message> sourceList, int fromIndex, LogPass lp) {
        this.list = new ArrayList<>();
        Message msg = null;
        int j = lp.getNumberOfReadMessages();
        for (int i = fromIndex; i < sourceList.size(); i++) {
            msg = sourceList.get(i);
            if(msg.getTo()==null || msg.getFrom().equals(lp.getLogin()) || msg.getTo().equals(lp.getLogin()))
                list.add(msg);
            j++;
        }
        lp.setNumberOfReadMessages(j);
    }
}
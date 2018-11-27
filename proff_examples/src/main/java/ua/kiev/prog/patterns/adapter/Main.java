package ua.kiev.prog.patterns.adapter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new GoodList<>(10);
        list.add("one");
        list.add("two");

        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }
}

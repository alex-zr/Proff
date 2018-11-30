package ua.kiev.prog.patterns.builder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Integer[] array = {2,3,6,8};

        List<Integer> list = Arrays.stream(array)
                .map(i -> i + 2)
                .map(i -> (int) Math.sqrt(i))
                .filter(i -> i == 2)
                .collect(Collectors.toList());

        String res = new StringBuilder()
                .append("3")
                .append("12")
                .append("21")
                .toString();

        Laptop laptop = new Laptop.Builder()
                .withHddCapasity(120)
                .withProcessorType(ProcessorType.AMD)
                .withScreenSize(15)
                .build();
    }
}

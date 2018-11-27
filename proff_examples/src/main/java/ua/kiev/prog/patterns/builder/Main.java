package ua.kiev.prog.patterns.builder;

public class Main {
    public static void main(String[] args) {
        Laptop laptop = new Laptop.Builder()
                .withHddCapasity(120)
                .withProcessorType(ProcessorType.AMD)
                .withScreenSize(15)
                .build();
    }
}

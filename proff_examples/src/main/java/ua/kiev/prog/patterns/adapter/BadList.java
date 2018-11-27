package ua.kiev.prog.patterns.adapter;

public class BadList {
    private Object[] items;
    private int index;

    public BadList(int capacity) {
        items = new Object[capacity];
    }

    public int length() {
        return index;
    }

    public void add(Object o) {
        items[index++] = o;
    }

    public Object getByIndex(int index) {
        if (index < 0 || index >= items.length)
            throw new IndexOutOfBoundsException();

        return items[index];
    }
}

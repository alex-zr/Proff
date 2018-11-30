package ua.kiev.prog.patterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BallsIterator implements Iterator<Ball> {
    private List<Ball> list = new ArrayList<>();
    private int index;

    public BallsIterator(Basket basket, BallColor color) {
        for (Ball b : basket.getItems()) {
            if (b.getColor() == color || color == BallColor.ANY)
                list.add(b);
        }
    }

    @Override
    public boolean hasNext() {
        return (index < list.size());
    }

    @Override
    public Ball next() {
        return list.get(index++);
    }
}

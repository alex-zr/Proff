package ua.kiev.prog.patterns.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Basket implements Iterable<Ball> {
    private List<Ball> balls = new ArrayList<>();

    public Basket() {
        balls.add(new Ball(BallColor.BLUE, 1));
        balls.add(new Ball(BallColor.RED, 2));
        balls.add(new Ball(BallColor.WHITE, 3));
        balls.add(new Ball(BallColor.BLUE, 4));
        balls.add(new Ball(BallColor.BLUE, 5));
        balls.add(new Ball(BallColor.WHITE, 6));
        balls.add(new Ball(BallColor.RED, 7));
        balls.add(new Ball(BallColor.RED, 8));
        balls.add(new Ball(BallColor.BLUE, 9));
    }

    public List<Ball> getItems() {
        return Collections.unmodifiableList(balls);
    }

    @Override
    public Iterator<Ball> iterator() {
        return new BallsIterator(this, BallColor.ANY);
    }

    public Iterator<Ball> iterator(BallColor color) {
        return new BallsIterator(this, color);
    }
}

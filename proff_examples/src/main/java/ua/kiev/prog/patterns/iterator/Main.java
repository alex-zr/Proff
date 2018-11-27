package ua.kiev.prog.patterns.iterator;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
	    // Iterator
        Basket basket = new Basket();

        System.out.println("All balls:");
        Iterator<Ball> iterator = basket.iterator();
        while (iterator.hasNext())
            System.out.println("\t" + iterator.next());

        System.out.println("Blue balls:");
        iterator = basket.iterator(BallColor.BLUE);
        while (iterator.hasNext())
            System.out.println("\t" + iterator.next());
    }
}

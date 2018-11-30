package ua.kiev.prog.patterns.iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

/*        Iterable<Integer> collection = new HashSet<>();

        Iterator<Integer> listItr;
        for (listItr = collection.iterator(); listItr.hasNext();) {
            Integer el = listItr.next();
            System.out.println(el);
        }*/

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

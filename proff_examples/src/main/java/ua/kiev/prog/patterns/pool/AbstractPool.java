package ua.kiev.prog.patterns.pool;

import java.util.HashSet;

public abstract class AbstractPool<T> {
    private HashSet<T> available = new HashSet<>();
    private HashSet<T> inUse = new HashSet<>();

    protected abstract T create();

    public synchronized T get() {
        if (available.size() <= 0) {
            available.add(create());
        }

        T t = available.iterator().next();
        available.remove(t);
        inUse.add(t);

        return t;
    }

    public synchronized void put(T t) {
        inUse.remove(t);
        available.add(t);
    }

    @Override
    public String toString() {
        return String.format("Pool available=%d, inUse=%d", available.size(), inUse.size());
    }
}

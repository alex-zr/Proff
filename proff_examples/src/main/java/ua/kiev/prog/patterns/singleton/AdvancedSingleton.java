package ua.kiev.prog.patterns.singleton;

// Double Checked Locking & volatile
public class AdvancedSingleton {
    private static volatile AdvancedSingleton instance;

    public static AdvancedSingleton getInstance() {
        AdvancedSingleton localInstance = instance;
        if (localInstance == null) {
            synchronized (AdvancedSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AdvancedSingleton();
                }
            }
        }
        return localInstance;
    }

    public void check() {
        System.out.println("Advanced Hello!");
    }
}
package ua.kiev.prog.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class ChatSubject {
    private List<ClientObserver<String>> observers = new ArrayList<>();
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyAllObservers(state);
    }

    public void attach(ClientObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(String state){
        for (ClientObserver observer : observers) {
            observer.update(state);
        }
    }
}

package ua.kiev.prog.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class ChatSubject {
    private List<ClientObserver> observers = new ArrayList<>();
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(ClientObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (ClientObserver observer : observers) {
            observer.update();
        }
    }
}

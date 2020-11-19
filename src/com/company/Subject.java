package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer)
    {
        observers.remove(observer);
    }
    public void notifyObservers(String message)
    {
        for(Observer observer: observers)
        {
            observer.update(this, message);
        }
    }


}

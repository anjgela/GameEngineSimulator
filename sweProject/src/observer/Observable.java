package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
	private List<Observer> observers = new ArrayList<>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	protected void notifyObservers(Object object) {
		for (Observer observer : observers) {
			observer.update(object);
		}
			
	}
}

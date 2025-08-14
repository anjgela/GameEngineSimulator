package observer;

import engine.Event;

public interface Observable {
	public abstract void attach(Observer observer);
	public abstract void detach(Observer observer);
	public abstract void notifyObservers(Event event);
}

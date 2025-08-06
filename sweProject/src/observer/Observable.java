package observer;

import engine.Event;

public abstract class Observable {
	protected abstract void attach(Observer observer);
	protected abstract void detach(Observer observer);
	protected abstract void notifyObservers(Event event);
}

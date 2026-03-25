package observer;

import static org.junit.Assert.*;
import org.junit.Test;

public class ObservableTest {
	
	//stubs
	public class DummyObservable extends Observable {
		public void triggerFakeEvent(Object obj) {
			notifyObservers(obj);
		}
	}

	public class DummyObserver implements Observer {
		private Object receivedEvent = null;

		@Override
		public void update(Object object) {
			this.receivedEvent = object;
		}

		public Object getReceivedEvent() {
			return receivedEvent;
		}
	}

	@Test
	public void notifyObserversTest() {
		DummyObservable subject = new DummyObservable();
		DummyObserver observer = new DummyObserver();
		
		subject.attach(observer);
			
		String payload = "Test Message";
		subject.triggerFakeEvent(payload);
			
		assertEquals(payload, observer.getReceivedEvent());
	}
}

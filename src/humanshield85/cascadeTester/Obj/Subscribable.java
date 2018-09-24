package humanshield85.cascadeTester.Obj;

public interface Subscribable {

	public void addSubscriber(Subscriber subscriber);
	public void removeSubscriber(Subscriber subscriber);
	public void updateSubscribers(StateChangedEvent stateChangedEvent);
}

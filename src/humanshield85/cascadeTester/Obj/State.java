package humanshield85.cascadeTester.Obj;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class State implements Serializable, Subscribable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9009632888435028602L;
	protected ArrayList<File> images = new ArrayList<File>();
	protected final ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
	protected File imagesPath;
	protected int effect;
	protected File cascadeFile;
	protected int activeImageIndex;
	protected boolean hasValidImages = false;
	private boolean isCascadeSet = false;

	public State() {

	}

	public synchronized int getEffect() {
		return effect;
	}

	public synchronized boolean HasValidImages() {
		return hasValidImages;
	}

	public synchronized void setEffect(int effect) {
		this.effect = effect;
		this.updateSubscribers(new StateChangedEvent(StateChangedEvent.EFFECT_CHNAGED));
	}

	public synchronized File getCascadeFile() {
		return cascadeFile;
	}

	public synchronized void setCascadeFile(File cascadeFile) {
		this.cascadeFile = cascadeFile;
		this.isCascadeSet = true;
		this.updateSubscribers(new StateChangedEvent(StateChangedEvent.CASCADE_FILE_CHANGED));
	}

	public synchronized int getActiveImageIndex() {
		return activeImageIndex;
	}

	public synchronized void setImagesPath(File imagesPath) {
		ArrayList<File> imagesInt = new ArrayList<File>();
		for (File f : imagesPath.listFiles()) {
			if (f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".png")
					|| f.getName().toLowerCase().endsWith(".gif") || f.getName().toLowerCase().endsWith(".bmp")) {
				imagesInt.add(f);
			}
		}
		if (!imagesInt.isEmpty()) {
			this.imagesPath = imagesPath;
			this.hasValidImages = true;
			images = imagesInt;
			updateSubscribers(new StateChangedEvent(StateChangedEvent.IMAGE_LIST_CHNAGED));
			this.setActiveImageIndex(0);
		}
	}

	public synchronized File getImagesPath() {
		return imagesPath;
	}

	private synchronized void setActiveImageIndex(int activeImageIndex) {
		this.activeImageIndex = activeImageIndex;
		this.updateSubscribers(new StateChangedEvent(StateChangedEvent.ACTIVE_IMAGE_CHNAGED));
	}

	public synchronized void nextImage() {
		if (this.activeImageIndex < images.size() - 1) {
			this.setActiveImageIndex(activeImageIndex + 1);
		}
	}

	public synchronized void prevImage() {
		if (this.activeImageIndex > 0) {
			this.setActiveImageIndex(activeImageIndex - 1);
		}
	}

	public File getCurrentImage() {
		if (this.hasValidImages)
			return images.get(activeImageIndex);
		else
			return null;
	}

	public int getMaxImageCount() {
		return images.size();
	}

	@Override
	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void removeSubscriber(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	@Override
	public void updateSubscribers(StateChangedEvent stateChangedEvent) {
		for (Subscriber subscriber : subscribers) {
			subscriber.getSubscriptionUpdate(stateChangedEvent);
		}
	}

	public boolean isCascadeSet() {
		return isCascadeSet;
	}

}

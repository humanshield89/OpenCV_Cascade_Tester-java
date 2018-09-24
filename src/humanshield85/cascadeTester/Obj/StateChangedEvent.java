package humanshield85.cascadeTester.Obj;

public class StateChangedEvent {
	public static final int IMAGE_LIST_CHNAGED = 0;
	public static final int ACTIVE_IMAGE_CHNAGED = 1;
	public static final int EFFECT_CHNAGED = 3;
	public static final int CASCADE_FILE_CHANGED = 4;
	//public static final int IMAGE_LIST_CHNAGED = 0;
	private int type;
	/**
	 * check the static fields of StateChangedEvent for more types
	 * @param TYPE
	 */
	public StateChangedEvent (int TYPE) {
		type = TYPE;
	}
	public int getType() {
		return type;
	}
}

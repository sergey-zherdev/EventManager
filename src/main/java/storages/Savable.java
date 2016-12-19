package storages;

import events.Eventable;

import java.util.HashMap;

/**
 * Created by Сергей on 09.11.2016.
 */
public interface Savable {
	void save(Eventable event);
	HashMap<String, String> watchAll();
}

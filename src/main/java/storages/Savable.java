package storages;

import events.Eventable;

/**
 * Created by Сергей on 09.11.2016.
 */
public interface Savable {
	void save(Eventable event);
}

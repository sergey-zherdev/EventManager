package storages;

/**
 * Created by Сергей on 09.11.2016.
 */
public class StorageFactory {
	public static Savable getStorage(StorageList storageList){
		switch (storageList) {
			case BASE: return new Base();
			case CONTAINER: return new Container();
			default: return null;
		}
	}
}

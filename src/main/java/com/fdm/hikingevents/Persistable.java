package com.fdm.hikingevents;

public interface Persistable<T extends Persistable> {

	int getId();
	void update(T persistable);

}

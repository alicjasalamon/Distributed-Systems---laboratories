package org.corba.impl.server;

import org.corba.generated.ItemAPOA;
import org.omg.CORBA.IntHolder;

public class ItemAImpl extends ItemAPOA{
	
	String name;

	public ItemAImpl(String name) {
		this.name = name;
	}

	@Override
	public void actionA(float a, IntHolder b) {
		b.value=1;
		System.out.println("action A!");
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int get_item_age() {
		return 1;
	}

}

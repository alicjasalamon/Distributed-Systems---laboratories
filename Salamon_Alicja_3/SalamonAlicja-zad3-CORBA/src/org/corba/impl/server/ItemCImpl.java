package org.corba.impl.server;

import org.corba.generated.ItemCPOA;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.ShortHolder;

public class ItemCImpl extends ItemCPOA{

	String name;

	public ItemCImpl(String name) {
		this.name = name;
	}
	
	@Override
	public void actionC(IntHolder a, ShortHolder b) {
		System.out.println("action C");
		a.value = 333;
		b.value = 33;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int get_item_age() {
		return 3;
	}

}

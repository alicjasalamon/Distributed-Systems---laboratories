package org.corba.impl.server;

import org.corba.generated.ItemBPOA;

public class ItemBImpl extends ItemBPOA{
	
	String name;

	public ItemBImpl(String name) {
		this.name = name;
	}

	@Override
	public float actionB(String a) {
		System.out.println("Action B!");
		return 222222;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public int get_item_age() {
		return 2;
	}

}

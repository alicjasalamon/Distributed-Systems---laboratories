package org.corba.impl.server;

import org.corba.generated.ItemPOA;

public class ItemImpl extends ItemPOA{

	@Override
	public String name() {
		return "justItem";
	}

	@Override
	public int get_item_age() {
		return 0;
	}

}

package org.corba.impl.server;

import java.util.HashMap;import java.util.Map;

import org.corba.generated.AFactoryPOA;
import org.corba.generated.Item;
import org.corba.generated.ItemAlreadyExists;
import org.corba.generated.ItemBusy;
import org.corba.generated.ItemHelper;
import org.corba.generated.ItemNotExists;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class AFactoryImpl extends AFactoryPOA {

	POA poa;

	public AFactoryImpl(POA poa) {
		this.poa = poa;
	}

	Map<String, Item> freeItems = new HashMap<String, Item>();
	Map<String, Item> busyItems = new HashMap<String, Item>();

	@Override
	public Item create_item(String name, String type) throws ItemAlreadyExists {

		if (freeItems.keySet().contains(name))
			throw new ItemAlreadyExists();
		if (busyItems.keySet().contains(name))
			throw new ItemAlreadyExists();

		Item i = null;
		try {
			if (type.equals("ItemA")) {
				ItemAImpl iAimpl = new ItemAImpl(name);
				i = ItemHelper.narrow(poa.servant_to_reference(iAimpl));
				freeItems.put(name, i);
			} else if (type.equals("ItemB")) {
				ItemBImpl iBimpl = new ItemBImpl(name);
				i = ItemHelper.narrow(poa.servant_to_reference(iBimpl));
				freeItems.put(name, i);
			} else if (type.equals("ItemC")) {
				ItemCImpl iCimpl = new ItemCImpl(name);
				i = ItemHelper.narrow(poa.servant_to_reference(iCimpl));
				freeItems.put(name, i);
			} else {
				ItemImpl iimpl = new ItemImpl();
				i = ItemHelper.narrow(poa.servant_to_reference(iimpl));
				freeItems.put(name, i);
			}
		} catch (ServantNotActive e) {
			e.printStackTrace();
		} catch (WrongPolicy e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public Item take_item(String name) throws ItemNotExists, ItemBusy {

		Item item = busyItems.get(name);
		if (item != null)
			throw new ItemBusy();

		item = freeItems.get(name);
		if (item == null)
			throw new ItemNotExists();

		busyItems.put(name, freeItems.remove(name));
		return item;

	}

	@Override
	public void release_item(String name) throws ItemNotExists {

		if (!busyItems.keySet().contains(name))
			throw new ItemNotExists();
		else
			freeItems.put(name, busyItems.remove(name));

	}

}

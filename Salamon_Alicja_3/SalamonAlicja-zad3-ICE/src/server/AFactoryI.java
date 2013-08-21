package server;

import java.util.HashMap;
import java.util.Map;

import org.omg.PortableServer.POA;

import Ice.Current;
import Ice.ObjectAdapter;
import MiddlewareTestbed.Item;
import MiddlewareTestbed.ItemAlreadyExists;
import MiddlewareTestbed.ItemBusy;
import MiddlewareTestbed.ItemNotExists;
import MiddlewareTestbed.ItemPrx;
import MiddlewareTestbed.ItemPrxHelper;

public class AFactoryI extends MiddlewareTestbed._AFactoryDisp {
	
	ObjectAdapter objectAdapter;

	public AFactoryI(ObjectAdapter objectAdapter) {
		this.objectAdapter = objectAdapter;
	}

	Map<String, ItemPrx> freeItems = new HashMap<String, ItemPrx>();
	Map<String, ItemPrx> busyItems = new HashMap<String, ItemPrx>();

	@Override
	public ItemPrx createItem(String name, String type, Current __current)
			throws ItemAlreadyExists {

		if (freeItems.keySet().contains(name))
			throw new ItemAlreadyExists();
		if (busyItems.keySet().contains(name))
			throw new ItemAlreadyExists();

		System.out.println("Dzialam");
		ItemPrx i = null;
		try {
			if (type.equals("ItemA")) {
				ItemAI iAimpl = new ItemAI(name);
				i = ItemPrxHelper.checkedCast(objectAdapter.addWithUUID(iAimpl));
				freeItems.put(name, i);
			} else if (type.equals("ItemB")) {
				ItemBI iBimpl = new ItemBI(name);
				i = ItemPrxHelper.checkedCast(objectAdapter.addWithUUID(iBimpl));
				freeItems.put(name, i);
			} else if (type.equals("ItemC")) {
				ItemCI iCimpl = new ItemCI(name);
				i = ItemPrxHelper.checkedCast(objectAdapter.addWithUUID(iCimpl));
				freeItems.put(name, i);
			} else {
				ItemI iimpl = new ItemI();
				i = ItemPrxHelper.checkedCast(objectAdapter.addWithUUID(iimpl));
				freeItems.put(name, i);
			}
		}catch(Exception e){
			System.out.println("sgaerh");
		}
		
		return i;
		
	}

	@Override
	public ItemPrx takeItem(String name, Current __current) throws ItemBusy,
			ItemNotExists {

		ItemPrx item = busyItems.get(name);
		if (item != null)
			throw new ItemBusy();

		item = freeItems.get(name);
		if (item == null)
			throw new ItemNotExists();

		busyItems.put(name, freeItems.remove(name));
		return item;
	}

	@Override
	public void releaseItem(String name, Current __current)
			throws ItemNotExists {

		if (!busyItems.keySet().contains(name))
			throw new ItemNotExists();
		else
			freeItems.put(name, busyItems.remove(name));

	}
}
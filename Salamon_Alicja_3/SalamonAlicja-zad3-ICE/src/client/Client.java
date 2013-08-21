package client;

import Ice.LongHolder;
import Ice.ObjectPrx;
import Ice.ShortHolder;
import MiddlewareTestbed.AFactoryPrx;
import MiddlewareTestbed.AFactoryPrxHelper;
import MiddlewareTestbed.ItemAPrx;
import MiddlewareTestbed.ItemAPrxHelper;
import MiddlewareTestbed.ItemAlreadyExists;
import MiddlewareTestbed.ItemBPrx;
import MiddlewareTestbed.ItemBPrxHelper;
import MiddlewareTestbed.ItemBusy;
import MiddlewareTestbed.ItemCPrx;
import MiddlewareTestbed.ItemCPrxHelper;
import MiddlewareTestbed.ItemNotExists;

public class Client extends Ice.Application {

	class ShutdownHook extends Thread {
		public void run() {
			try {
				communicator().destroy();
			} catch (Ice.LocalException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public int run(String[] args) {

		setInterruptHook(new ShutdownHook());

		AFactoryPrx aFactoryPrx = AFactoryPrxHelper.checkedCast(communicator()
				.propertyToProxy("Hello.Proxy"));

		try {
			
			//////////////////////////////////////////////////
			//              create items                    // 
			//////////////////////////////////////////////////
			ObjectPrx iaPrx = aFactoryPrx.createItem("first", "ItemA");
			ObjectPrx ibPrx = aFactoryPrx.createItem("second", "ItemB");
			ObjectPrx icPrx = aFactoryPrx.createItem("third", "ItemC");
			
			
			//////////////////////////////////////////////////
			//                 action A                     // 
			//////////////////////////////////////////////////
			ItemAPrx itemAPrx = ItemAPrxHelper.checkedCast(iaPrx);
			aFactoryPrx.takeItem("first");
			itemAPrx.actionA(6.0f,  new Ice.LongHolder(5));
			aFactoryPrx.releaseItem("first");
			System.out.println("ActionA - success");
			
			//////////////////////////////////////////////////
			//                 action B                     // 
			//////////////////////////////////////////////////
			ItemBPrx itemBPrx = ItemBPrxHelper.checkedCast(ibPrx);
			aFactoryPrx.takeItem("second");
			float result = itemBPrx.actionB("lol");
			aFactoryPrx.releaseItem("second");
			System.out.println("ActionB - success" + result);
			
			//////////////////////////////////////////////////
			//                 action C                     // 
			//////////////////////////////////////////////////
			ItemCPrx itemCPrx = ItemCPrxHelper.checkedCast(icPrx);
			aFactoryPrx.takeItem("third");
			short s= 6;
			float result1 = itemCPrx.actionC(3, new LongHolder(5), new ShortHolder(s));
			aFactoryPrx.releaseItem("third");
			System.out.println("ActionC - success" + result1);
			
		} catch (ItemAlreadyExists e) {
			e.printStackTrace();
		} catch (ItemBusy e) {
			e.printStackTrace();
		} catch (ItemNotExists e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static void main(String[] args) {
		Client app = new Client();
		int status = app.main("Client", args, "config.client");
		System.exit(status);
	}
}
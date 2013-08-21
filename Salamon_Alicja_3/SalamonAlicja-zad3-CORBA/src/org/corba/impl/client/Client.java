package org.corba.impl.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.corba.generated.AFactory;
import org.corba.generated.AFactoryHelper;
import org.corba.generated.Item;
import org.corba.generated.ItemA;
import org.corba.generated.ItemAHelper;
import org.corba.generated.ItemAlreadyExists;
import org.corba.generated.ItemBusy;
import org.corba.generated.ItemNotExists;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.DATA_CONVERSION;
import org.omg.CORBA.MARSHAL;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Client {

	/**
	 * @param args
	 * @throws InvalidName
	 * @throws CannotProceed
	 * @throws NotFound
	 * @throws IOException 
	 */

	public static void main(String[] args) throws NotFound, CannotProceed,
			InvalidName, IOException {
		
		InputStream stream = new FileInputStream("corbaClient.properties");
		Properties p = new Properties();
		p.load(stream);
		stream.close();
		
		//////////////////////////////////////////////////
		//                 init                         // 
		//////////////////////////////////////////////////
		ORB orb = ORB.init(args, null);
		org.omg.CORBA.Object nsRef = null;
		nsRef = orb
				.string_to_object(p.getProperty("IOR"));

		NamingContextExt ncRef = NamingContextExtHelper.narrow(nsRef);
		org.omg.CORBA.Object objRef = ncRef.resolve_str(p.getProperty("FactoryName"));

		try {
			//////////////////////////////////////////////////
			//              create items                    // 
			//////////////////////////////////////////////////
			AFactory aFactory = AFactoryHelper.narrow(objRef);
			aFactory.create_item("first", "ItemA");
			aFactory.create_item("second", "ItemB");
			aFactory.create_item("third", "ItemC");

			//////////////////////////////////////////////////
			//                 action A                     // 
			//////////////////////////////////////////////////
			
			Item item11 = aFactory.take_item("first");
			if (item11._is_a("IDL:MiddlewareTestbed/ItemA:1.0")) {

				org.omg.CORBA.Request r = item11._request("actionA");
				r.add_in_arg().insert_float(77);
				r.add_out_arg().insert_long(888);
				r.set_return_type(orb
						.get_primitive_tc(org.omg.CORBA.TCKind.tk_void));

				r.invoke();

				if (r.env().exception() == null) {
					System.out.println("Action A -- success\n");
				}
			}
			aFactory.release_item("first");
			//////////////////////////////////////////////////
			//                 action B                     // 
			//////////////////////////////////////////////////
			Item item22 = aFactory.take_item("second");
			if (item22._is_a("IDL:MiddlewareTestbed/ItemB:1.0")) {

				org.omg.CORBA.Request r = item22._request("actionB");
				r.add_in_arg().insert_string("bbbBBB");
				r.set_return_type(orb
						.get_primitive_tc(org.omg.CORBA.TCKind.tk_float));

				r.invoke();

				if (r.env().exception() == null) {
					System.out.println("Action B -- success " + r.return_value().extract_float() + "\n");
				}
			}	
			aFactory.release_item("second");
			//////////////////////////////////////////////////
			//                 action C                     // 
			//////////////////////////////////////////////////
			Item item33 = aFactory.take_item("third");
			if (item33._is_a("IDL:MiddlewareTestbed/ItemC:1.0")) {

				org.omg.CORBA.Request r = item33._request("actionC");
				r.add_inout_arg().insert_long(555);
				short s = 55;
				r.add_out_arg().insert_short(s);
				r.set_return_type(orb
						.get_primitive_tc(org.omg.CORBA.TCKind.tk_void));

				r.invoke();

				if (r.env().exception() == null) {
					System.out.println("Action C -- success");
				}
			}	
			aFactory.release_item("third");
			
		} catch (ItemAlreadyExists e) {
			e.printStackTrace();
		} catch (ItemNotExists e) {
			e.printStackTrace();
		} catch (ItemBusy e) {
			e.printStackTrace();
		}

	}

}

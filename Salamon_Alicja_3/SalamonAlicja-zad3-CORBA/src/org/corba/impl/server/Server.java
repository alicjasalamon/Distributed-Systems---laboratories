package org.corba.impl.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class Server {

	/**
	 * @param args
	 * @throws AdapterInactive 
	 * @throws InvalidName 
	 * @throws org.omg.CosNaming.NamingContextPackage.InvalidName 
	 * @throws WrongPolicy 
	 * @throws ServantNotActive 
	 * @throws ServantAlreadyActive 
	 * @throws CannotProceed 
	 * @throws NotFound 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws AdapterInactive, InvalidName, org.omg.CosNaming.NamingContextPackage.InvalidName, ServantNotActive, WrongPolicy, ServantAlreadyActive, NotFound, CannotProceed, IOException {
	
		InputStream stream = new FileInputStream("corbaServer.properties");
		Properties p = new Properties();
		p.load(stream);
		stream.close();
		ORB orb = ORB.init( args, null );
		
		POA rootpoa = POAHelper.narrow(orb.resolve_initial_references( "RootPOA" ));
		rootpoa.the_POAManager().activate();
		
		AFactoryImpl i1Impl = new AFactoryImpl(rootpoa);
		rootpoa.activate_object(i1Impl);
		
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(i1Impl);

		org.omg.CORBA.Object nsRef;
		//to tez arg
		nsRef = orb.string_to_object(p.getProperty("IOR"));

		NamingContextExt ncRef = NamingContextExtHelper.narrow( nsRef );
		NameComponent path[] = ncRef.to_name(p.getProperty("FactoryName"));

		ncRef.rebind(path, ref);
		
		orb.run();

	}

}

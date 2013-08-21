package server;

import Ice.Current;

public class ItemBI extends MiddlewareTestbed._ItemBDisp{

	String name;

	public ItemBI(String name) {
		this.name = name;
	}
	
	@Override
	public float actionB(String a, Current __current) {
		System.out.println("Action B!");
		return 222222;
	}

	@Override
	public long getItemAge(Current __current) {
		return 2;
	}

	@Override
	public String getName(Current __current) {
		return name;
	}

}

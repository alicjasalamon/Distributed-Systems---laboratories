package server;

import Ice.Current;
import Ice.LongHolder;
import Ice.ShortHolder;

public class ItemCI extends MiddlewareTestbed._ItemCDisp{

	String name;

	public ItemCI(String name) {
		this.name = name;
	}
	

	@Override
	public long getItemAge(Current __current) {
		return 3;
	}

	@Override
	public String getName(Current __current) {
		return name;
	}


	@Override
	public float actionC(long a1, LongHolder a2, ShortHolder b,
			Current __current) {
		System.out.println("action C");
		a2.value = 333;
		b.value = 33;
		return 333;
	}
	

}

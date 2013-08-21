package server;

import Ice.Current;
import Ice.LongHolder;

public class ItemAI extends MiddlewareTestbed._ItemADisp{

	String name;

	public ItemAI(String name) {
		this.name = name;
	}

	@Override
	public void actionA(float a, LongHolder b, Current __current) {
		b.value=1;
		System.out.println("action A!");
		
	}

	@Override
	public long getItemAge(Current __current) {
		return 1;
	}

	@Override
	public String getName(Current __current) {
		return name;
	}

}

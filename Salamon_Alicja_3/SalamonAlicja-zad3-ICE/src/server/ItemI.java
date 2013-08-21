package server;

import Ice.Current;

public class ItemI extends MiddlewareTestbed._ItemDisp {

	@Override
	public long getItemAge(Current __current) {
		return 0;
	}

	@Override
	public String getName(Current __current) {
		return "justItem";
	}
	

}

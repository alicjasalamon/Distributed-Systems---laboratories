package server;


import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import common.ITicTacToeRoom;

public class Server {
	
	static ITicTacToeRoom nbImpl;

	public static void main(String[] args) {
        
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		
		try {
			
			if(args.length != 2){
				System.out.println("usage: <host> <port>");
				return;
			}
			
			nbImpl = new TicTacToeRoom();
			ITicTacToeRoom noteBoard = (ITicTacToeRoom) UnicastRemoteObject
					.exportObject(nbImpl, 0);
			Naming.rebind("rmi://" +  args[0]+ ":" + Integer.parseInt(args[1])+ "/game", noteBoard);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

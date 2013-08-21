package client;

import java.rmi.Naming;
import java.util.Scanner;


import common.IBoardListener;
import common.ITicTacToeRoom;
import common.UserRejectedException;

public class Client {

	public static void main(String[] args) {
		
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

		if(args.length != 3){
			System.out.println("uage: <host> <port> <nick>");
			return;
		}
		
		try {
			final String user = args[2];
			Object o = Naming.lookup("rmi://" +  args[0]+ ":" + Integer.parseInt(args[1])+ "/game");
			
			final ITicTacToeRoom nb = (ITicTacToeRoom) o;
		
			//zarejestruj sie
			nb.register(user);
			
			//wybierz tryb gry			
			System.out.println("podaj tryb gry\n\t1 - z komputerem \n\t2 - z osoba");
			Scanner scanner = new Scanner(System.in);
			int tekst;
			

			do{
				tekst = scanner.nextInt();
			} while (tekst!=1 && tekst!=2) ;
				
			
			IBoardListener l = new BoardListener();
			nb.chooseGame(user, l, tekst);
			
			 Runtime.getRuntime().addShutdownHook(new Thread(){
				 @Override
				public void run() {
					try {
						nb.unregister(user);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			 });
			
		}catch (UserRejectedException ex) {
			System.out.println("nick jest juz zajety");
			//ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

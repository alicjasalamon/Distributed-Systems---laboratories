package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import common.IBoard;
import common.IBoardListener;

public class BoardListener extends UnicastRemoteObject implements IBoardListener {

	IBoard b;
	Scanner scanner;
	char sign;
	
	private static final long serialVersionUID = 1L;

	protected BoardListener() throws RemoteException {
		super();
		scanner = new Scanner(System.in);
	}

	@Override
	public void nextMove() throws RemoteException {
		System.out.println("nadszedl czas na Twoj ruch! \n" +
				"podaj 2 cyfry - kazda zatwierdz enterem");
		int x,y;
		boolean ok = true;
		do{
			x = scanner.nextInt();
			y = scanner.nextInt();
			
			ok = isOK(x-1, y-1);
			if (!ok)
				System.out.println("pomylka! wpisz jeszcze raz");
		}while(!ok);
		
		b.click(x-1,y-1, sign);
		
	}
	
	@Override
	public boolean isOK(int x,int y) throws RemoteException{
		boolean ok = true;
		if (x>2 || x<0) ok = false;
		else if (y>2 || y<0) ok = false;
		else if (b.getBoard()[x][y]!=' ')ok = false;
			
		return ok;
	}
	
	@Override
	public void onNewText(String text) throws RemoteException {
		System.out.println(text);
	}

	@Override
	public void gameOver(String text) throws RemoteException {
		System.out.println(text);
		UnicastRemoteObject.unexportObject(this, true);
	}
	
	@Override
	public void setBoard(IBoard b) throws RemoteException {
		this.b = b;
	}
	
	@Override
	public void setSign(char sign) {
		this.sign = sign;
	}

}

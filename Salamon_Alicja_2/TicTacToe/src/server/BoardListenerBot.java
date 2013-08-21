package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import common.IBoard;
import common.IBoardListener;

public class BoardListenerBot extends UnicastRemoteObject implements
		IBoardListener {

	IBoard b;
	char sign = 'x';

	private static final long serialVersionUID = 1L;

	public BoardListenerBot(IBoard board) throws RemoteException {
		super();
		b = board;
	}

	@Override
	public void nextMove() throws RemoteException {

		// tu wylosuj jakies wolne miejsce
		int x, y;
		Random r = new Random();

		do {
			x = r.nextInt(3);
			y = r.nextInt(3);
		} while (!isOK(x, y));

		// i kliknij tam
		b.click(x, y, sign);
	}
	
	@Override
	public void onNewText(String text) throws RemoteException {
	}

	@Override
	public boolean isOK(int x, int y) throws RemoteException {
		if (b.getBoard()[x][y]==' ') return true;
		return false;
	}

	@Override
	public void setBoard(IBoard b) throws RemoteException {
		this.b = b;
	}

	@Override
	public void setSign(char sign) {
		this.sign = sign;
	}

	@Override
	public void gameOver(String text) throws RemoteException {
	}

}

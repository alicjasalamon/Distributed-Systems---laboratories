package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBoardListener extends Remote {

	public void onNewText(String text) throws RemoteException;

	public void nextMove() throws RemoteException;

	public void setBoard(IBoard b) throws RemoteException;

	void setSign(char sign) throws RemoteException;

	boolean isOK(int x, int y) throws RemoteException;
	
	void gameOver(String text) throws RemoteException;
}

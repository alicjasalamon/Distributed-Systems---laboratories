package common;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBoard extends Remote {

	String string() throws RemoteException;

	void click(int x, int y, char sign) throws RemoteException;

	void setPassive(IBoardListener l) throws RemoteException;

	void setActive(IBoardListener l) throws RemoteException;

	void setPlayersNumber(int n) throws RemoteException;

	IBoardListener getPassive() throws RemoteException;

	IBoardListener getActive() throws RemoteException;

	int getPlayersNumber() throws RemoteException;

	char[][] getBoard() throws RemoteException;

}

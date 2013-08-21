package common;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ITicTacToeRoom extends Remote {

	public void register(String user) throws RemoteException, UserRejectedException;

	public void unregister(String user) throws RemoteException, UserRejectedException;

	public IBoard chooseGame(String user, IBoardListener l, int tekst)
			throws RemoteException;

}

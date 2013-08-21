package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import common.IBoard;
import common.IBoardListener;
import common.ITicTacToeRoom;
import common.UserRejectedException;

public class TicTacToeRoom implements ITicTacToeRoom {

	List<String> registered;
	Map<String, IBoard> users;

	public TicTacToeRoom() {
		registered = new ArrayList<String>();
		users = new HashMap<String, IBoard>();
	}

	@Override
	public void register(String user) throws RemoteException, UserRejectedException {

		if (registered.contains(user)) {
			throw new UserRejectedException("taki uzytkownik jest juz zerejestrowany");
		}
		registered.add(user);
		System.out.println("rejestracja");
	}

	@Override
	public void unregister(String user) throws RemoteException, UserRejectedException {

		if (!registered.contains(user)) {
			throw new UserRejectedException("nie mozna odrejestrowac tego uzytkownika");
		}
		users.remove(user);
	}

	@Override
	public IBoard chooseGame(String user, IBoardListener l, int way)
			throws RemoteException {

		System.out.println("gra w trybie" + way);
		if (way == 1) {

			IBoard board = new Board(l);
			IBoardListener botl = new BoardListenerBot(board);

			l.setBoard(board);
			l.setSign('o');
			botl.setBoard(board);

			board.setPassive(botl);
			board.setActive(l);
			board.setPlayersNumber(2);
			users.put(user, board);

			users.get(user).getActive().nextMove();
			return users.get(user);
		}

		else if (way == 2) {

			for (IBoard board : users.values())
				if (board.getPlayersNumber() == 1) {

					l.setBoard(board);
					l.setSign('x');
					board.setActive(l);
					board.setPlayersNumber(2);
					users.put(user, board);
					users.get(user).getActive().nextMove();
					return board;
				}

			IBoard board = new Board(l);
			board.setPlayersNumber(1);
			l.setBoard(board);
			l.setSign('o');
			users.put(user, board);
			users.get(user).getPassive().onNewText("czekaj na partnera");
			return board;

		}
		return null;

	}

}

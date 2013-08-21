package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.IBoard;
import common.IBoardListener;

public class Board extends UnicastRemoteObject implements IBoard {

	private static final long serialVersionUID = 1L;
	char[][] board;
	int playerNumber = 0;
	IBoardListener active = null, passive = null;

	public Board(IBoardListener l1) throws RemoteException {
		// first user, u r passive now
		this.passive = l1;

		board = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = ' ';

	}

	@Override
	public void click(int x, int y, char sign) throws RemoteException {

		board[x][y] = sign;

		// sprawdz czy wygrana lub koniec gry!
		if (youWin()) {
			active.gameOver("wygrales, koniec gry\nGRATULACJE");
			passive.gameOver("przegrales, koniec gry\nSPROBUJ JESZCZE RAZ");
			return;
		}

		if (isFull()) {
			active.gameOver("koniec gry\nREMIS");
			passive.gameOver("koniec gry\nREMIS");
			return;
		}
		
		// wyswietl uzytkownikom plansze
		passive.onNewText(string());
		active.onNewText(string()
				+ "\n\nto byl twoj ruch\nteraz czekaj na ruch przeciwnika");
		
		// switch listeners
		IBoardListener help = passive;
		passive = active;
		active = help;

		// teraz nowy passive moze sobie kliknac
		active.nextMove();

	}

	boolean isFull() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == ' ')
					return false;
		return true;
	}
	
	boolean youWin() {
		
		for (int i = 0; i < 3; i++) {
			if (board[i][0]=='o' && board[i][1]=='o' && board[i][2]=='o') return true;
		}
		for (int i = 0; i < 3; i++) {
			if (board[0][i]=='o' && board[1][i]=='o' && board[2][i]=='o') return true;
		}
		if (board[0][0]=='o' && board[1][1]=='o' && board[2][2]=='o') return true;
		if (board[0][2]=='o' && board[1][1]=='o' && board[2][0]=='o') return true;
		
		for (int i = 0; i < 3; i++) {
			if (board[i][0]=='x' && board[i][1]=='x' && board[i][2]=='x') return true;
		}
		for (int i = 0; i < 3; i++) {
			if (board[0][i]=='x' && board[1][i]=='x' && board[2][i]=='x') return true;
		}
		if (board[0][0]=='x' && board[1][1]=='x' && board[2][2]=='x') return true;
		if (board[0][2]=='x' && board[1][1]=='x' && board[2][0]=='x') return true;
		
		return false;
	}
	
	public String string() {
		return "\n\t " + board[0][0] + " | " + board[0][1] + " | "
				+ board[0][2] + "\n\t---+---+---" + "\n\t " + board[1][0]
				+ " | " + board[1][1] + " | " + board[1][2] + "\n\t---+---+---"
				+ "\n\t " + board[2][0] + " | " + board[2][1] + " | "
				+ board[2][2];
	}

	@Override
	public void setPlayersNumber(int n) throws RemoteException {
		playerNumber = n;
	}

	@Override
	public IBoardListener getPassive() throws RemoteException {
		return passive;
	}

	@Override
	public IBoardListener getActive() throws RemoteException {
		return active;
	}

	@Override
	public int getPlayersNumber() throws RemoteException {
		return playerNumber;
	}

	@Override
	public char[][] getBoard() throws RemoteException {
		return board;
	}
	
	public void setActive(IBoardListener active) {
		this.active = active;
	}

	public void setPassive(IBoardListener passive) {
		this.passive = passive;
	}

}

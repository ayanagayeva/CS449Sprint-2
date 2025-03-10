package SOSGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

abstract class sosGame {
	protected int gameBoardSize;
	protected char[][] gameBoard;
	protected boolean isRedTurn = true;
	
	public sosGame(int size) {
		this.gameBoardSize = size;
		this.gameBoard = new char[size][size];
	}
	// method tracking user's move & placement on grid
	public abstract void userMove(int row, int col, char letter);
}

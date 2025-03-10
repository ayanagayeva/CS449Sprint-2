package SOSGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class simpleGame extends sosGame {
	public simpleGame(int gameBoardSize) {
		super(gameBoardSize);
	}
	
	public void userMove(int row, int col, char letter) {
		if(gameBoard[row][col] == '\0') {
			gameBoard[row][col] = letter;
			isRedTurn = !isRedTurn;
		}
	}
}

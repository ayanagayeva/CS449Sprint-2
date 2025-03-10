package SOSGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class generalGame extends sosGame {
	public generalGame(int gameBoardSize) {
		super(gameBoardSize);
	}
	
	public void userMove(int row, int col, char letter) {
		if(gameBoard[row][col] == '\0') {
			gameBoard[row][col] = letter;
			isRedTurn = !isRedTurn;
		}
	}

}


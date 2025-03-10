package SOSGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class sosGameTest {
	private simpleGame simpGame;
	private generalGame genGame;
	
	void setUp() {
		simpGame = new simpleGame(5);
		genGame = new generalGame(5);
	}
	
	void boardSetUp() {
		assertEquals(5, simpGame.gameBoardSize);
		assertEquals(5, genGame.gameBoardSize);
	}
	
	void moveSimpGame() {
		simpGame.userMove(2,  2, 'S');
		assertEquals('S', simpGame.gameBoard[2][2]);
		assertFalse(simpGame.isRedTurn);
	}
	
	void moveGenGame() {
		genGame.userMove(1,  1, 'O');
		assertEquals('O', simpGame.gameBoard[1][1]);
		assertFalse(simpGame.isRedTurn);
	}
	
	void switchTurns() {
		simpGame.userMove(0, 0, 'S');
		assertFalse(simpGame.isRedTurn);
		simpGame.userMove(1, 1, 'O');
		assertTrue(simpGame.isRedTurn);
	}
	/*
	// claude AI generated unit tests
	@Test
	void testDiagonalSOSDetection() {
	    // Place 'S' at (0,0) for red player
	    simpGame.userMove(0, 0, 'S');
	    
	    // Place 'O' at (1,1) for blue player
	    simpGame.userMove(1, 1, 'O');
	    
	    // Place 'S' at (2,2) for red player
	    simpGame.userMove(2, 2, 'S');
	    
	    // Check the score directly to see if a diagonal SOS was detected
	    // Assuming the checkSOS logic is already implemented within userMove
	    assertEquals(1, simpGame.redScore);
	    assertEquals(0, simpGame.blueScore);
	    
	    // Also verify the board state
	    assertEquals('S', simpGame.gameBoard[0][0]);
	    assertEquals('O', simpGame.gameBoard[1][1]);
	    assertEquals('S', simpGame.gameBoard[2][2]);
	}

	@Test
	void testInvalidBoardPosition() {
	    // Test moves outside the board boundaries
	    boolean validMove = simpGame.userMove(5, 5, 'S'); // Assuming 5 is outside the 5x5 board
	    
	    // Verify the move was rejected
	    assertFalse(validMove);
	    
	    // Ensure the turn has not changed since the move was invalid
	    assertTrue(simpGame.isRedTurn); // Still red's turn
	    
	    // Test negative indices
	    validMove = simpGame.userMove(-1, 2, 'O');
	    assertFalse(validMove);
	    assertTrue(simpGame.isRedTurn); // Still red's turn
	}
*/
}

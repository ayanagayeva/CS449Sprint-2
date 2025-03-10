package SOSGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class sosGameGUI {
	private JFrame frame;
	private JPanel sosGrid, sosSettings, sosPositioning;
	private JButton[][] buttons;
	private JLabel currentPlayer, timerLabel;
	private sosGame game;

	private char bluePlayerChoice = 'S', redPlayerChoice = 'S';
	private Timer timer;
	private int elapsedTime;
	
	public sosGameGUI() {
		frame = new JFrame("SOS Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(500,500);
		
		gameButtons();
		setupBoard(4);
		startTimer();
		frame.setVisible(true);
	}
	
	private void gameButtons() {
		sosSettings = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		JPanel leftPanel = new JPanel(new GridLayout(3, 1));
		JPanel rightPanel = new JPanel(new GridLayout(3, 1));
		JLabel modeLabel = new JLabel("SOS");
		JRadioButton simpleGameButton = new JRadioButton("Simple game");
		JRadioButton generalGameButton = new JRadioButton("General game");
		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(simpleGameButton);
		modeGroup.add(generalGameButton);

		JLabel sizeLabel = new JLabel("Board size ");
		JTextField sizeField = new JTextField("4", 2);
		JButton startButton = new JButton("Start Game");
		
		JLabel blueLabel = new JLabel("Blue player");
		JRadioButton blueS = new JRadioButton("S", true);
		JRadioButton blueO = new JRadioButton("O");
		ButtonGroup blueGroup = new ButtonGroup();
		blueGroup.add(blueS);
		blueGroup.add(blueO);
		
		JLabel redLabel = new JLabel("Red player");
		JRadioButton redS = new JRadioButton("S", true);
		JRadioButton redO = new JRadioButton("O");
		ButtonGroup redGroup = new ButtonGroup();
		redGroup.add(redS);
		redGroup.add(redO);
		
		currentPlayer = new JLabel("Current turn: Blue", SwingConstants.CENTER);
		timerLabel = new JLabel("Time: O sec", SwingConstants.CENTER);
		
		startButton.addActionListener(e -> {
			int size = Integer.parseInt(sizeField.getText());
			if(size < 3 || size > 8) {
				JOptionPane.showMessageDialog(frame, "Size must be between 3 & 8");
				return;
			}
			if(simpleGameButton.isSelected()) game = new simpleGame(size);
			else game = new generalGame(size);
			setupBoard(size);
		});
		
		blueS.addActionListener(e -> bluePlayerChoice ='S');
		blueO.addActionListener(e -> bluePlayerChoice ='O');
		redS.addActionListener(e -> redPlayerChoice ='S');
		redO.addActionListener(e -> redPlayerChoice ='O');

		topPanel.add(modeLabel);
		topPanel.add(simpleGameButton);
		topPanel.add(generalGameButton);
		topPanel.add(timerLabel);
		topPanel.add(sizeLabel);
		topPanel.add(sizeField);
		topPanel.add(startButton);
		
		leftPanel.add(blueLabel);
		leftPanel.add(blueS);
		leftPanel.add(blueO);
		
		rightPanel.add(redLabel);
		rightPanel.add(redS);
		rightPanel.add(redO);
		
		sosSettings.add(topPanel, BorderLayout.NORTH);
		sosSettings.add(leftPanel, BorderLayout.WEST);
		sosSettings.add(rightPanel, BorderLayout.EAST);

		frame.add(sosSettings, BorderLayout.NORTH);
		frame.add(currentPlayer, BorderLayout.SOUTH);
	}
	
	private void setupBoard(int boardSize) {
		if(sosPositioning != null) {
			frame.remove(sosPositioning);
		}
		sosGrid = new JPanel(new GridLayout(boardSize, boardSize, 10, 10));
		buttons = new JButton[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(50, 50));
				buttons[i][j].setFont(new Font("Calibri", Font.BOLD, 14));
				final int row = i, col = j;
				buttons[i][j].addActionListener(e -> handleMove(row, col));
				sosGrid.add(buttons[i][j]);
			}
		}
		
		sosPositioning = new JPanel(new GridBagLayout());
		sosPositioning.add(sosGrid);
		
		frame.add(sosPositioning, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}
	
	private void handleMove(int row, int col) {
		char userMove;
		if(game.isRedTurn) {
			userMove = bluePlayerChoice;
		} else{
			userMove = redPlayerChoice;
		}
		game.userMove(row, col, userMove);
		buttons[row][col].setText(String.valueOf(userMove));
		buttons[row][col].setEnabled(false);
		updateTurn();
	}
	
	private void updateTurn() {
		if(game.isRedTurn) {
			currentPlayer.setText("Current turn: Red");
		} else {
			currentPlayer.setText("Current turn: Blue");
		}
		elapsedTime = 0;
	}
	
	private void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				elapsedTime++;
				timerLabel.setText("Time: " + elapsedTime + " sec");
			}
		}, 1000, 1000);
	}
	
	public static void main(String[] args) {
		new sosGameGUI();
	}
}

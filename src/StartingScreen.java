import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartingScreen extends JFrame{
	
	private static final long serialVersionUID = -3454607811308753375L;
	private JTextField txtRows;
	private JTextField txtCols;
	private JTextField txtSize;
	private JLabel lblHeading;
	private JLabel lblRows;
	private JLabel lblCols;
	private JLabel lblSize;
	private JPanel inputPanel;
	private JButton btnCreate;
	
	private Gui gui;
	private Game game;	
	
	public StartingScreen() {
		this.setLayout(new GridLayout(3, 1, 10 , 30));
		lblHeading = new JLabel("Game Of Life");
		lblRows = new JLabel("Rows: ");
		lblCols = new JLabel("Cols: ");
		lblSize = new JLabel("Size: ");
		txtRows = new JTextField(10);
		txtCols = new JTextField(10);
		txtSize = new JTextField(10);
		btnCreate = new JButton("Start Game");
		inputPanel = new JPanel();
		
		inputPanel.setLayout(new GridLayout(3, 2, 30, 10));
		inputPanel.add(lblRows);
		inputPanel.add(txtRows);
		inputPanel.add(lblCols);
		inputPanel.add(txtCols);
		inputPanel.add(lblSize);
		inputPanel.add(txtSize);
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				startNewGame();
				setVisible(false);
			}
		});
		
		this.add(lblHeading);
		this.add(inputPanel);
		this.add(btnCreate);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	private void startNewGame() {
		
		if(txtRows.getText().matches("-?\\d+") && txtCols.getText().matches("-?\\d+") && txtSize.getText().matches("-?\\d+")) {
			int rows = Integer.parseInt(txtRows.getText());
			int cols = Integer.parseInt(txtCols.getText());
			int size = Integer.parseInt(txtSize.getText());
			gui = new Gui(rows, cols, size);
			game = new Game(cols, rows, gui);
			gui.setGame(game);
			gui.setScreen(this);
			gui.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(this, "Inputs must be integers");
		}
	}
}


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame{
	
	private static final long serialVersionUID = 5671668911100310669L;
	private StartingScreen screen;
	private Game game;
	private JButton button;
	
	public Gui(int rows, int cols, int cellSize) {
		
		JPanel contentPane = new CellPanel(rows,cols,cellSize, this);
		button = new JButton("next step");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.iterate();
			}
		});
		
		this.setTitle("Game Of Life");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setContentPane(contentPane);
		this.add(button);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void setScreen(StartingScreen screen) {
		this.screen = screen;
	};
	
	public void updateCell(int row, int col) {
		game.updateCell(row, col);
	}
	
	public void updateDisplay() {
		this.getContentPane().repaint();
	}
	
	public boolean getState(int row, int col) {
		return game.getState(row, col);
	}
	
	@Override
	public void hide() {
		screen.setVisible(true);
		super.hide();
	}
}

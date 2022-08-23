import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class CellPanel extends JPanel{
	
	private Gui gui;
	private int rows;
	private int cols;
	private int sizeCell;
	
	public CellPanel(int rows, int cols, int sizeCells, Gui gui) {
		this.gui = gui;
		this.rows = rows;
		this.cols = cols;
		this.sizeCell = sizeCells;
		this.setPreferredSize(new Dimension(cols*sizeCells, rows*sizeCells));
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				updateCell(e.getY()/sizeCells, e.getX()/sizeCells);
            }
		});
	}
	
	public void updateCell(int row, int col) {
		gui.updateCell(row, col);
		revalidate();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(gui.getState(i, j)) g.setColor(Color.black);
				else g.setColor(Color.white);
				g.fillRect(j*sizeCell, i*sizeCell, sizeCell, sizeCell);
			}
		}
	}
}

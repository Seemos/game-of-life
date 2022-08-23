
public class Game {
	
	int width;
	int height;
	boolean[][] cells;
	Gui g;
	
	public Game(int width, int height, Gui g) {
		this.g = g;
		this.width = width;
		this.height = height;
		cells = new boolean[height][width];
	}
	
	public void run(int iterations) {
		for(int i = 0; i < iterations; i++) {
			iterate();
			printCells();
		}
	}
	
	public void printCells() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(cells[i][j]) System.out.print("#");
				else System.out.print(" ");
			}
			System.out.println();
		}
		g.updateDisplay();
	}
	
	public void iterate() {
		System.out.println("iterate called");
		boolean[][] replacement = new boolean[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int neighbours = countNeighbours(i, j);
				if(neighbours == 3) replacement[i][j] = true;
				else if(neighbours == 2 && cells[i][j]) replacement[i][j] = true;
			}
		}
		cells = replacement;
		printCells();
	}
	
	private int countNeighbours(int row, int col) {
		int count = 0;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int idxR = row+i < 0 ? height + (row+i) : (row+i)%height;
				int idxC = col+j < 0 ? width + (col+j) : (col+j)%width;
				if(cells[idxR][idxC]) count++;
			}
		}
		if(cells[row][col]) count--;
		return count;
	}
	
	public boolean getState(int i, int j) {
		return cells[i][j];
	}
	
	public void setState(int i, int j, boolean isAlive) {
		cells[i][j] = isAlive;
	}
	
	public void updateCell(int i, int j) {
		cells[i][j] = !cells[i][j];
	}
}

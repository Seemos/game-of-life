
public class Game {
	
	int width;
	int height;
	boolean isRunning;
	boolean[][] cells;
	Gui g;
	Thread cellUpdater;
	
	public Game(int width, int height, Gui g) {
		this.g = g;
		this.width = width;
		this.height = height;
		cells = new boolean[height][width];
	}
	
	public void startStop() {
		if(isRunning) stop();
		else start();
	}
	
	public void start() {
		cellUpdater = new Thread(new CellUpder(this, 200));
		cellUpdater.setDaemon(true);
		cellUpdater.start();
		isRunning = true;
	}
	
	public void stop() {
		if(isRunning) cellUpdater.interrupt();
		isRunning = false;
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
				if(neighbours == 3 || (neighbours == 2 && cells[i][j])) {
					replacement[i][j] = true;
				}
			}
		}
		cells = replacement;
		printCells();
	}
	
	private int countNeighbours(int row, int col) {
		int count = 0;
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				int idxR = (row+height+i)%height;
				int idxC = (col+width+j)%width;
				if(cells[idxR][idxC]) count++;
			}
		}
		if(cells[row][col]) count--;
		return count;
	}
	
	public synchronized boolean getState(int i, int j) {
		return cells[i][j];
	}
	
	public void setState(int i, int j, boolean isAlive) {
		cells[i][j] = isAlive;
	}
	
	public void updateCell(int i, int j) {
		cells[i][j] = !cells[i][j];
	}
}

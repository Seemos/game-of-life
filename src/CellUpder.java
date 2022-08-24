
public class CellUpder implements Runnable{
	
	private boolean shouldUpdate = true;
	private long delay;
	private Game game;
	
	public CellUpder(Game game, long delay) {
		this.delay = delay;
		this.game = game;
	}

	@Override
	public void run() {
		while(shouldUpdate){
			try {
				Thread.sleep(delay);
				game.iterate();
			} catch (InterruptedException e) {
				shouldUpdate = false;
			}
		}
	}

}

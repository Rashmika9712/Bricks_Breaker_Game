
public abstract class GameEntity {
	protected int x;
	protected int y;
	protected int gameW;
	protected int gameH;
	protected int w;
	protected int h;
	protected int speed;
	
	protected int dx;
	
	public GameEntity(int gameW, int gameH, int startX, int startY, int w, int h, int speed) {
		this.gameW = gameW;
		this.gameH = gameH;
		this.x = startX;
		this.y = startY;		
		this.w = w;
		this.h = h;
		this.speed = speed;
	}

	public abstract void update();
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
}

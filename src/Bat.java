public class Bat extends GameEntity{		

	public Bat(int gameW, int gameH,  int startX, int startY, int w, int h, int speed) {
		super(gameW, gameH, startX, startY, w, h, speed);			
	}
	
	public void setLeft() {
		dx = -speed;
	}
	
	public void setRight() {
		dx = speed;
	}
		
	@Override
	public void update() {
		x = x + dx;
		
		if(x <= 0) {
			x =0 ;
		}
		if(x >= gameW - w) {
			x = gameW - w;
		}
		dx = 0;
	}
}
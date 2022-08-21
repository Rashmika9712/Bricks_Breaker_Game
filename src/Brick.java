import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Brick{
	private int brickW;
	private int brickH;
	private int map[][]; 
	
	public Brick(int row, int col) {
		map = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = 1;
			}			
		}
		
		this.brickW = 370/col;
		this.brickH = 100/row;		
	}
	
	public int getW() {
		return this.brickW;
	}
	
	public int getH() {
		return this.brickH;
	}
	
	public void setBrick(int value, int row, int col) {
		map[row][col] = value;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0; i<map.length;i++) {
			for(int j=0; j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.GREEN);
					g.fillRect(j*this.brickW + 15, i*this.brickH + 15, this.brickW, this.brickH);
					
					g.setColor(Color.BLACK);
					g.setStroke(new BasicStroke(13));
					g.drawRect(j*this.brickW + 15, i*this.brickH + 15, this.brickW, this.brickH);
				}
			}
		}
	}
	
	public void destroy() {
		System.out.println("Brick was destroyed");
	}
}

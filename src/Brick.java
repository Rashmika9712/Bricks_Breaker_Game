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
		
		this.brickW = 400/col;
		this.brickH = 80/row;
		
	}
	
	public int getWidth() {
		return brickW;
	}
	
	public int getHeight() {
		return brickH;
	}
	
	public void setBrick(int value, int row, int col) {
		map[row][col] = value;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0; i<map.length;i++) {
			for(int j=0; j<map[0].length;j++) {
				if(map[i][j]>0) {
					g.setColor(Color.GREEN);
					g.fillRect(j*this.brickW, i*this.brickH, this.brickW, this.brickH);
				}
			}
		}
	}
}

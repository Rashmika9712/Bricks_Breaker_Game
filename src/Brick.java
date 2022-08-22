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
	
}

public class Vector2 
{
	private int x;
	public void setX(int x){this.x = x;}
	public int getX(){return x;}
	
	private int y;
	public void setY(int y){this.y = y;}
	public int getY(){return y;}
	
	public Vector2(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	boolean equals(Vector2 other)
	{
		return this.x == other.x && this.y == other.y;
	}
}

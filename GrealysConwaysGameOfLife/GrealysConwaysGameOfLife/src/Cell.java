
public class Cell
{
	static public enum Direction
	{
		NORTHWEST,NORTH,NORTHEAST,WEST,EAST,SOUTHWEST,SOUTH,SOUTHEAST
	}
	
	private Vector2 position;
	public Vector2 getPosition(){return this.position;}
	
	private int numNeighbors;
	public int getNumNeighbors(){return this.numNeighbors;}
	public void incrementNumNeighbors(){this.numNeighbors += 1;}
	public void zeroNumNeighbors(){this.numNeighbors = 0;}
	
	public Cell(Vector2 position)
	{
		this.position = position;
		this.numNeighbors = 0;		
	}
	
	public Vector2 getNeighborPosition(Direction direction)
	{
		Vector2 neighborPosition = null;
		switch (direction)
		{
			case NORTHWEST:
				neighborPosition = new Vector2(this.position.getX()-1,this.position.getY()-1);
				break;
			case NORTH:
				neighborPosition = new Vector2(this.position.getX(),this.position.getY()-1);
				break;
			case NORTHEAST:
				neighborPosition = new Vector2(this.position.getX()+1,this.position.getY()-1);
				break;
			case WEST:
				neighborPosition = new Vector2(this.position.getX()-1,this.position.getY());
				break;
			case EAST:
				neighborPosition = new Vector2(this.position.getX()+1,this.position.getY());
				break;				
			case SOUTHWEST:
				neighborPosition = new Vector2(this.position.getX()-1,this.position.getY()+1);
				break;
			case SOUTH:
				neighborPosition = new Vector2(this.position.getX(),this.position.getY()+1);
				break;
			case SOUTHEAST:
				neighborPosition = new Vector2(this.position.getX()+1,this.position.getY()+1);
				break;		
		}
		return neighborPosition;
	}	
}
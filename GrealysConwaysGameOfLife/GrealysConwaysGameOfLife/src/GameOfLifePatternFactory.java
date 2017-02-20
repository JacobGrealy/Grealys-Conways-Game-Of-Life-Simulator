import java.util.LinkedList;
import java.util.List;

public class GameOfLifePatternFactory
{
	static public enum Pattern
	{
		EXCERCISE_EXAMPLE, SINGLE_CELL, EXCERCISE_EXAMPLE_AFTER_1_GEN, BLOCK, BEEHIVE, LOAF, BOAT, TUB, BLINKER, TOAD, BEACON, GLIDER, LIGHTWEIGHT_SPACESHIP, GOSPERS_GLIDER_GUN
	}
	
	static public List<Cell> getPattern(Pattern patternEnum)
	{
		List<Cell> pattern = new LinkedList<Cell>();
		
		switch (patternEnum)
		{
			case SINGLE_CELL:
			pattern.add(new Cell(new Vector2(0,0)));
			break;
		
			case EXCERCISE_EXAMPLE:
				pattern.add(new Cell(new Vector2(6,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(1,1)));
				pattern.add(new Cell(new Vector2(2,1)));	
				pattern.add(new Cell(new Vector2(6,1)));
				pattern.add(new Cell(new Vector2(6,2)));
				pattern.add(new Cell(new Vector2(3,4)));
				pattern.add(new Cell(new Vector2(4,4)));
				pattern.add(new Cell(new Vector2(3,5)));
				pattern.add(new Cell(new Vector2(4,5)));
				break;
				
			case EXCERCISE_EXAMPLE_AFTER_1_GEN:
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(1,1)));
				pattern.add(new Cell(new Vector2(5,1)));
				pattern.add(new Cell(new Vector2(6,1)));
				pattern.add(new Cell(new Vector2(7,1)));
				pattern.add(new Cell(new Vector2(1,2)));
				pattern.add(new Cell(new Vector2(3,4)));
				pattern.add(new Cell(new Vector2(4,4)));
				pattern.add(new Cell(new Vector2(3,5)));
				pattern.add(new Cell(new Vector2(4,5)));
				break;	
				
			case BLOCK:
				pattern.add(new Cell(new Vector2(0,0)));
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(1,1)));
				break;
				
			case BEEHIVE:
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(2,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(3,1)));
				pattern.add(new Cell(new Vector2(1,2)));
				pattern.add(new Cell(new Vector2(2,2)));
				break;
				
			case LOAF:
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(2,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(3,1)));
				pattern.add(new Cell(new Vector2(1,2)));
				pattern.add(new Cell(new Vector2(3,2)));
				pattern.add(new Cell(new Vector2(2,3)));				
				break;
				
			case BOAT:
				pattern.add(new Cell(new Vector2(0,0)));
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(2,1)));
				pattern.add(new Cell(new Vector2(1,2)));	
				break;
				
			case TUB:
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(2,1)));
				pattern.add(new Cell(new Vector2(1,2)));
				break;
				
			case BLINKER:
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(1,1)));
				pattern.add(new Cell(new Vector2(1,2)));
				break;		
				
			case TOAD:
				pattern.add(new Cell(new Vector2(1,1)));
				pattern.add(new Cell(new Vector2(2,1)));
				pattern.add(new Cell(new Vector2(3,1)));
				pattern.add(new Cell(new Vector2(0,2)));
				pattern.add(new Cell(new Vector2(1,2)));
				pattern.add(new Cell(new Vector2(2,2)));
				break;	
				
			case BEACON:
				pattern.add(new Cell(new Vector2(0,0)));
				pattern.add(new Cell(new Vector2(1,0)));
				pattern.add(new Cell(new Vector2(0,1)));
				pattern.add(new Cell(new Vector2(1,1)));
				pattern.add(new Cell(new Vector2(2,2)));
				pattern.add(new Cell(new Vector2(3,2)));
				pattern.add(new Cell(new Vector2(2,3)));
				pattern.add(new Cell(new Vector2(3,3)));
				break;
				
			case GLIDER:
				pattern.add(new Cell(new Vector2(0,0)));
				pattern.add(new Cell(new Vector2(1,1)));
				pattern.add(new Cell(new Vector2(2,1)));
				pattern.add(new Cell(new Vector2(0,2)));
				pattern.add(new Cell(new Vector2(1,2)));
				break;
				
			case LIGHTWEIGHT_SPACESHIP:
				pattern.add(new Cell(new Vector2(0,0)));
				pattern.add(new Cell(new Vector2(3,0)));
				pattern.add(new Cell(new Vector2(4,1)));
				pattern.add(new Cell(new Vector2(0,2)));
				pattern.add(new Cell(new Vector2(4,2)));
				pattern.add(new Cell(new Vector2(1,3)));
				pattern.add(new Cell(new Vector2(2,3)));
				pattern.add(new Cell(new Vector2(3,3)));
				pattern.add(new Cell(new Vector2(4,3)));
				
				break;
				
			case GOSPERS_GLIDER_GUN:
				pattern.add(new Cell(new Vector2(24,0)));
				
				pattern.add(new Cell(new Vector2(22,1)));
				pattern.add(new Cell(new Vector2(24,1)));
				
				pattern.add(new Cell(new Vector2(12,2)));
				pattern.add(new Cell(new Vector2(13,2)));
				pattern.add(new Cell(new Vector2(20,2)));
				pattern.add(new Cell(new Vector2(21,2)));
				pattern.add(new Cell(new Vector2(34,2)));
				pattern.add(new Cell(new Vector2(35,2)));
				
				pattern.add(new Cell(new Vector2(11,3)));
				pattern.add(new Cell(new Vector2(15,3)));
				pattern.add(new Cell(new Vector2(20,3)));
				pattern.add(new Cell(new Vector2(21,3)));
				pattern.add(new Cell(new Vector2(34,3)));
				pattern.add(new Cell(new Vector2(35,3)));
				
				pattern.add(new Cell(new Vector2(0,4)));
				pattern.add(new Cell(new Vector2(1,4)));
				pattern.add(new Cell(new Vector2(10,4)));
				pattern.add(new Cell(new Vector2(16,4)));
				pattern.add(new Cell(new Vector2(20,4)));
				pattern.add(new Cell(new Vector2(21,4)));				
				
				pattern.add(new Cell(new Vector2(0,5)));
				pattern.add(new Cell(new Vector2(1,5)));
				pattern.add(new Cell(new Vector2(10,5)));
				pattern.add(new Cell(new Vector2(14,5)));
				pattern.add(new Cell(new Vector2(16,5)));
				pattern.add(new Cell(new Vector2(17,5)));
				pattern.add(new Cell(new Vector2(22,5)));
				pattern.add(new Cell(new Vector2(24,5)));
				
				pattern.add(new Cell(new Vector2(10,6)));
				pattern.add(new Cell(new Vector2(16,6)));
				pattern.add(new Cell(new Vector2(24,6)));
				
				pattern.add(new Cell(new Vector2(11,7)));
				pattern.add(new Cell(new Vector2(15,7)));
				
				pattern.add(new Cell(new Vector2(12,8)));
				pattern.add(new Cell(new Vector2(13,8)));
				
				break;
				
		}		
		return pattern;
	}
}

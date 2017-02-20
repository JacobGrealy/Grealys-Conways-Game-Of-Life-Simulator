import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class DeadCellTest extends TestCase {

	IGameOfLifeModel gameOfLifeModel;
	GameplayController gameplayController;
	Cell DeadCellStandIn;
	
   // Setup a basic model and create a GameplayController for generating next generation
   protected void setUp()
   {
	   //Create a standard gameOfLifeModel (Size Vector2Int(8,6), BorderMode.EXPANDABLE, 1f,null
	   gameOfLifeModel = new GameOfLifeModelInMemory();
	   //2,2 DeadCellStandIn - We won't actually add this to any lists, we will just use it for getting neighbor positions and comparing position to see if a live cell get's generated. 
	   DeadCellStandIn = new Cell(new Vector2(2,2));
	   //Create a GameplayController, we will use this to simulate 1 turn
	   gameplayController = new GameplayController();
   }
	
	@Test
	public void test0Neighbors()
	{	
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 0 neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test1Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);		
		//With 1 neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test2Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 2 neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test3Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHEAST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 3 neighbors the Dead cell should be alive after 1 generation.
		assertNotNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test4Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.WEST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 4 or more neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test5Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.EAST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 4 or more neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test6Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.EAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.SOUTHWEST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);

		//With 4 or more neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test7Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.EAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.SOUTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.SOUTH)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 4 or more neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
	
	@Test
	public void test8Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.EAST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.SOUTHWEST)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.SOUTH)));
		neighbors.add(new Cell (DeadCellStandIn.getNeighborPosition(Cell.Direction.SOUTHEAST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//With 4 or more neighbors the Dead cell should still be dead after 1 generation.
		assertNull(gameOfLifeModel.getCellAtPosition(DeadCellStandIn.getPosition()));
	}
}

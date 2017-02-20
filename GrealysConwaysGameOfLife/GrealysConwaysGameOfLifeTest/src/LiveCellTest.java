import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class LiveCellTest extends TestCase {

	IGameOfLifeModel gameOfLifeModel;
	GameplayController gameplayController;
	Cell liveCellToTest;
	
   // Setup a basic model with one live cell at 2,2 and create a GameplayController for generating next generation
   protected void setUp()
   {
	   //Create a standard gameOfLifeModel
	   gameOfLifeModel = new GameOfLifeModelInMemory();
	   //Create a live cell at 2,2 
	   liveCellToTest = new Cell(new Vector2(2,2));
	   //Add the cell to the LiveCellsList
	   gameOfLifeModel.addLiveCells(liveCellToTest);
	   //Create a GameplayController, we will use this to simulate 1 turn
	   gameplayController = new GameplayController();
   }
	
	@Test
	public void test0Neighbors()
	{	
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Shoud have had 0 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),0);
		//With 0 neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test1Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);		
		//Should have had 1 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),1);
		//With 1 neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test2Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 2 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),2);
		//With 2 neighbors the live cell should be alive after 1 generation.
		assertTrue(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test3Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHEAST)));

				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 3 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),3);
		//With 3 neighbors the live cell should be alive after 1 generation.
		assertTrue(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test4Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.WEST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 4 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),4);
		//With 4 or more neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test5Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.EAST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 5 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),5);
		//With 4 or more neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test6Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.EAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.SOUTHWEST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 6 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),6);
		//With 4 or more neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test7Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.EAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.SOUTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.SOUTH)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 7 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),7);
		//With 4 or more neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
	
	@Test
	public void test8Neighbors()
	{			
		List<Cell> neighbors = new ArrayList<Cell>();
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.NORTHEAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.WEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.EAST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.SOUTHWEST)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.SOUTH)));
		neighbors.add(new Cell (liveCellToTest.getNeighborPosition(Cell.Direction.SOUTHEAST)));
				
		gameOfLifeModel.addLiveCells(neighbors);
		
		//Go to next Generation
		gameplayController.generateUpToGeneration(2, gameOfLifeModel);
		//Should have had 8 neighbors
		assertEquals(liveCellToTest.getNumNeighbors(),8);
		//With 4 or more neighbors the live cell should be dead after 1 generation.
		assertFalse(gameOfLifeModel.getLiveCellsList().contains(liveCellToTest));
	}
}

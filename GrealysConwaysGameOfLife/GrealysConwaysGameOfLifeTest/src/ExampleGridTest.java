import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

//We will use this to test that the example given in the writeup works as expected
public class ExampleGridTest extends TestCase {

	IGameOfLifeModel gameOfLifeModel;
	GameplayController gameplayController;
	
   // Setup a basic model with one live cell at 2,2 and create a GameplayController for generating next generation
   protected void setUp()
   {
	   //Create a standard gameOfLifeModel (Size Vector2Int(8,6), BorderMode.EXPANDABLE, 1f,null
	   gameOfLifeModel = new GameOfLifeModelInMemory();
	   
	   //Add the exercise example to the model
	   gameOfLifeModel.addLiveCells(GameOfLifePatternFactory.getPattern(GameOfLifePatternFactory.Pattern.EXCERCISE_EXAMPLE));
	   
	   //Create a GameplayController, we will use this to simulate 1 turn
	   gameplayController = new GameplayController();
	   
		//Go to next Generation
	   gameplayController.generateUpToGeneration(2, gameOfLifeModel);
   }
	
   @Test
	public void testForNoExtraLiveCells()
	{	
		//There should be exactly 10 live cells
		assertEquals(gameOfLifeModel.getLiveCellsList().size(),10);
	} 
   
   @Test
	public void testForExpectedLiveCells()
	{	
		//Every Cell in expectedLiveCells should be in alive cells. Both of these asserts together ensure that we have exactly the live cells we expect, no more, no less.
		List<Cell> expectedLiveCells = (GameOfLifePatternFactory.getPattern(GameOfLifePatternFactory.Pattern.EXCERCISE_EXAMPLE_AFTER_1_GEN)); 
		for(Cell expectedCell : expectedLiveCells)
		{
			assertNotNull(gameOfLifeModel.getCellAtPosition(expectedCell.getPosition()));
		}
	}
}

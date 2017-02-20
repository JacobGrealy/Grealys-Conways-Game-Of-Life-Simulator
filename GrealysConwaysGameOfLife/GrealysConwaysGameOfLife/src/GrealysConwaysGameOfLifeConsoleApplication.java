import java.util.List;

//This console application was developed as a proof of concept for the gameOfLifeModel and GameplayController.
//It has not been updated to fully support expandable borders, but it does support expanding down and to the right.
//This application has been replaced by /GrealysConwaysGameOfLifeGUI/src/GrealysConwaysGameOfLifeGUIApplicationWindow.java which contains the full feature set.

public class GrealysConwaysGameOfLifeConsoleApplication
{
	private static IGameOfLifeModel gameOfLifeModel;
	private static GameplayController gameplayController;
	private static IView view;
	
	static long lastFrameTime = 0;
	static long thisFrameTime = 0;
	static final float NanoSecondsInASecond = 1000000000f;
	
	//This is the entry point to our program
	public static void main(String[] args)
	{	
		BorderMode borderMode = BorderMode.CONSTRAINED;
		float timeToGenerateCells = 1f;
		int generationToStartOn = 1;		
		//Boundary Size to start with
		Vector2 startingPlayFieldSize = new Vector2(8,6);
		//List of live cells to start with
		List<Cell> startingLiveCellsList = GameOfLifePatternFactory.getPattern(GameOfLifePatternFactory.Pattern.EXCERCISE_EXAMPLE);
		
		gameOfLifeModel = new GameOfLifeModelInMemory(startingPlayFieldSize, borderMode, timeToGenerateCells, startingLiveCellsList);		
		gameplayController = new GameplayController();
		view = new ConsoleView();
		gameplayController.generateUpToGeneration(generationToStartOn, gameOfLifeModel);
		gameOfLifeModel.zeroTimeSinceLastGeneration();
		//Set the starting point for the frame times
		lastFrameTime = System.nanoTime();
		thisFrameTime = System.nanoTime();
		
		//We are done initializing, let's start the gameloop
		StartGameLoop();
	}
	
	private static float calculateDeltaTimeInSeconds()
	{
		lastFrameTime = thisFrameTime;
		thisFrameTime = System.nanoTime();
		float deltaTimeInSeconds = (thisFrameTime - lastFrameTime) / NanoSecondsInASecond;
		return deltaTimeInSeconds;
	}
	
	private static void StartGameLoop()
	{	
		//As long as our application is open we want our game to be running
		while(true)
		{
			//We want our game to be frame independent, so we will keep track of how much time has passed since the last frame, and pass that with the update method.
			float deltaTimeInSeconds = calculateDeltaTimeInSeconds();			
			//Update the game state
			gameplayController.Update(deltaTimeInSeconds, gameOfLifeModel);
			//Update the view based on the gamestate
			view.update(deltaTimeInSeconds, gameOfLifeModel);
		}
		
	}	
}

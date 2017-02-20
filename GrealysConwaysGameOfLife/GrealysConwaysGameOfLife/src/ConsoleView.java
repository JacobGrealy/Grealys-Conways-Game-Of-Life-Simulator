import java.util.Arrays;

public class ConsoleView implements IView
{
	int lastGenerationPrinted;
	
	public ConsoleView()
	{
		lastGenerationPrinted = 0;
	}
	
	public void update(float deltaTimeInSeconds, IGameOfLifeModel gameOfLifeModel)
	{
		//If there has been a generation generated since the last time we printed the state, we need to print the new state
		if(lastGenerationPrinted < gameOfLifeModel.getCurrentGeneration())
		{
			printGridView(gameOfLifeModel);
			lastGenerationPrinted = gameOfLifeModel.getCurrentGeneration();
		}
	}
	
	public void printGridView(IGameOfLifeModel gameOfLifeModel)
	{
		int rows = gameOfLifeModel.getPlayFieldSize().getX();
		int columns = gameOfLifeModel.getPlayFieldSize().getY();
		char[][] gameStateAsArray = new char[columns][rows];
			
		for(Cell liveCell : gameOfLifeModel.getLiveCellsList())
		{
			int posX = liveCell.getPosition().getX();
			int posY = liveCell.getPosition().getY();
			//Don't try to draw anything outside of the array
			if(posX >= 0 && posX < rows && posY >= 0 && posY < columns)
				gameStateAsArray[posY][posX] = 'O';
		}
		
		System.out.println("Generation Number: " + gameOfLifeModel.getCurrentGeneration() + " Population: " + gameOfLifeModel.getLiveCellsList().size());
		for(int i = 0; i < gameStateAsArray.length; i++)
		{
			System.out.println(Arrays.toString(gameStateAsArray[i]));
		}	
	}
}

import java.util.LinkedList;
import java.util.List;

public class GameplayController
{
	//List of potential cells, if they end up with exactly 3 neighbors they will become a cell
	private List<Cell> potentialCellList;
		
	public GameplayController()
	{
		potentialCellList = new LinkedList<Cell>();
	}
	
	public void generateUpToGeneration(int generationNumber,IGameOfLifeModel gameOfLifeModel)
	{
		//We can't go backwards, that would require keeping a history of gamestates, or a list of all commands executed
		if (generationNumber <= gameOfLifeModel.getCurrentGeneration()) return;
		//Generate up to the stating generation
		int generationI;
		for(generationI = 1; generationI < generationNumber; generationI++)
		{			
			UpdateGameState(gameOfLifeModel);
		}
		gameOfLifeModel.setCurrentGeneration(generationI);
	}
	
	public void Update(float deltaTimeInSeconds, IGameOfLifeModel gameOfLifeModel)
	{
		if(gameOfLifeModel.getIsPaused() == false)
		{
			//Increment the time since last generation by deltatime
			gameOfLifeModel.incrementTimeSinceLastGeneration(deltaTimeInSeconds);
			if(gameOfLifeModel.getTimeSinceLastGeneration() > gameOfLifeModel.getTimeToGenerateCells())
			{
				while(gameOfLifeModel.getCurrentGeneration() < gameOfLifeModel.getNextGenerationToGoTo())
				{
					UpdateGameState(gameOfLifeModel);
					//increment the generation number
					gameOfLifeModel.setCurrentGeneration(gameOfLifeModel.getCurrentGeneration()+1);
				}
				//zero the time since last generation
				gameOfLifeModel.zeroTimeSinceLastGeneration();
				//Set the next generation to be 1 more than the current, this is default unless input makes it otherwise
				gameOfLifeModel.setNextGenerationToGoTo(gameOfLifeModel.getCurrentGeneration()+1);
			}
		}
	}
	
	private void UpdateGameState(IGameOfLifeModel gameOfLifeModel)
	{
		//get rid of any cells that are outside of the border, or expand the border to include them if bordermode is expandable
		List<Cell> liveCellsToKeep = new LinkedList<Cell>();
		for(Cell liveCell : gameOfLifeModel.getLiveCellsList())
		{
			//if they are in bounds we keep them
			if(gameOfLifeModel.isPositionInBounds(liveCell.getPosition()))
			{
				liveCellsToKeep.add(liveCell);
			}
			else
			{
				//if they are out of bounds but bordermode = expandable, then we still keep them and we also expand the border to include them
				if(gameOfLifeModel.getBorderMode() == BorderMode.EXPANDABLE)
				{
					gameOfLifeModel.expandBorderToIncludePosition(liveCell.getPosition());
					liveCellsToKeep.add(liveCell);
				}
			}
		}
		gameOfLifeModel.setLiveCellsList(liveCellsToKeep);
		
		//Zero neighbor count for all cells
		for(Cell liveCell : gameOfLifeModel.getLiveCellsList())
		{
			liveCell.zeroNumNeighbors();
		}		

		//Calculate neighbor count for all cells and potential cells
		calculateNeighborCounts(gameOfLifeModel);
		
		//Determine which live cells to keep
		List<Cell> nextLiveCellsList = new LinkedList<Cell>();
		for(Cell liveCell : gameOfLifeModel.getLiveCellsList())
		{
			//Any live cell with two or three live neighbors lives on to the next generation
			if(liveCell.getNumNeighbors() == 2 || liveCell.getNumNeighbors() == 3)
			{
				nextLiveCellsList.add(liveCell);
			}
		}
		
		//Determine which potential cells to add
		for(Cell potentialCell : potentialCellList)
		{
			//Any dead cell with exactly three live neighbors becomes a live cell.
			if(potentialCell.getNumNeighbors() == 3)
			{
				nextLiveCellsList.add(potentialCell);
				//if expandable boundary, we need to see if this is outside of the playfield, if it is we need to expand the playfield
				if(gameOfLifeModel.getBorderMode() == BorderMode.EXPANDABLE)
				{
					gameOfLifeModel.expandBorderToIncludePosition(potentialCell.getPosition());
				}
			}
		}
		
		//set set live cells to the new live cells list
		gameOfLifeModel.setLiveCellsList(nextLiveCellsList);
		//clear the potential cells list
		potentialCellList = new LinkedList<Cell>();
	}
	
	private void calculateNeighborCounts(IGameOfLifeModel gameOfLifeModel)
	{
		//For every Cell we need to check how many neighbors it has, and add a neighbor to any dead cells that neighbor this cell
		for(Cell liveCell : gameOfLifeModel.getLiveCellsList())
		{
			//for each each neighboring cell
			for(Cell.Direction direction : Cell.Direction.values())
			{
				Vector2 neighborPosition = liveCell.getNeighborPosition(direction);
				//Check to see if the position is inside the bounds if we are in constrained mode
				if(gameOfLifeModel.getBorderMode() != BorderMode.CONSTRAINED || gameOfLifeModel.isPositionInBounds(neighborPosition))
				{
					//if it's wrap around and outside of the grid we need to correct the position to wrap around
					if(gameOfLifeModel.getBorderMode() == BorderMode.WRAPAROUND && !gameOfLifeModel.isPositionInBounds(neighborPosition))
					{
						neighborPosition = gameOfLifeModel.getWrapAroundPosition(neighborPosition);
					}
					Cell neighbor = null;
					//if it's a live cell, increment this cell's neighbor by 1
					neighbor = gameOfLifeModel.getCellAtPosition(neighborPosition);
					if(neighbor != null)
					{
						liveCell.incrementNumNeighbors();
					}
					//else if it's a potential cell, increment it's neighbor's by 1
					else
					{
						neighbor = getCellAtPosition(potentialCellList,neighborPosition);
						if(neighbor != null)
						{
							neighbor.incrementNumNeighbors();
						}
						else
						{
							neighbor = new Cell(neighborPosition);
							neighbor.incrementNumNeighbors();
							//add it to the list of potential cells
							potentialCellList.add(neighbor);
						}
					}
				}
			}
		}
	}
	
	private Cell getCellAtPosition(List<Cell> cellList, Vector2 position)
	{
		//Look for a cell at that position, if you find one return it
		for(Cell cell : cellList)
		{
			if(cell.getPosition().equals(position))
			{
				return cell;
			}
		}
		//We didn't find a cell, so we'll return null
		return null;
	}
}

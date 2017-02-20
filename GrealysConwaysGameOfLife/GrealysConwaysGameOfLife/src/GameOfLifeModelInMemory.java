import java.util.LinkedList;
import java.util.List;

//A Game of life model that only exists in memory, ie the data is not persisted to a database
public class GameOfLifeModelInMemory implements IGameOfLifeModel
{	
	private Vector2 playFieldTopLeftCorner;	
	public Vector2 getPlayFieldTopLeftCorner() {return playFieldTopLeftCorner;}
	public void setPlayFieldTopLeftCorner(Vector2 playFieldTopLeftCorner) {this.playFieldTopLeftCorner = playFieldTopLeftCorner;}
	
	private Vector2 playFieldBottomRightCorner;
	public Vector2 getPlayFieldBottomRightCorner() {return playFieldBottomRightCorner;}
	public void setPlayFieldBottomRightCorner(Vector2 playFieldTopRightCorner) {this.playFieldBottomRightCorner = playFieldTopRightCorner;}

	public Vector2 getPlayFieldSize()
	{
		int sizeX = playFieldBottomRightCorner.getX() - playFieldTopLeftCorner.getX();
		int sizeY = playFieldBottomRightCorner.getY() - playFieldTopLeftCorner.getY();
		return new Vector2(sizeX,sizeY);
	}
	public void setPlayFieldSize(Vector2 playFieldSize)
	{
		int playFieldBottomRightCornerX = playFieldTopLeftCorner.getX() + playFieldSize.getX();
		int playFieldBottomRightCornerY = playFieldTopLeftCorner.getY() + playFieldSize.getY();
		playFieldBottomRightCorner = new Vector2(playFieldBottomRightCornerX,playFieldBottomRightCornerY);
	}
	
	private BorderMode borderMode;
	public BorderMode getBorderMode() {return borderMode;}
	public void setBorderMode(BorderMode borderMode) {this.borderMode = borderMode;}
	
	private float zoom;
	public float getZoom() {return zoom;}
	public void setZoom(float zoom) {this.zoom = zoom;}

	private float timeToGenerateCells;
	public float getTimeToGenerateCells() {return timeToGenerateCells;}
	public void setTimeToGenerateCells(float timeToGenerateCells) {this.timeToGenerateCells = timeToGenerateCells;}
	
	private int currentGeneration;
	public void setCurrentGeneration(int currentGeneration){this.currentGeneration = currentGeneration;}
	public int getCurrentGeneration() {return currentGeneration;}
	
	private float timeSinceLastGeneration;
	public void incrementTimeSinceLastGeneration(float amountToIncrement){timeSinceLastGeneration += amountToIncrement;}
	public void zeroTimeSinceLastGeneration(){timeSinceLastGeneration = 0f;}
	public float getTimeSinceLastGeneration() {return timeSinceLastGeneration;}	

	private Boolean isPaused;	
	public Boolean getIsPaused() {return isPaused;}
	public void setIsPaused(Boolean isPaused) {this.isPaused = isPaused;}
	
	private int nextGenerationToGoTo;
	public int getNextGenerationToGoTo() {return nextGenerationToGoTo;}
	public void setNextGenerationToGoTo(int nextGenerationToGoTo) {this.nextGenerationToGoTo = nextGenerationToGoTo;}

	//List of Cell's that are currently alive
	private List<Cell> liveCellsList;
	public List<Cell> getLiveCellsList() {return liveCellsList;}
	public void setLiveCellsList(List<Cell> liveCellsList) {this.liveCellsList = liveCellsList;}
	public void clearLiveCells(){this.liveCellsList = new LinkedList<Cell>();}
	public void addLiveCells(Cell cellToAdd)
	{
		//Check to make sure there isn't already a cell there
		if(getCellAtPosition(cellToAdd.getPosition())==null)
			this.liveCellsList.add(cellToAdd);
	}
	public void addLiveCells(List<Cell> liveCellsToAddList)
	{
		for(Cell cell : liveCellsToAddList)
		{
			this.addLiveCells(cell);
		}
	}
	//Use to add live cells relative to a position
	public void addLiveCells(List<Cell> liveCellsToAddList, Vector2 position)
	{
		//Loop through every cell and add a new cell that has that cells position plus the pass position
		for(Cell cell : liveCellsToAddList)
		{
			this.addLiveCells(new Cell(new Vector2(cell.getPosition().getX()+position.getX(),cell.getPosition().getY()+position.getY())));
		}
	}
	
	//Returns the live cell at the given position, or null if no live cell is at the position
	public Cell getCellAtPosition(Vector2 position)
	{
		for(Cell cell : liveCellsList)
		{
			if(cell.getPosition().equals(position))
				return cell;
		}
		return null;
	}
	
	//use to see if a cell is within the playfield
	public Boolean isPositionInBounds(Vector2 position)
	{
		Boolean isInLeftBounds = position.getX() >= playFieldTopLeftCorner.getX();
		Boolean isInRightBounds = position.getX() < playFieldBottomRightCorner.getX();
		Boolean isInTopBounds = position.getY() >= playFieldTopLeftCorner.getY();
		Boolean isInBottomBounds = position.getY() < playFieldBottomRightCorner.getY();
		return isInLeftBounds && isInRightBounds && isInTopBounds && isInBottomBounds;
	}
	
	public void expandBorderToIncludePosition(Vector2 position)
	{
		if(position.getX() < playFieldTopLeftCorner.getX())
			playFieldTopLeftCorner.setX(position.getX());
		if(position.getX() >= playFieldBottomRightCorner.getX())
			playFieldBottomRightCorner.setX(position.getX()+1);
		if(position.getY() < playFieldTopLeftCorner.getY())
			playFieldTopLeftCorner.setY(position.getY());
		if(position.getY() >= playFieldBottomRightCorner.getY())
			playFieldBottomRightCorner.setY(position.getY()+1);
	}
	
	public Vector2 getWrapAroundPosition(Vector2 position)
	{
		Vector2 wrapAroundPosition = null;
		int wrapAroundPositionX = position.getX();
		int wrapAroundPositionY = position.getY();
		
		if(wrapAroundPositionX >= playFieldBottomRightCorner.getX())
			wrapAroundPositionX = playFieldTopLeftCorner.getX();
		
		if(wrapAroundPositionX < playFieldTopLeftCorner.getX())
			wrapAroundPositionX = playFieldBottomRightCorner.getX()-1;		
		
		if(wrapAroundPositionY >= playFieldBottomRightCorner.getY())
			wrapAroundPositionY = playFieldTopLeftCorner.getY();
		
		if(wrapAroundPositionY < playFieldTopLeftCorner.getY())
			wrapAroundPositionY = playFieldBottomRightCorner.getY()-1;	
		
		wrapAroundPosition = new Vector2(wrapAroundPositionX,wrapAroundPositionY);
		return wrapAroundPosition;
	}
	
	public GameOfLifeModelInMemory(Vector2 startingPlayFieldSize, BorderMode borderMode, float timeToGenerateCells, List<Cell> startingLiveCellsList)
	{
		//set constraints
		this.playFieldTopLeftCorner = new Vector2(0,0);
		this.playFieldBottomRightCorner = new Vector2(startingPlayFieldSize.getX(),startingPlayFieldSize.getY());
		
		this.borderMode = borderMode;
		this.timeToGenerateCells = timeToGenerateCells;
		this.timeSinceLastGeneration = 0f;		
		
		//Initialize cell lists
		if(startingLiveCellsList == null)
		{
			this.liveCellsList = new LinkedList<Cell>();
		}
		else
		{
			this.liveCellsList = startingLiveCellsList;
		}
		//Start on initial generation
		this.currentGeneration = 1;
		//The next Generation will be generation 2
		this.nextGenerationToGoTo = 2;
		//Start unpaused
		this.isPaused = false;
		this.zoom = 1f;
	}	
	
	public GameOfLifeModelInMemory()
	{
		this(new Vector2(8,6),BorderMode.EXPANDABLE,1f,null);
	}
}

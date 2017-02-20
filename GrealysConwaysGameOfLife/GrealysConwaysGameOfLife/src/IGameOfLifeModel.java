import java.util.List;

public interface IGameOfLifeModel {

	Vector2 getPlayFieldTopLeftCorner();
	void setPlayFieldTopLeftCorner(Vector2 playFieldTopLeftCorner);
	
	Vector2 getPlayFieldBottomRightCorner();
	void setPlayFieldBottomRightCorner(Vector2 playFieldBottomRightCorner);
	
	Vector2 getPlayFieldSize();
	void setPlayFieldSize(Vector2 playFieldSize);

	BorderMode getBorderMode();
	
	float getZoom();	
	void setZoom(float zoom);

	void setBorderMode(BorderMode borderMode);

	float getTimeToGenerateCells();
	void setTimeToGenerateCells(float timeToGenerateCells);

	void setCurrentGeneration(int currentGeneration);
	int getCurrentGeneration();

	void incrementTimeSinceLastGeneration(float amountToIncrement);
	void zeroTimeSinceLastGeneration();
	float getTimeSinceLastGeneration();
	
	Boolean getIsPaused();	
	void setIsPaused(Boolean isPaused);
	
	int getNextGenerationToGoTo();	
	void setNextGenerationToGoTo(int nextGenerationToGoTo);

	List<Cell> getLiveCellsList();
	void setLiveCellsList(List<Cell> liveCellsList);	
	void clearLiveCells();
	void addLiveCells(Cell cellToAdd);
	void addLiveCells(List<Cell> liveCellsToAddList);
	void addLiveCells(List<Cell> liveCellsToAddList, Vector2 position);
	
	Cell getCellAtPosition(Vector2 position);
	
	Boolean isPositionInBounds(Vector2 position);
	
	void expandBorderToIncludePosition(Vector2 position);
	
	Vector2 getWrapAroundPosition(Vector2 position);
}
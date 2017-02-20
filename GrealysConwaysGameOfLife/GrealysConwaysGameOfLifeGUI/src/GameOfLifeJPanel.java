import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameOfLifeJPanel extends JPanel implements IView
{
	private BufferedImage liveCellImage;
	private IGameOfLifeModel gameOfLifeModel;
    private int defaultTileSize = 92;   
	public int getDefaultTileSize() {return defaultTileSize;}

	public GameOfLifeJPanel()
	{
		//Read in the live cell image
		try {                
			liveCellImage = ImageIO.read(GrealysConwaysGameOfLifeGUIApplicationWindow.class.getResource("/resources/LiveCell.png"));
	       } catch (IOException e) {
	    	   System.out.println("Exception thrown  :" + e);
	       }	
	}	
	
	public void update(float deltaTimeInSeconds, IGameOfLifeModel gameOfLifeModel) 
	{
		this.gameOfLifeModel = gameOfLifeModel;
		//repaint		
		repaint();
	}
	
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(gameOfLifeModel!=null)
        {
	        Graphics2D graphics2D = (Graphics2D) graphics;
	        graphics2D.setColor(Color.black);
	        int cellSize = (int)(defaultTileSize * gameOfLifeModel.getZoom());
	        
	        //draw the cells
	        for(Cell liveCell: gameOfLifeModel.getLiveCellsList())
	        {
	        	paintCell(graphics2D,liveCell,cellSize);
	        }
	        int borderSizeX = gameOfLifeModel.getPlayFieldSize().getX()*cellSize;
	        int borderSizeY = gameOfLifeModel.getPlayFieldSize().getY()*cellSize;
	        
	        
	        //draw the border
	        Rectangle2D borderRectangle = new Rectangle2D.Float(0,0,borderSizeX,borderSizeY);
	        graphics2D.draw(borderRectangle);
	        
	        //draw rectangle to mark the origin
	        Rectangle2D originRectangle = new Rectangle2D.Float(-gameOfLifeModel.getPlayFieldTopLeftCorner().getX()*cellSize,-gameOfLifeModel.getPlayFieldTopLeftCorner().getY()*cellSize,cellSize,cellSize);
	        graphics2D.setColor(Color.red);
	        graphics2D.draw(originRectangle);
	        
	        Toolkit.getDefaultToolkit().sync();
	        
	        //Resize the game area so that the playfield fits inside of it
	        this.setPreferredSize(new Dimension(borderSizeX,borderSizeY));
	        this.revalidate();
        }
    }
    
    private void paintCell(Graphics2D graphics2D, Cell liveCell, int size)
    {
    	//We use topleftcorner here so that we fit the entire playfield into the screen, incuding any negative coordinates if the topleft corner has been pushed back
        int imageX = (liveCell.getPosition().getX()-gameOfLifeModel.getPlayFieldTopLeftCorner().getX())*size;
        int imageY = (liveCell.getPosition().getY()-gameOfLifeModel.getPlayFieldTopLeftCorner().getY())*size;
        graphics2D.drawImage(liveCellImage, imageX, imageY, size,size, this); 
    }
    
    
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class GrealysConwaysGameOfLifeGUIApplicationWindow
{
	private Color toolBarColor = new Color(220,220,220);
		
	static final float NanoSecondsInASecond = 1000000000f;
	private Timer timer;
	private final int DELAY = 1;
	private GameplayController gameplayController;
	private IGameOfLifeModel gameOfLifeModel;
	private long lastFrameTime = 0;
	private long thisFrameTime = 0;
	private GameOfLifePatternFactory.Pattern selectedPattern;

	ActionListener updateTimerActionListener;
	
	private JFrame mainJFrame;
	private JTextField rowsTextField;
	private JTextField columnsTextField;
	//Stats pain labels - we need to be able to update these
	private JLabel columnValueLabel;
	private JLabel rowValueLabel;
	private JLabel generationValueLabel;
	private JLabel PopulationValueLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrealysConwaysGameOfLifeGUIApplicationWindow window = new GrealysConwaysGameOfLifeGUIApplicationWindow();
					window.mainJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GrealysConwaysGameOfLifeGUIApplicationWindow()
	{
		//Initialize Game Of Life Stuff
		BorderMode borderMode = BorderMode.CONSTRAINED;
		float timeToGenerateCells = 2f;
		int generationToStartOn = 1;		
		//Boundary Size to start with
		Vector2 startingPlayFieldSize = new Vector2(8,6);
		//List of live cells to start with
		List<Cell> startingLiveCellsList = GameOfLifePatternFactory.getPattern(GameOfLifePatternFactory.Pattern.EXCERCISE_EXAMPLE);
		
		gameOfLifeModel = new GameOfLifeModelInMemory(startingPlayFieldSize, borderMode, timeToGenerateCells, startingLiveCellsList);		
		gameplayController = new GameplayController();
		gameplayController.generateUpToGeneration(generationToStartOn, gameOfLifeModel);
		gameOfLifeModel.zeroTimeSinceLastGeneration();
		//Set the starting point for the frame times
		lastFrameTime = System.nanoTime();
		thisFrameTime = System.nanoTime();
		gameOfLifeModel.setZoom(1f);
        selectedPattern = GameOfLifePatternFactory.Pattern.SINGLE_CELL;
        //Initialize Swing stuff
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainJFrame = new JFrame();
		mainJFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(GrealysConwaysGameOfLifeGUIApplicationWindow.class.getResource("/resources/LiveCell.png")));
		mainJFrame.setTitle("Grealy's Conway's Game Of Life Simulator");
		mainJFrame.setBounds(100, 100, 1024, 768);
		mainJFrame.setMinimumSize(new Dimension(1024,768));
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel PatternPanel = new JPanel();
		mainJFrame.getContentPane().add(PatternPanel, BorderLayout.WEST);
		PatternPanel.setLayout(new GridLayout(0, 1, 0, 1));
		
		JToolBar ControlsToolBar = new JToolBar();
		ControlsToolBar.setFloatable(false);
		ControlsToolBar.setBackground(toolBarColor);
		mainJFrame.getContentPane().add(ControlsToolBar, BorderLayout.NORTH);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		ControlsToolBar.add(horizontalGlue_3);
		
		JLabel generationTimeLabel = new JLabel("Generation Time: ");
		ControlsToolBar.add(generationTimeLabel);
		
		JComboBox generationTimeComboBox = new JComboBox();
		generationTimeComboBox.setModel(new DefaultComboBoxModel(new String[] {"2s", "1s", ".5s", "No Limit"}));
		generationTimeComboBox.setToolTipText("Generation Time");
		generationTimeComboBox.setMaximumSize(new Dimension(50, 1000));
		ControlsToolBar.add(generationTimeComboBox);
		//Action Listener for changing bordermode
		ActionListener generationTimeComboBoxActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	changeGenerationTime((String)generationTimeComboBox.getSelectedItem());
            }
        };
        generationTimeComboBox.addActionListener(generationTimeComboBoxActionListener);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		ControlsToolBar.add(horizontalStrut_4);
		
		JLabel borderModeLabel = new JLabel("Border Mode: ");
		ControlsToolBar.add(borderModeLabel);
		
		JComboBox BorderModeComboBox = new JComboBox();
		BorderModeComboBox.setToolTipText("Border Mode");
		BorderModeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Cut Off", "Expand", "Wrap"}));
		BorderModeComboBox.setMaximumSize(new Dimension(50,1000));
		ControlsToolBar.add(BorderModeComboBox);
		//Action Listener for changing bordermode
		ActionListener borderModeComboBoxActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	changeBorderMode((String)BorderModeComboBox.getSelectedItem());
            }
        };
        BorderModeComboBox.addActionListener(borderModeComboBoxActionListener);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		ControlsToolBar.add(horizontalStrut_5);
		
		JLabel rowsEntryLabel = new JLabel("Rows: ");
		ControlsToolBar.add(rowsEntryLabel);
		
		rowsTextField = new JTextField();
		ControlsToolBar.add(rowsTextField);
		rowsTextField.setColumns(4);
		rowsTextField.setMaximumSize(new Dimension(6,50));
		//Action Listener for changing number of rows
		ActionListener rowsTextFieldActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	changeNumberOfRows(rowsTextField.getText());
            }
        };
        rowsTextField.addActionListener(rowsTextFieldActionListener);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(10);
		ControlsToolBar.add(horizontalStrut_6);
		
		JLabel columnsEntryLabel = new JLabel("Columns: ");
		ControlsToolBar.add(columnsEntryLabel);
		
		columnsTextField = new JTextField();
		columnsTextField.setColumns(4);
		columnsTextField.setMaximumSize(new Dimension(6,50));
		ControlsToolBar.add(columnsTextField);
		//Action Listener for changing number of columns
		ActionListener columnTextFieldActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	changeNumberOfColumns(columnsTextField.getText());
            }
        };
        columnsTextField.addActionListener(columnTextFieldActionListener);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		ControlsToolBar.add(horizontalStrut_1);
		
		JLabel lblZoom = new JLabel("Zoom ");
		ControlsToolBar.add(lblZoom);
		
		JButton zoomMinusButton = new JButton(" - ");
		ControlsToolBar.add(zoomMinusButton);
		//Action Listener for zooming out
		ActionListener zoomMinusButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	zoomOut();
            }
        };
        zoomMinusButton.addActionListener(zoomMinusButtonActionListener);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setMaximumSize(new Dimension(20, 1000));
		ControlsToolBar.add(separator_2);
		
		JButton zoomPlusButton = new JButton(" + ");
		ControlsToolBar.add(zoomPlusButton);
		//Action Listener for zooming in
		ActionListener zoomPlusButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	zoomIn();
            }
        };
        zoomPlusButton.addActionListener(zoomPlusButtonActionListener);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		ControlsToolBar.add(horizontalStrut_2);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		ControlsToolBar.add(horizontalGlue_4);
		
		JButton playButton = new JButton("Play");
		ControlsToolBar.add(playButton);
		//Action Listener for playing
		ActionListener playButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	play();
            }
        };
        playButton.addActionListener(playButtonActionListener);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setMaximumSize(new Dimension(20,1000));
		ControlsToolBar.add(separator);
		
		JButton pauseButton = new JButton("Pause");
		ControlsToolBar.add(pauseButton);
		//Action Listener for pausing
		ActionListener pauseButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	pause();
            }
        };
        pauseButton.addActionListener(pauseButtonActionListener);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setMaximumSize(new Dimension(20, 1000));
		ControlsToolBar.add(separator_1);
		
		JButton stopButton = new JButton("Stop");
		ControlsToolBar.add(stopButton);
		//Action Listener for stopping
		ActionListener stopButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	stop();
            }
        };
        stopButton.addActionListener(stopButtonActionListener);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		ControlsToolBar.add(horizontalGlue_2);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		ControlsToolBar.add(horizontalStrut);
		
		GameOfLifeJPanel gameOfLifeJPanel = new GameOfLifeJPanel();
		gameOfLifeJPanel.setToolTipText("");
		gameOfLifeJPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		gameOfLifeJPanel.setBackground(new Color(255, 255, 255));
		//We need to listen for mouse ups in the game of life panel so that we can create patterns there
		gameOfLifeJPanel.addMouseListener(new MouseListener() {
		    @Override
		    public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
			    int mouseX=e.getX();
			    int mouseY=e.getY();
			    //Translate from mouse space to grid space
			    int positionX = (int)(mouseX/(gameOfLifeJPanel.getDefaultTileSize()*gameOfLifeModel.getZoom()));
			    positionX += gameOfLifeModel.getPlayFieldTopLeftCorner().getX();
			    int positionY = (int)(mouseY/(gameOfLifeJPanel.getDefaultTileSize()*gameOfLifeModel.getZoom()));
			    positionY += gameOfLifeModel.getPlayFieldTopLeftCorner().getY();
			    gameOfLifeModel.addLiveCells(GameOfLifePatternFactory.getPattern(selectedPattern),new Vector2(positionX,positionY));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(gameOfLifeJPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainJFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		//Action Listener for loading patterns
		ActionListener PatternButtonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	String buttonText = ((JButton) e.getSource()).getText();
            	selectPattern(buttonText);
            }
        };
		for(GameOfLifePatternFactory.Pattern pattern : GameOfLifePatternFactory.Pattern.values())
		{
			//We don't want to use this pattern, it's just for validating that the exercise pattern 2nd generation is as expected
			if(pattern!=GameOfLifePatternFactory.Pattern.EXCERCISE_EXAMPLE_AFTER_1_GEN)
			{
				JButton btnPattern = new JButton(pattern.toString().toLowerCase().replace('_', ' '));
				btnPattern.setPreferredSize(new Dimension(250,5));
				btnPattern.setMinimumSize(new Dimension(250,5));
				btnPattern.setMaximumSize(new Dimension(250,5));
				btnPattern.setHorizontalAlignment(SwingConstants.LEFT);
				btnPattern.setIcon(new ImageIcon(GrealysConwaysGameOfLifeGUIApplicationWindow.class.getResource("/resources/PATTERN_" + pattern.toString() + ".png")));
				PatternPanel.add(btnPattern);
				btnPattern.addActionListener(PatternButtonActionListener);
			}
		}
		
		JToolBar StatDisplayToolBar = new JToolBar();
		StatDisplayToolBar.setFloatable(false);
		StatDisplayToolBar.setBackground(new Color(220, 220, 220));
		mainJFrame.getContentPane().add(StatDisplayToolBar, BorderLayout.SOUTH);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		StatDisplayToolBar.add(horizontalGlue_1);
		
		JLabel columnTitleLabel = new JLabel("Columns: ");
		StatDisplayToolBar.add(columnTitleLabel);
		
		columnValueLabel = new JLabel("8");
		StatDisplayToolBar.add(columnValueLabel);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(10);
		StatDisplayToolBar.add(horizontalStrut_7);
		
		JLabel rowTitleLabel = new JLabel("Rows: ");
		StatDisplayToolBar.add(rowTitleLabel);
		
		rowValueLabel = new JLabel("8");
		StatDisplayToolBar.add(rowValueLabel);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		StatDisplayToolBar.add(horizontalStrut_8);
		
		JLabel generationTitleLabel = new JLabel("Generation: ");
		StatDisplayToolBar.add(generationTitleLabel);
		
		generationValueLabel = new JLabel("1");
		StatDisplayToolBar.add(generationValueLabel);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		StatDisplayToolBar.add(horizontalStrut_3);
		
		JLabel PopulationTitleLabel = new JLabel("Population: ");
		StatDisplayToolBar.add(PopulationTitleLabel);
		
		PopulationValueLabel = new JLabel("0");
		StatDisplayToolBar.add(PopulationValueLabel);
		
		//Action Listener for update timer, this will function as our "gameloop"
        updateTimerActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	doGameLoop(gameOfLifeJPanel);
            }
        };
        
        timer = new Timer(DELAY, updateTimerActionListener);
        timer.start();
	}
	
	private void changeNumberOfRows(String numberOfRowsString)
	{
		Boolean ableToParseInt = true;
		int numRows = 0;
	     try {  
	    	 numRows = Integer.parseInt(numberOfRowsString);  
	      } catch (NumberFormatException e) {  
	    	  ableToParseInt = false;
	      }
	     if(ableToParseInt)
	     {
	    	 int numColumns = gameOfLifeModel.getPlayFieldSize().getX();	    	 
	    	 gameOfLifeModel.setPlayFieldSize(new Vector2(numColumns,numRows));
	     }
	}	
	
	private void changeNumberOfColumns(String numberOfColumnsString)
	{
		Boolean ableToParseInt = true;
		int numColumns = 0;
	     try {  
	    	 numColumns = Integer.parseInt(numberOfColumnsString);  
	      } catch (NumberFormatException e) {  
	    	  ableToParseInt = false;
	      }
	     if(ableToParseInt)
	     {
	    	 int numRows = gameOfLifeModel.getPlayFieldSize().getY();	    	 
	    	 gameOfLifeModel.setPlayFieldSize(new Vector2(numColumns,numRows));
	     }
	}
	
	private void selectPattern(String buttonText)
	{
		String patternEnumText = buttonText.toUpperCase().replace(' ', '_');
		GameOfLifePatternFactory.Pattern patternEnum = GameOfLifePatternFactory.Pattern.valueOf(patternEnumText);
        selectedPattern = patternEnum;
	}
	
	private void play()
	{
		gameOfLifeModel.setIsPaused(false);
	}
	
	private void pause()
	{
		gameOfLifeModel.setIsPaused(true);
	}
	
	private void stop()
	{
		gameOfLifeModel.setIsPaused(true);
		gameOfLifeModel.clearLiveCells();
		gameOfLifeModel.setCurrentGeneration(1);
		gameOfLifeModel.setNextGenerationToGoTo(2);
		gameOfLifeModel.setPlayFieldTopLeftCorner(new Vector2(0,0));
		Vector2 playFieldSize = new Vector2(8,6);
		gameOfLifeModel.setPlayFieldSize(playFieldSize);
	}
	
	private void zoomIn()
	{
		float nextZoom = gameOfLifeModel.getZoom() * 1.5f;
		gameOfLifeModel.setZoom(nextZoom);
	}
	
	private void zoomOut()
	{
		float nextZoom = gameOfLifeModel.getZoom() /1.5f;
		gameOfLifeModel.setZoom(nextZoom);
	}
	
	private void changeGenerationTime(String generationTimeString)
	{
		switch(generationTimeString){
		case "2s":
			gameOfLifeModel.setTimeToGenerateCells(2f);
			break;
		case "1s":
			gameOfLifeModel.setTimeToGenerateCells(1f);
			break;
		case ".5s":
			gameOfLifeModel.setTimeToGenerateCells(.5f);
			break;
		case "No Limit":
			gameOfLifeModel.setTimeToGenerateCells(0f);
			break;
		}
	}
	
	private void changeBorderMode(String borderModeString)
	{					
		switch(borderModeString){
			case "Cut Off":
				gameOfLifeModel.setBorderMode(BorderMode.CONSTRAINED);
				break;
			case "Expand":
				gameOfLifeModel.setBorderMode(BorderMode.EXPANDABLE);
				break;
			case "Wrap":
				gameOfLifeModel.setBorderMode(BorderMode.WRAPAROUND);
				break;			
		}
	}
	
	private void doGameLoop(GameOfLifeJPanel gameOfLifeJPanel)
	{		
		float deltaTimeInSeconds = calculateDeltaTimeInSeconds();
		//Update the gameState
		gameplayController.Update(deltaTimeInSeconds, gameOfLifeModel);
		//Update the view 
    	gameOfLifeJPanel.update(deltaTimeInSeconds,gameOfLifeModel);
    	//Update stats pane based on the gameOfLifeModel
    	updateStatsPane();
	}
	
	private void updateStatsPane()
	{
		columnValueLabel.setText(""+gameOfLifeModel.getPlayFieldSize().getX());
		rowValueLabel.setText(""+gameOfLifeModel.getPlayFieldSize().getY());
		generationValueLabel.setText(""+gameOfLifeModel.getCurrentGeneration());
		PopulationValueLabel.setText(""+gameOfLifeModel.getLiveCellsList().size());
	}
	
	private float calculateDeltaTimeInSeconds()
	{
		lastFrameTime = thisFrameTime;
		thisFrameTime = System.nanoTime();
		float deltaTimeInSeconds = (thisFrameTime - lastFrameTime) / NanoSecondsInASecond;
		return deltaTimeInSeconds;
	}
}

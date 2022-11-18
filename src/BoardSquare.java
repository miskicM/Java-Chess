import java.awt.Color;

import javax.swing.*;

public abstract class BoardSquare extends JButton
{
	private int xLocation;
	private int yLocation;
	
	private Color whiteColor = new Color(255,255,204);
	private Color blackColor = new Color(51,102,0);
	
	private Color squareColor;
	
	private Board board;
	
	
	public BoardSquare(int x, int y,Board b)
	{
		super(new ImageIcon("empty"));
		
		xLocation = x;
		yLocation = y;
		
		board = b;
		
		setBackground(blackColor);
	}
	
	public void setColor(boolean b)
	{
		squareColor = whiteColor;
		if(b) squareColor = blackColor;
		setBackground(squareColor);
	}
	
	public void setWarningColor()
	{
		setBackground(Color.RED);
	}
	
	public void setImage(String filename)
	{
		this.setIcon(new ImageIcon(filename));
	}
  
    public int getXLocation()
    {
        return xLocation;
    }
    
    public int getYLocation()
    {
        return yLocation;
    }
    
    public Board getBoard()
    {
    	return board;
    }
    
    public void cleanHighlight()
    {
    	setBackground(squareColor);
    }
    
    public abstract void onClick();
}

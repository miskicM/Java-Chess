import java.util.ArrayList;

public abstract class Piece 
{
	public static final boolean WHITE = false, BLACK = true;
	
	private boolean color;
	
	private String imgFilename;
	
	private Tile currentTile;
	
	private boolean firstMove = true;
	
	public Piece(boolean teamColor,String pieceType,Tile startingTile)
	{
		color = teamColor;
		currentTile = startingTile;
		
		String temp = "White";
		if(teamColor == BLACK) temp = "Black";
		
		imgFilename = temp+pieceType;
	}
	
	public abstract ArrayList<Tile> getMoves();
	
	public String getImage()
	{
		return imgFilename;
	}
	
	public boolean getColor()
	{
		return color;
	}
	
	public Tile getCurrentTile()
	{
		return currentTile;
	}
	
	public void setCurrentTile(Tile tile)
	{
		currentTile = tile;
	}
	
	public boolean isFirstMove()
	{
		return firstMove;
	}
	
	public void setFirstMove(boolean b)
	{
		firstMove = b;
	}

}

import java.util.ArrayList;

public class Pawn extends Piece
{
	//A pawn can only move forward
	private int direction = -1;
	
	public Pawn(boolean color,Tile startingTile)
	{
		super(color,"Pawn",startingTile);
		if(color) direction = 1;
	}
	
	public ArrayList<Tile> getMoves() 
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
		int yCurrent = getCurrentTile().getYLocation();
		int xCurrent = getCurrentTile().getXLocation();
		Tile[][] b = getCurrentTile().getBoard().getTiles();
		// move forward 
		if(!b[xCurrent][yCurrent + direction].isOccupied()) temp.add(b[xCurrent][yCurrent+direction]);
		
		if(this.isFirstMove() && !b[xCurrent][yCurrent + direction*2].isOccupied()) temp.add(b[xCurrent][yCurrent+direction*2]);
		
		// capture diagonally
		if(xCurrent >= 1 && b[xCurrent-1][yCurrent + direction].isOccupied())
		{
			if(b[xCurrent-1][yCurrent + direction].getCurrentPiece().getColor()!=this.getColor()) temp.add(b[xCurrent-1][yCurrent + direction]);
		}
		if(xCurrent <= 6 && b[xCurrent+1][yCurrent + direction].isOccupied())
		{
			if(b[xCurrent+1][yCurrent + direction].getCurrentPiece().getColor()!=this.getColor()) temp.add(b[xCurrent+1][yCurrent + direction]);
		}
		
		
		return temp;
	}

}

import java.util.ArrayList;

public class Knight extends Piece
{
	public Knight(boolean color,Tile startingTile)
	{
		super(color,"Knight",startingTile);
	}
	
	public ArrayList<Tile> getMoves()
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
		//move and capture in Ls
		getKnightMovesOn(1,2,temp);
		getKnightMovesOn(-1,2,temp);
		
		getKnightMovesOn(1,-2,temp);
		getKnightMovesOn(-1,-2,temp);
		
		getKnightMovesOn(2,1,temp);
		getKnightMovesOn(2,-1,temp);
		
		getKnightMovesOn(-2,1,temp);
		getKnightMovesOn(-2,-1,temp);
		
		return temp;
	}
	
	private void getKnightMovesOn(int xDir,int yDir,ArrayList<Tile> temp)
	{
		int yCurrent = getCurrentTile().getYLocation();
		int xCurrent = getCurrentTile().getXLocation();
		Tile[][] b = getCurrentTile().getBoard().getTiles();
		
		int newX = xCurrent + xDir;
		int newY = yCurrent + yDir;
		
		if(newX>7 || newX <0 ||newY>7 || newY <0) return;
		
		if(!b[newX][newY].isOccupied())
		{
			temp.add(b[newX][newY]);
			return;
		}
		
		if(b[newX][newY].getCurrentPiece().getColor()!=this.getColor())
			temp.add(b[newX][newY]);
		
	}

}

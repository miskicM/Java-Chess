import java.util.ArrayList;

public class Queen extends Piece
{
	public Queen(boolean color,Tile startingTile)
	{
		super(color,"Queen",startingTile);
	}
	
	public ArrayList<Tile> getMoves()
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
		// move and capture diagonally
		getMovesOn(1,1,1,temp);
		getMovesOn(1,-1,1,temp);
		getMovesOn(-1,1,1,temp);
		getMovesOn(-1,-1,1,temp);
		
		//move and capture in straight lines
		getMovesOn(0,1,1,temp);
		getMovesOn(1,0,1,temp);
		getMovesOn(0,-1,1,temp);
		getMovesOn(-1,0,1,temp);
		
		return temp;
	}
	
	private void getMovesOn(int xDir,int yDir,int dist,ArrayList<Tile> temp)
	{
		int yCurrent = getCurrentTile().getYLocation();
		int xCurrent = getCurrentTile().getXLocation();
		Tile[][] b = getCurrentTile().getBoard().getTiles();
	
		if(xCurrent+xDir*dist<0 || yCurrent+yDir*dist<0 || xCurrent+xDir*dist>7 || yCurrent+yDir*dist>7 ) return;
			
		if(!b[xCurrent + xDir*dist][yCurrent +yDir*dist].isOccupied())
		{
			temp.add(b[xCurrent + xDir*dist][yCurrent +yDir*dist]);
			getMovesOn(xDir,yDir,dist+1,temp);
		}
		
		if(temp.contains(b[xCurrent + xDir*dist][yCurrent +yDir*dist]))return;
		
		if(b[xCurrent + xDir*dist][yCurrent +yDir*dist].getCurrentPiece().getColor()!=this.getColor())
			temp.add(b[xCurrent + xDir*dist][yCurrent +yDir*dist]);
	}
}

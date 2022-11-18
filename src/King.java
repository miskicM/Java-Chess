import java.util.ArrayList;

public class King extends Piece
{
	public King(boolean color,Tile startingTile)
	{
		super(color,"King",startingTile);
	}
	
	public ArrayList<Tile> getMoves()
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
		int yCurrent = getCurrentTile().getYLocation();
		int xCurrent = getCurrentTile().getXLocation();
		Tile[][] b = getCurrentTile().getBoard().getTiles();
		
		for (int y = yCurrent-1;y!=yCurrent+2;y++)
		{
			for (int x = xCurrent-1;x!=xCurrent+2;x++)
			{
				if(x<0 || y<0 || x>7 || y>7 ) continue;
				
				if(!b[x][y].isOccupied())
				{
					temp.add(b[x][y]);
					continue;
				}
				if(b[x][y].getCurrentPiece().getColor()!=this.getColor())
					temp.add(b[x][y]);
			}
		}
		
		return temp;
	}
}

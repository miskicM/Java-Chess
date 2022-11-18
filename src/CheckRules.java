import java.util.ArrayList;

public class CheckRules
{
	private Board board;
	Tile[][] tiles;
	
	public CheckRules(Board b)
	{
		board = b;
		tiles = board.getTiles();
		
	}
	
	/*
	 * Returns a list of tiles that would create a threat to own king if played.
	 */
	public ArrayList<Tile> getIllegalMoves(Piece piece)
	{
		ArrayList<Tile> temp = new ArrayList<Tile>();
		
		ArrayList<Tile> moves = piece.getMoves();
		
		Tile currentTile = piece.getCurrentTile();
		
		for(int i = 0; i!= moves.size();i++)
		{
			Piece curPiece = moves.get(i).getCurrentPiece();
			
			moves.get(i).setPiece(piece);
			if(checkForThreatsOnKing(piece.getColor())) temp.add(moves.get(i));
			currentTile.setPiece(piece);
			
			if(curPiece!=null) moves.get(i).setPiece(curPiece);
			else moves.get(i).removePiece();
		}
		
		return temp;
	}
	
	/*
	 * Check if a king of input color is under check.
	 */
	public boolean checkForThreatsOnKing(boolean color)
	{
		King king = board.getWhiteKing();
		if(color) king = board.getBlackKing();
		
		Tile kingsTile = king.getCurrentTile();
		
		for (int y = 0; y!=7;y++)
		{
			for (int x = 0; x!=7;x++)
			{
				if(!tiles[x][y].isOccupied()) continue;
				if(tiles[x][y].getCurrentPiece().getColor()==color) continue;
				
				ArrayList<Tile> moves = tiles[x][y].getCurrentPiece().getMoves();
				
				if(moves.contains(kingsTile)) return true;
			}
		}
		return false;
	}
}

import java.awt.Color;
import java.util.ArrayList;

public class Tile extends BoardSquare
{
	private Piece currentPiece;

	public Tile(int x, int y, Board board) 
	{
		super(x, y, board);
		
	}

	public void onClick() 
	{
		boolean playerTurn = getBoard().getPlayerTurn();
		Piece selectedPiece = getBoard().getSelectedPiece();
		
		if(selectedPiece!=null) 
		{
			movePiece(selectedPiece);
			return;
		}
		
		if(currentPiece == null || currentPiece.getColor()!=playerTurn) return;
		
		ArrayList<Tile> possibleMoves = currentPiece.getMoves();
		
		//Check for impossible moves
		ArrayList<Tile> impossibleMoves = getBoard().getRuleChecker().getIllegalMoves(currentPiece);
		ArrayList<Tile> temp = new ArrayList<Tile>();
		for(int i =0; i!=possibleMoves.size();i++)
			if(!impossibleMoves.contains(possibleMoves.get(i))) temp.add(possibleMoves.get(i));
		possibleMoves = temp;
			
		if(possibleMoves.size()<1) return;
		
		getBoard().setSelectedPiece(currentPiece);
		
		for(Tile tile:possibleMoves)
		{
			tile.setBackground(Color.YELLOW);
		}
		
	}
	
	private void movePiece(Piece piece)
	{
		if(!piece.getMoves().contains(this)) return;
		piece.getCurrentTile().removePiece();
		setPiece(piece);
		piece.setFirstMove(false);
		getBoard().nextTurn();
	}
	
	public void setPiece(Piece piece)
	{
		currentPiece = piece;
		currentPiece.setCurrentTile(this);
		setImage("res/"+piece.getImage()+".png");
	}
	
	public void removePiece()
	{
		currentPiece = null;
		setImage("empty");
	}
	
	public Piece getCurrentPiece()
	{
		return currentPiece;
	}
	
	public boolean isOccupied()
	{
		if(currentPiece == null) return false;
		return true;
	}

}

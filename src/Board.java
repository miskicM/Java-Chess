import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JFrame
{
	private JPanel boardPanel = new JPanel();
	private Tile[][] board;
	private int imgSize = 60;
	
	private boolean playerTurn = false;
	private Piece selectedPiece = null;
	
	private CheckRules cr;
	
	private King whiteKing;
	private King blackKing;
	
	public Board()
	{
		super();
		
		board = new Tile[8][8];
		
		setSize(8*imgSize,8*imgSize);
		setContentPane(boardPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardPanel.setLayout(new GridLayout(8,8));
		
		for(int y=0;y!=8;y++)
		{
			for (int x=0;x!=8;x++)
			{
				board[x][y] = new Tile(x,y,this);
				board[x][y].addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        BoardSquare s = (BoardSquare) e.getSource();

                        if (e.getButton() == MouseEvent.BUTTON1)
                            s.onClick();
                    }
                });
                    
				boardPanel.add(board[x][y]);
			}
		}
		
		//Paint the board to chequers
		
		boolean currSquare = true;
		for(int y=0;y!=8;y++)
		{
			board[0][y].setColor(currSquare);
			currSquare =!currSquare;
		}
		currSquare = false;
		for(int y=0;y!=8;y++)
		{
			for(int x =1;x!=8;x++)
			{
				board[x][y].setColor(currSquare);
				currSquare =!currSquare;
			}
		}
		
		//Set the pieces
		
		whiteKing = new King(false,board[3][7]);
		blackKing = new King(true,board[4][0]);
		
		for(int x =0;x<8;x++)
		{
			board[x][1].setPiece(new Pawn(true,board[x][1]));
			board[x][6].setPiece(new Pawn(false,board[x][6]));
		}
		board[0][0].setPiece(new Rook(true,board[0][0]));
		board[1][0].setPiece(new Knight(true,board[1][0]));
		board[2][0].setPiece(new Bishop(true,board[2][0]));
		board[3][0].setPiece(new Queen(true,board[3][0]));
		board[4][0].setPiece(blackKing);
		board[5][0].setPiece(new Bishop(true,board[5][0]));
		board[6][0].setPiece(new Knight(true,board[6][0]));
		board[7][0].setPiece(new Rook(true,board[7][0]));
		
		board[0][7].setPiece(new Rook(false,board[0][7]));
		board[1][7].setPiece(new Knight(false,board[1][7]));
		board[2][7].setPiece(new Bishop(false,board[2][7]));
		board[3][7].setPiece(whiteKing);
		board[4][7].setPiece(new Queen(false,board[4][7]));
		board[5][7].setPiece(new Bishop(false,board[5][7]));
		board[6][7].setPiece(new Knight(false,board[6][7]));
		board[7][7].setPiece(new Rook(false,board[7][7]));
		
		
		cr = new CheckRules(this);
		
		setTitle("WHITE's move");
		setVisible(true);
	}
	
	public Tile[][] getTiles()
	{
		return board;
	}
	
	public Piece getSelectedPiece()
	{
		return selectedPiece;
	}
	
	public void setSelectedPiece(Piece piece)
	{
		selectedPiece = piece;
	}
	
	public boolean getPlayerTurn()
	{
		return playerTurn;
	}
	
	public King getWhiteKing()
	{
		return whiteKing;
	}
	
	public King getBlackKing()
	{
		return blackKing;
	}
	
	public CheckRules getRuleChecker()
	{
		return cr;
	}
	
	public void nextTurn()
	{
		selectedPiece = null;
		playerTurn = !playerTurn;
		
		for(int y=0;y!=8;y++)
		{
			for (int x=0;x!=8;x++)
			{
				board[x][y].cleanHighlight();
			}
		}
		
		if(!playerTurn) setTitle("WHITE's move");
		else setTitle("BLACK's move");
		
		//Check warning
		if(cr.checkForThreatsOnKing(playerTurn))
		{
			if(playerTurn) blackKing.getCurrentTile().setWarningColor();
			else whiteKing.getCurrentTile().setWarningColor();
		}
		
	}
}

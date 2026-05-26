import java.awt.*;
import javax.swing.*;

public class Board extends TicTacToe{
    private JButton[][] board = new JButton[3][3];

    public Board() {
        super.editBoardPanel(3,3,Color.darkGray);
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font ("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setContentAreaFilled(false);
                tile.setOpaque(true);
                
                board[r][c] = tile;
                super.editBoardPanel(tile);
            }
        }
    }

    // getters and setters
    public String getTile(int r, int c) {
        return board[r][c].getText();
    }

    public JButton getTile(int r, int c, String character) {
        return board[r][c];
    }

    public void setTile(int r, int c, String symbol) {
        board[r][c].setText(symbol);
    }

    // check methods
    public void checkWinner() // checks who wins
    {
        if (super.getTurns() == 9) {
            super.setLabelText("Tie!");
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
        }

        // check horizontally
        for (int r = 0; r < 3; r++) {
            String firstItem = board[r][0].getText();
            if (firstItem.equals("")) {
                r++;
            } else {
                if (board[r][1].getText().equals(firstItem) && board[r][2].getText().equals(firstItem))
                {
                    for (int i = 0; i < 3; i++){
                        setWinner(board[r][i]);
                    }
                    super.changeGameOver(true);
                }
            }
        }

        // check vertically
        for (int c = 0; c < 3; c++) {
            String firstItem = board[0][c].getText();
            if (firstItem.equals("")) {
                c++;
            } else {
                if (board[1][c].getText().equals(firstItem) && board[2][c].getText().equals(firstItem))
                {
                    for (int i = 0; i < 3; i++){
                        setWinner(board[i][c]);
                    }
                    super.changeGameOver(true);
                }
            }
        }

        // check diagonally
        if (!board[0][0].getText().equals("") &&
        board[0][0].getText().equals(board[1][1].getText()) &&
        board[0][0].getText().equals(board[2][2].getText()))
        {
            for (int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            super.changeGameOver(true);
        }

        if (!board[0][2].getText().equals("") &&
        board[0][2].getText().equals(board[1][1].getText()) &&
        board[0][2].getText().equals(board[2][0].getText()))
        {
            setWinner(board[0][2]); 
            setWinner(board[1][1]);
            setWinner(board[2][0]);            
            super.changeGameOver(true);
        } 
    }

    public void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.green);
        super.setLabelText("Click anywhere to restart!");
    }

    public void setTie(JButton tile)
    {
        tile.setForeground(Color.gray);
        tile.setBackground(Color.gray);
        super.setLabelText("Click anywhere to restart!");
    }

    public void resetGame() {
        setTurns(0);
        super.setCurrentPlayer(1);
        super.changeGameOver(true);
        super.setLabelText("Tic Tac Toe");

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.darkGray);
                board[r][c].setForeground(Color.BLACK);
            }
        }
    }

}

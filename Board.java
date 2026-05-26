import java.awt.*;
import javax.swing.*;

public class Board {
    private JButton[][] tiles = new JButton[3][3];
    private JPanel boardPanel = new JPanel();

    public Board() {
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setContentAreaFilled(false);
                tile.setOpaque(true);
                tiles[r][c] = tile;
                boardPanel.add(tile);
            }
        }
    }

    // getters
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JButton getTile(int r, int c) {
        return tiles[r][c];
    }

    public String getTileText(int r, int c) {
        return tiles[r][c].getText();
    }

    public void setTileText(int r, int c, String symbol) {
        tiles[r][c].setText(symbol);
    }

    // returns a 2D array of the current board state as strings
    public String[][] getBoardState() {
        String[][] state = new String[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                state[r][c] = tiles[r][c].getText();
            }
        }
        return state;
    }

    public void highlightWinner(int r, int c) {
        tiles[r][c].setForeground(Color.green);
        tiles[r][c].setBackground(Color.green);
    }

    public void highlightTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c].setForeground(Color.gray);
                tiles[r][c].setBackground(Color.gray);
            }
        }
    }

    public void reset() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tiles[r][c].setText("");
                tiles[r][c].setBackground(Color.darkGray);
                tiles[r][c].setForeground(Color.BLACK);
            }
        }
    }
}

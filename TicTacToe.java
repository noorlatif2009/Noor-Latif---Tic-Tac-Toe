import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    private int boardWidth = 600;
    private int boardHeight = 650;

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JLabel textLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private Board board = new Board();

    private JPanel boardPanel = new JPanel();

     // to keep track of each button
    private int currentPlayer = 1;

    private int turns = 0;

    private boolean gameOver = false;

    public TicTacToe() { // constructor/game loop
        // Creates Window settings (i.e. size, resizibility)
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // JPanel Visuals
        textLabel.setBackground(Color.darkGray); // background color
        textLabel.setForeground(Color.white); // text color
        textLabel.setFont(new Font("Arial", Font.BOLD, 50)); // text font and size
        textLabel.setHorizontalAlignment(JLabel.CENTER); // centers text
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        // Pushes the header at the top of the window
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Creates the buttons
        frame.add(boardPanel);
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board.getTile(r, c, "tile").addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            board.resetGame();
                            return;
                        }
                        JButton tile = (JButton) e.getSource();
                        textLabel.setText(currentPlayer + "'s Turn");
                        if (tile.getText().equals("")){
                            board.setTile(r, c, "0");
                            turns++;
                            board.checkWinner();
                            if (currentPlayer == 1) {
                                currentPlayer = 0;
                            } else {
                                currentPlayer = 1;
                            }
                        }
                        
                    }
                });
            }
        }
    }

    // getters and setters
    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void changeGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setLabelText(String newText) {
        textLabel.setText(newText);
    }

    public void setCurrentPlayer (int player) {
        this.currentPlayer = player;
    }

    public void editBoardPanel(int gridWidth, int gridHeight, Color color) {
        boardPanel.setLayout(new GridLayout(gridWidth, gridHeight));
        boardPanel.setBackground(color);
    }

    public void editBoardPanel(JButton button) {
        boardPanel.add(button);
    }

}

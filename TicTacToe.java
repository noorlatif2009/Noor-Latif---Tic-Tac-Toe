import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3]; // to keep track of each button
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

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
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                // Makes tile
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                // Sets tile visuals
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.BLACK);
                tile.setFont(new Font ("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.setContentAreaFilled(false);
                tile.setOpaque(true);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) { return; }
                        JButton tile = (JButton) e.getSource();
                        textLabel.setText(currentPlayer + "'s Turn");
                        if (tile.getText().equals("")){
                            tile.setText(currentPlayer);
                            System.out.println("MADE IT");
                            checkWinner();
                            if (currentPlayer.equals("X")) {
                                currentPlayer = playerO;
                            } else {
                                currentPlayer = playerX;
                            }
                        }
                        
                    }
                });
            }
        }
    }

    public void checkWinner() // checks who wins
    {
        // check horizontally
        for (int r = 0; r < 3; r++) {
            String firstItem = board[r][0].getText();
            if (firstItem.equals("")) {
                r++;
            } else {
                if (board[r][1].getText().equals(firstItem) && board[r][2].getText().equals(firstItem))
                {
                    gameOver = true;
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
                    gameOver = true;
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
            gameOver = true;
        }

        if (!board[0][2].getText().equals("") &&
        board[0][2].getText().equals(board[1][1].getText()) &&
        board[0][2].getText().equals(board[2][0].getText()))
        {
            setWinner(board[0][2]); 
            setWinner(board[1][1]);
            setWinner(board[2][0]);            
            gameOver = true;
        } 
    }

    public void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.green);
        textLabel.setText(currentPlayer + " is the winner.");
    }
}

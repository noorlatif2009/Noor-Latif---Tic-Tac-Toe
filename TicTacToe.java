import java.awt.*;
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

    public TicTacToe() {
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

                // checks actions
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = e.getSource();
                    }
                });
            }
        }
    }
}

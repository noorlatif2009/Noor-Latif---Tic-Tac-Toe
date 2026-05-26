import java.awt.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    
    JPanel[][] board = new JPanel[3][3];

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

        //
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = new JPanel();
                board[r][c].setBackground(Color.darkGray);
                frame.add(board[r][c]);
            }
        }
    }
}
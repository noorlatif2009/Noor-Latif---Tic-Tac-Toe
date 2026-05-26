import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class TicTacToe {
    private int boardWidth = 600;
    private int boardHeight = 700;

    private JFrame frame = new JFrame("Tic Tac Toe");
    private JLabel textLabel = new JLabel();
    private JLabel bottomLabel = new JLabel();
    private JPanel textPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private Board board = new Board();

    private Player player1 = new HumanPlayer("Player 1", 'X');
    private Player player2 = new ComputerPlayer('O');
    private Player currentPlayer = player1;

    private int turns = 0;
    private boolean gameOver = false;

    private ArrayList<String> winners = new ArrayList<String>();

    public TicTacToe() {
        // window settings
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // header label
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        bottomLabel.setBackground(Color.darkGray);
        bottomLabel.setForeground(Color.white);
        bottomLabel.setFont(new Font("Serif", Font.BOLD, 30));
        bottomLabel.setHorizontalAlignment(JLabel.CENTER);
        bottomLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(bottomLabel);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // add board
        frame.add(board.getBoardPanel(), BorderLayout.CENTER);

        // add listeners to each tile
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final int finalR = r;
                final int finalC = c;

                board.getTile(r, c).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) {
                            resetGame();
                            return;
                        }

                        boolean special = specialRound();

                        // only allow human player to click
                        if (!(currentPlayer instanceof HumanPlayer)) {
                            return;
                        }

                        if (board.getTileText(finalR, finalC).equals("")) {
                            board.setTileText(finalR, finalC, String.valueOf(currentPlayer.getSymbol()));
                            turns++;
                            checkWinner(special);
                            if (!gameOver) {
                                switchPlayer();
                                // if next player is computer, make its move
                                if (currentPlayer instanceof ComputerPlayer) {
                                    computerMove(special);
                                }
                            }
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    // switches to the other player and updates the label
    private void switchPlayer() {
        if (currentPlayer.equals(player1)){
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        textLabel.setText(currentPlayer.getName() + "'s Turn");
    }

    // handles the computer's random move
    private void computerMove(boolean special) {
        ComputerPlayer computer = (ComputerPlayer) currentPlayer;
        int[] move = computer.getRandomMove(board.getBoardState());
       
        long start = System.currentTimeMillis();
        long current = System.currentTimeMillis();

        while (current < start+2){
            current = System.currentTimeMillis();
        }

        board.setTileText(move[0], move[1], String.valueOf(currentPlayer.getSymbol()));
        turns++;
        checkWinner(special);
        if (!gameOver) {
            switchPlayer();
        }
    }

    // checks all win conditions and tie
    private void checkWinner(boolean special) {
        // check horizontally
        for (int r = 0; r < 3; r++) {
            String first = board.getTileText(r, 0);
            if (!first.equals("") &&
                board.getTileText(r, 1).equals(first) &&
                board.getTileText(r, 2).equals(first)) {
                for (int i = 0; i < 3; i++) {
                    board.highlightWinner(r, i);
                }

                if (special) {
                    int loops = doublePoints(getWinCount(currentPlayer));
                    for (int i = 0; i < loops; i++) {
                        winners.add(currentPlayer.getName());
                    }
                } else {
                    winners.add(currentPlayer.getName());
                }

                endGame(currentPlayer.getName() + " wins!");
                return;
            }
        }

        // check vertically
        for (int c = 0; c < 3; c++) {
            String first = board.getTileText(0, c);
            if (!first.equals("") &&
                board.getTileText(1, c).equals(first) &&
                board.getTileText(2, c).equals(first)) {
                for (int i = 0; i < 3; i++) { board.highlightWinner(i, c); }

                if (special) {
                    int loops = doublePoints(getWinCount(currentPlayer));
                    for (int i = 0; i < loops; i++) {
                        winners.add(currentPlayer.getName());
                    }
                } else {
                    winners.add(currentPlayer.getName());
                }

                endGame(currentPlayer.getName() + " wins!");
                return;
            }
        }

        // check diagonals
        String mid = board.getTileText(1, 1);
        if (!mid.equals("") &&
            board.getTileText(0, 0).equals(mid) &&
            board.getTileText(2, 2).equals(mid)) {
            for (int i = 0; i < 3; i++) {
                board.highlightWinner(i, i);
            }

            if (special) {
                int loops = doublePoints(getWinCount(currentPlayer));
                for (int i = 0; i < loops; i++) {
                    winners.add(currentPlayer.getName());
                }
            } else {
                winners.add(currentPlayer.getName());
            }
            
            endGame(currentPlayer.getName() + " wins!");
            return;
        }

        // check diagonal top-right to bottom-left
        if (!mid.equals("") &&
            board.getTileText(0, 2).equals(mid) &&
            board.getTileText(2, 0).equals(mid)) {
            board.highlightWinner(0, 2);
            board.highlightWinner(1, 1);
            board.highlightWinner(2, 0);
            
            if (special) {
                int loops = doublePoints(getWinCount(currentPlayer));
                for (int i = 0; i < loops; i++) {
                    winners.add(currentPlayer.getName());
                }
            } else {
                winners.add(currentPlayer.getName());
            }

            endGame(currentPlayer.getName() + " wins!");
            return;
        }

        // check tie
        if (turns == 9) {
            board.highlightTie();
            endGame("It's a tie!");
        }
    }

    private void endGame(String message) {
        textLabel.setText(message + " Click to restart!");
        gameOver = true;
        bottomLabel.setText(player1.getName() + " wins: " + getWinCount(player1) + "  |  " + player2.getName() + " wins: " + getWinCount(player2));
    }

    private void resetGame() { // resets game after win or tie
        turns = 0;
        currentPlayer = player1;
        gameOver = false;
        textLabel.setText("Tic Tac Toe");
        board.reset();
    }

    // counts how many times a player's name appears in the winners list
    private int getWinCount(Player player) {
        int count = 0;
        for (String winner : winners) {
            if (winner.equals(player.getName())) {
                count++;
            }
        }
        return count;
    }

    private boolean specialRound() {
        int special = ((int) Math.random()) + 1;

        if (special % 2 == 0) {
            textLabel.setText("Special Round! Points are doubled");

            return true;
        }
        return false;
    }

    private int doublePoints(int num) {
        if (num == 0) {
            return 1;
        } else {
            return 1 + doublePoints(num-1);
        }
    }
}

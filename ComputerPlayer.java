import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random = new Random();

    public ComputerPlayer(char symbol) {
        super("Computer", symbol);
    }

    @Override
    public String getMove() {
        return "Picking random move...";
    }

    // returns a random empty cell as {row, col}
    public int[] getRandomMove(String[][] boardState) {
        int r, c;
        do {
            r = random.nextInt(3);
            c = random.nextInt(3);
        } while (!boardState[r][c].equals(""));
        return new int[]{r, c};
    }
}
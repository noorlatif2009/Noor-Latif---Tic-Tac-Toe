public class HumanPlayer extends Player {

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public String getMove() {
        return "Waiting for click...";
    }
}
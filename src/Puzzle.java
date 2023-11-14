public class Puzzle {
    private final String name;
    private final String description;
    private final String answer;
    private final int attemptsAllowed;
    private int remainingAttempts;


    public Puzzle(String name, String description, String answer, int attemptsAllowed) {
        this.name = name;
        this.description = description;
        this.answer = answer;
        this.attemptsAllowed = attemptsAllowed;
        this.remainingAttempts = attemptsAllowed; // Initialize remainingAttempts with attemptsAllowed
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", answer='" + answer + '\'' +
                ", attemptsAllowed=" + attemptsAllowed +
                '}';
    }

    public boolean checkAnswer(String playerAnswer) {
        boolean isCorrect = answer.equalsIgnoreCase(playerAnswer);
        if (!isCorrect) {
            remainingAttempts--;
        }
        return isCorrect;
    }

    public int getAttemptsAllowed() {
        return attemptsAllowed;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public void setRemainingAttempts(int remainingAttempts) {
        this.remainingAttempts = remainingAttempts;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}


package ch.jeda.puzzle1;

// ------------------------------------------------------------------------

public class Question {

    private int number;
    private String answer;
    private String[] hints;
    private int usedHints;

    public Question(int number, String answer, String... hints) {
        this.number = number;
        this.answer = answer;
        this.hints = hints;
        this.usedHints = 0;
    }

// ------------------------------------------------------------------------

    public int getNumber() {
        return number;
    }

    boolean checkAnswer(String guess) {
        return answer.equals(guess);
    }

// ------------------------------------------------------------------------

    public int getScore() {
        return number * 10 - usedHints;
    }

    public String nextHint() {
        if (usedHints < hints.length) {
            usedHints = usedHints + 1;
            return hints[usedHints - 1];
        }
        else {
            return "No hints remaining.";
        }
    }

// ------------------------------------------------------------------------

}


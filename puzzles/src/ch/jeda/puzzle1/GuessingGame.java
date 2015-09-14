package ch.jeda.puzzle1;

import ch.jeda.*;
import ch.jeda.event.*;
import ch.jeda.ui.*;

// ------------------------------------------------------------------------
public class GuessingGame extends Program {

    private Question[] questions;

    @Override
    public void run() {
        questions = new Question[2];
        questions[0] = new Question(1, "Barak Obama", "A living person", "Looses his job on Jan 19, 2017", "Very powerful", "President of the United States");
        questions[1] = new Question(2, "Marie Curie", "Physicist", "French", "Discovered radioactivity");

        play();
    }

// ------------------------------------------------------------------------
    void play() {
        int score = 0;
        int i = 0;
        while (i < questions.length) {
            write("Question " + questions[i].getNumber(), "\n");
            write(questions[i].nextHint(), "\n");
            String answer = readString("Antwort");
            while (!questions[i].checkAnswer(answer)) {
                write(questions[i].nextHint(), "\n");
                answer = readString("Antwort");
            }

            score = score + questions[i].getScore();
            i = i + 1;
        }

        write("Game over, your score is ", score, "\n");
    }

// ------------------------------------------------------------------------
}

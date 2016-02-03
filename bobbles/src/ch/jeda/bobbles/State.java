package ch.jeda.bobbles;

public class State {

    private int state;

    public State(int state) {
        this.state = state;
    }

    public boolean isStarting() {
        return state == 0;
    }

    public boolean isRunning() {
        return state == 1;
    }

    public boolean hasEnded() {
        return state == 2;
    }

    public void start() {
        state = 0;
    }

    public void run() {
        state = 1;
    }

    public void end() {
        state = 2;
    }
}

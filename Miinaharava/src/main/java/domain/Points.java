package domain;

import application.Main;
import java.util.HashMap;

public class Points {

    private Alias alias;
    private int points;
    private HashMap<Alias, Integer> board;

    public Points(Alias alias, int points) {
        this.alias = alias;
        this.points = points;
        this.board = board;

    }

    public Alias getAlias() {
        return alias;
    }

    public int getPoints() {
        return points;
    }

    public void setAlias(Alias alias) {
        this.alias = alias;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Alias fetchTop5() {
        return null;

    }
}

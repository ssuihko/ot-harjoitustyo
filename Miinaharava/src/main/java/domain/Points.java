package domain;

import java.io.Serializable;

public class Points implements Serializable {

    private Alias alias;
    private int time;

    public Points(Alias alias, int time) {
        this.alias = alias;
        this.time = time;
    }

    public Alias getAlias() {
        return alias;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
   
}

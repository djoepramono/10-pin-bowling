package bowling.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    public List<Bowl> bowls = new ArrayList<Bowl>();
    public Integer maxPins = 10;
    public Integer knockedPins = 0;

    public Frame(List<Bowl> bowls) {
        this.bowls = bowls;
    }

    public void addBowl(Bowl bowl) {
        this.bowls.add(bowl);
    }
}

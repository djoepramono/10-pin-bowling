package bowling.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Bowl> bowls = new ArrayList<Bowl>();
    private Integer maxScore = 10;
    private Integer score;

    public Frame() {}

    public void addBowl(Bowl bowl) {
        this.bowls.add(bowl);
    }
}

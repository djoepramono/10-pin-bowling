package bowling.util;

import bowling.model.Bowl;

import java.util.List;

public final class ScoreUtil {

    public static Integer getTotalScore(List<Bowl> bowls, Integer countLimit) {
        Integer totalScore = 0;

        for (var i = 0; i < bowls.size(); i++) {
            Bowl bowl = bowls.get(i);

            if (i < countLimit) {
                if (bowl.display.equals("X")) {
                    totalScore = totalScore + bowl.knockedPins + getBowlKnockedPinsFromArrayList(bowls, i+1) + getBowlKnockedPinsFromArrayList(bowls, i+2);
                } else if (bowl.display.equals("/")) {
                    totalScore = totalScore + bowl.knockedPins + getBowlKnockedPinsFromArrayList(bowls, i+1);
                } else {
                    totalScore += bowl.knockedPins;
                }
            }
        }

        return totalScore;
    }

    private static Integer getBowlKnockedPinsFromArrayList(List<Bowl> bowls, Integer i) {
        try {
            return bowls.get(i).knockedPins;
        } catch (IndexOutOfBoundsException e){
            return 0;
        }
    }
}

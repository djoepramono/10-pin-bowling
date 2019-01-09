package bowling;

import bowling.model.Bowl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static bowling.util.ScoreUtil.getTotalScore;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameGetTotalScoreTest {

    @Test
    public void getTotalScoreWithSpare() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("/",9));
        bowls.add(new Bowl("2",2));
        Integer countLimit = 20;

        assertEquals((Integer) 14, getTotalScore(bowls,countLimit));
    }

    @Test
    public void getTotalScoreWithSpares() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("/",9));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("/",8));
        bowls.add(new Bowl("3",3));
        Integer countLimit = 20;

        assertEquals((Integer) 28, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithStrike() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("2",2));
        Integer countLimit = 20;

        assertEquals((Integer) 16, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithUncompleteStrike() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("1",1));
        Integer countLimit = 20;

        assertEquals((Integer) 12, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithStrikes() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("1",1));
        Integer countLimit = 20;

        assertEquals((Integer) 38, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithConsecutiveStrikes() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("X",10));
        Integer countLimit = 20;

        assertEquals((Integer) 90, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithMiss() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("3",3));
        Integer countLimit = 20;

        assertEquals((Integer) 6, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithMisses() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("3",3));
        Integer countLimit = 20;

        assertEquals((Integer) 4, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithCombinations() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("/",9));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("2",2));
        Integer countLimit = 20;

        assertEquals((Integer) 34, getTotalScore(bowls, countLimit));
    }

    @Test
    public void getTotalScoreWithExceededcountLimit() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("3",3));
        bowls.add(new Bowl("4",4));
        bowls.add(new Bowl("5",5));
        Integer countLimit = 3;

        assertEquals((Integer) 20, getTotalScore(bowls, countLimit));
    }
}

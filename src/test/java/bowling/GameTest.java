package bowling;

import bowling.model.Bowl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    // Get total score

    @Test
    public void getTotalScoreWithSpare() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("/",9));
        bowls.add(new Bowl("2",2));

        Game game = new Game();
        assertEquals((Integer) 14, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithSpares() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("/",9));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("/",8));
        bowls.add(new Bowl("3",3));

        Game game = new Game();
        assertEquals((Integer) 28, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithStrike() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("2",2));

        Game game = new Game();
        assertEquals((Integer) 16, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithUncompleteStrike() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("1",1));

        Game game = new Game();
        assertEquals((Integer) 12, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithStrikes() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("1",1));

        Game game = new Game();
        assertEquals((Integer) 36, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithMiss() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("2",2));
        bowls.add(new Bowl("3",3));

        Game game = new Game();
        assertEquals((Integer) 6, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithMisses() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("3",3));

        Game game = new Game();
        assertEquals((Integer) 4, game.getTotalScore(bowls));
    }

    @Test
    public void getTotalScoreWithCombinations() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        bowls.add(new Bowl("/",9));
        bowls.add(new Bowl("X",10));
        bowls.add(new Bowl("-",0));
        bowls.add(new Bowl("2",2));

        Game game = new Game();
        assertEquals((Integer) 34, game.getTotalScore(bowls));
    }
}

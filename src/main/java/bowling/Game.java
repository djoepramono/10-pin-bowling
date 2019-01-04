package bowling;

import java.util.stream.Stream;
import bowling.model.Bowl;
import bowling.model.Frame;
// import bowling.model.Score;

public class Game {

    // Stream.Builder<Bowl> bowler = Stream.builder();
    Stream<Bowl> bowls;
    Stream<Frame> frames;
    Integer bowlLimit;
    Bowl initialBowl = new Bowl("u");
    Integer score;

    public Game() {
        this.bowlLimit = 10000;
    }

    public void process(Bowl currentBowl) {
        bowls = Stream.iterate(initialBowl, ignored -> currentBowl);

        // frames.limit(bowlLimit).forEach(System.out::println);
    }

}

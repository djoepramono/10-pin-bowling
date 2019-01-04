package bowling;

import java.util.stream.Stream;
import bowling.model.Bowl;
import bowling.model.Frame;
// import bowling.model.Score;

public class Game {

    // Stream.Builder<Bowl> bowler = Stream.builder();
    Stream<Bowl> bowls;
    Stream<String> temp;
    Stream<Frame> frames;
    Integer bowlLimit;
    Bowl initialBowl = new Bowl("u");
    Integer score;

    public Game() {
        this.bowlLimit = 10;
    }

    public void process(Bowl currentBowl) {
        temp = Stream.iterate(initialBowl, ignored -> currentBowl)
            .map(bowl -> {System.out.println(bowl.display); return bowl.display;})
            // .limit(bowlLimit)
            ;
        // bowls.forEach(bowl -> System.out.println(bowl.display));
        // frames.limit(bowlLimit).forEach(System.out::println);
    }

}

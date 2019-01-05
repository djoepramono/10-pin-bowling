package bowling;

import java.util.stream.Stream;
import java.util.ArrayList;
import bowling.model.Bowl;
import bowling.model.Frame;
// import bowling.model.Score;

public class Game {

    // Stream.Builder<Bowl> bowler = Stream.builder();
    Stream<Frame> frames;
    Integer bowlPerFrame = 2;
    Integer bowlLimit;
    // Bowl initialBowl = new Bowl("u");
    Integer score;
    Frame currentFrame = new Frame(new ArrayList<Bowl>());

    public Game() {
        this.bowlLimit = 10;
    }

    public void process(Bowl currentBowl) {
        // Stream<Bowl> frameBowls = Stream.iterate(initialBowl, ignored -> currentBowl)
        //     .map(bowl -> { addBowltoFrame(frame, currentBowl); return bowl;} )
        //     .limit(bowlPerFrame)
        //     .collect(Collectors.toList());
        //     ;

        System.out.println("-----");
        System.out.println("Process");
        System.out.println(currentBowl.display);

        addBowltoFrame(currentFrame, currentBowl);
        currentFrame.bowls.forEach(x -> System.out.println(x.display));
        // bowls.forEach(bowl -> System.out.println(bowl.display));
        // frames.limit(bowlLimit).forEach(System.out::println);
    }

    public void addBowltoFrame(Frame frame, Bowl bowl) {
        if (frame.bowls.size() >= bowlPerFrame) {
            frame.bowls = new ArrayList<Bowl>();
        }
        System.out.print("Add bowl to current frame size: ");
        System.out.println(frame.bowls.size());
        frame.bowls.add(bowl);
    }

}

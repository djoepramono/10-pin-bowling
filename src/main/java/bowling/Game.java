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

    public void process(String entry) {
        // Stream<Bowl> frameBowls = Stream.iterate(initialBowl, ignored -> currentBowl)
        //     .map(bowl -> { addBowltoFrame(frame, currentBowl); return bowl;} )
        //     .limit(bowlPerFrame)
        //     .collect(Collectors.toList());
        //     ;

        System.out.println("-----");
        System.out.println("Process");
        System.out.println(entry);

        prepareFrame(currentFrame);
        addBowltoFrame(currentFrame, new Bowl(entry));
        currentFrame.bowls.forEach(x -> System.out.println(x.display));
        // bowls.forEach(bowl -> System.out.println(bowl.display));
        // frames.limit(bowlLimit).forEach(System.out::println);
    }

    public void prepareFrame(Frame frame) {
        if (frame.bowls.size() >= bowlPerFrame) {
            frame.bowls = new ArrayList<Bowl>();
        }
    }


    public Integer calculateBowlScore(Frame frame, String bowlDisplay) {
        Integer bowlScore;
        // Alternatively, regex match can be used here
        // But since it needs to be parsed anyway, try catch is better
        try {
            bowlScore = Integer.parseInt(bowlDisplay);
        } catch(NumberFormatException e) {
            switch(bowlDisplay) {
                case "-": bowlScore = 0; break;
                case "/": bowlScore = frame.maxScore - frame.score; break;
                case "X": bowlScore = frame.maxScore - frame.score; break;
                default: bowlScore = 0; // This line should not be reachable
            }
        }

        return bowlScore;
    }

    public void addBowltoFrame(Frame frame, Bowl bowl) {

        System.out.print("Add bowl to current frame size: ");
        System.out.println(frame.bowls.size());
        frame.bowls.add(bowl);
    }

}

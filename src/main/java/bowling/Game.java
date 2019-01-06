package bowling;

import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import bowling.model.Bowl;
import bowling.model.Frame;

public class Game {

    List<Frame> frames = new ArrayList<Frame>();
    Integer bowlPerFrame = 2;
    Integer bowlLimit;
    // Bowl initialBowl = new Bowl("u");
    Integer score;

    Frame frame = new Frame(new ArrayList<Bowl>()); // needed for the first run

    public Game() {
        this.bowlLimit = 10;
    }

    public void process(String entry) {

        // prepareFrame(currentFrame);

        System.out.println("-----");
        System.out.println("Process " + entry);

        Frame frame = addBowlToFrame(entry);



        // Bust
        if ((frame.bowls.size() >= bowlPerFrame) || (frame.knockedPins >= frame.maxPins)) {
            //reset the frame
            frames.add(frame);

            // Start debug
            frames.forEach(
                f -> {
                    System.out.println("Frame");
                    f.bowls.forEach(
                        b -> System.out.println("  display: " + b.display + " knocks: " + b.knockedPins)
                    );
                }
            );
            // End debug

            System.out.println("busted");
            frame = new Frame(new ArrayList<Bowl>());
        }

    }

    // Recursion
    private Frame addBowlToFrame(String bowlDisplay) {
        Integer bowlPerFrame = 2;

        frame.bowls.add(
            new Bowl(
                bowlDisplay,
                calculateKnockedPins(
                    frame.maxPins,
                    frame.knockedPins,
                    bowlDisplay
                )
            )
        );

        return frame;
    }

    // private void prepareFrame(Frame frame) {
    //     if ((frame.bowls.size() >= bowlPerFrame) || (frame.knockedPins >= frame.maxPins)) {
    //         frames.add(currentFrame);
    //         System.out.println("Added frame");
    //         // frame.bowls = new ArrayList<Bowl>();
    //     }
    // }


    private Integer calculateKnockedPins(Integer maxPins, Integer knockedPins, String bowlDisplay) {
        Integer bowlScore;
        // Alternatively, regex match can be used here
        // But since it needs to be parsed anyway, try catch is better
        try {
            bowlScore = Integer.parseInt(bowlDisplay);
        } catch(NumberFormatException e) {
            switch(bowlDisplay) {
                case "-": bowlScore = 0; break;
                case "/": bowlScore = maxPins - knockedPins; break;
                case "X": bowlScore = maxPins - knockedPins; break;
                default: bowlScore = 0;
            }
        }

        System.out.println("  calculated " + bowlDisplay + " as " + bowlScore);
        return bowlScore;
    }

}

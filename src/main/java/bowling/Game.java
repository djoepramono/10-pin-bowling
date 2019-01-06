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
    Frame currentFrame = new Frame(new ArrayList<Bowl>());

    public Game() {
        this.bowlLimit = 10;
    }

    public void process(String entry) {

        // prepareFrame(currentFrame);

        System.out.println("-----");
        System.out.println("Process " + entry);

        // Frame currentFrame = new Frame(new ArrayList<Bowl>());
        currentFrame.bowls.add(
            new Bowl(entry, calculateKnockedPins(
                currentFrame.maxPins,
                currentFrame.knockedPins,
                entry
            )
        ));

        if ((currentFrame.bowls.size() >= bowlPerFrame) || (currentFrame.knockedPins >= currentFrame.maxPins)) {
            frames.add(currentFrame);
            System.out.println("Added frame");
            System.out.println("  " + String.join(
                "-",
                currentFrame.bowls.stream().map(b->b.display).collect(Collectors.toList())
            ));
            currentFrame.bowls = new ArrayList<Bowl>();
        }

        //Debug purpose
        //Printout all bowls
        frames.forEach(
            f -> {
                System.out.println("Frame");
                f.bowls.forEach(
                    b -> System.out.println("  display: " + b.display + " knocks: " + b.knockedPins)
                );
            }
        );
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

        return bowlScore;
    }

}

package bowling;

import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import bowling.model.Bowl;
import bowling.model.Frame;
import bowling.util.FrameUtil;
import bowling.helper.FrameException;

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


        try {
            addBowlToFrame(frame, entry);
        } catch(FrameException e) {
            System.out.println(e.getMessage());
        }

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

            frame = new Frame(new ArrayList<Bowl>());
            System.out.println("frame reset");
        }

    }

    // What should happen if the entered bowl is not valid?
    // (e.g. 9 followed by 4 in a frame)
    // Should the program ignore bowl? or should the program throw an exception?
    // In this case I choose to the latter, in the absence of `Either` in Java
    private void addBowlToFrame(Frame frame, String bowlDisplay) throws FrameException {
        Integer knockedPins = FrameUtil.translateBowlDisplay(
            frame.maxPins,
            frame.knockedPins,
            bowlDisplay
        );

        if (knockedPins + frame.knockedPins <= frame.maxPins) {
            frame.bowls.add(
                new Bowl(
                    bowlDisplay,
                    knockedPins
                )
            );

            frame.knockedPins = knockedPins;
        } else {
            throw new FrameException("cant add " + knockedPins + " to " + frame.knockedPins);
        }

    }

}

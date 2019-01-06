package bowling;

import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import bowling.model.Bowl;
import bowling.model.Frame;
import bowling.util.FrameUtil;

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

        // Frame frame = addBowlToFrame(entry);
        addBowlToFrame(entry);

        // System.out.println(frame.knockedPins);


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

    private void addBowlToFrame(String bowlDisplay) {
        // Integer bowlPerFrame = 2;

        Integer knockedPins = FrameUtil.calculateKnockedPins(
            frame.maxPins,
            frame.knockedPins,
            bowlDisplay
        );

        frame.bowls.add(
            new Bowl(
                bowlDisplay,
                knockedPins
            )
        );

        frame.knockedPins = knockedPins;
    }

}

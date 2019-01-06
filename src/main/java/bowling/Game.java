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
    Integer score;

    Frame frame = new Frame(new ArrayList<Bowl>()); // needed for the first run

    public Game() {
        this.bowlLimit = 10;
    }

    public void process(String entry) throws FrameException {


        System.out.println("-----");
        System.out.println("Process " + entry);

        frame.addBowlToFrame(frame, entry);

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
}

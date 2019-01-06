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
    Integer frameLimit;
    Integer score;

    Frame frame = new Frame(new ArrayList<Bowl>()); // needed for the first run

    public Game() {
        this.frameLimit = 3;
    }

    public void process(String entry) throws FrameException {
        System.out.println("-----");
        System.out.println("Process " + entry);
        System.out.println("  no of frames : " + frames.size());

        if (frames.size() < frameLimit) {
            frame.addBowlToFrame(frame, entry);

            if ((frame.bowls.size() >= bowlPerFrame) || (frame.knockedPins >= frame.maxPins)) {
                frames.add(frame);
                debug();
                frame = new Frame(new ArrayList<Bowl>());
            }
        } else {
            // What should be done once the frame limit has been reached?
            // Throw an exception or just log an output?
            // I choose to do the latter
            System.out.println("cannot add anymore frame. You have reached the limit of " + frameLimit + " frames");
        }
    }

    public Integer getTotalScore() {
        return 100;
    }

    private void debug() {
        frames.forEach(
            f -> {
                System.out.println("Frame");
                f.bowls.forEach(
                    b -> System.out.println("  display: " + b.display + " knocks: " + b.knockedPins)
                );
            }
        );
    }
}

package bowling;

import bowling.helper.BowlingException;
import bowling.model.Bowl;
import bowling.model.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.util.FrameUtil.calculateTotalKnockedPins;

public class Game {

    List<Frame> frames = new ArrayList<Frame>();
    Integer bowlPerFrame = 2;
    Integer frameLimit;
    Boolean finished = false;

    Frame frame = new Frame(new ArrayList<Bowl>());

    public Game() {
        this.frameLimit = 10;
        frames.add(frame);
    }

    public Integer process(String entry) throws BowlingException {
        if (frames.size() <= frameLimit) {
            if (frame.getBowls().size() < bowlPerFrame && (calculateTotalKnockedPins(frame.getBowls()) < frame.getMaxPins())) {
                frame.addBowlToFrame(frame, entry);
            } else {
                frame = new Frame(new ArrayList<Bowl>());
                frames.add(frame);
                frame.addBowlToFrame(frame, entry);
            }

        } else {
            finished = true;
            // What should be done once the frame limit has been reached?
            // Throw an exception or just log an output?
            // I choose to do the latter
            System.out.println("WARNING: cannot add anymore frame. You have reached the limit of " + frameLimit + " frames");
        }

        return frames.size();
    }
}

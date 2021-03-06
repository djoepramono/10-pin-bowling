package bowling;

import bowling.helper.BowlingException;
import bowling.model.Bowl;
import bowling.model.Frame;

import java.util.ArrayList;
import java.util.List;

import static bowling.util.FrameUtil.calculateTotalKnockedPins;

public class Game {

    public final List<Frame> frames = new ArrayList<Frame>();
    public final Integer bowlPerFrame = 2;
    public final Integer frameLimit = 10;

    private Frame frame = new Frame();

    public Game() {
        frames.add(frame);
    }

    public Integer process(String entry) throws BowlingException {
        if (frames.size() <= frameLimit) {
            if (frame.getBowls().size() < bowlPerFrame && (calculateTotalKnockedPins(frame.getBowls()) < frame.getMaxPins())) {
                frame.addBowlToFrame(frame, entry);
            } else {
                frame = new Frame();
                frames.add(frame);
                frame.addBowlToFrame(frame, entry);
            }

        } else {
            // What should be done once the frame limit has been reached?
            // Throw an exception or just log an output?
            // I choose to do the latter
            System.out.println("WARNING: cannot add anymore frame. You have reached the limit of " + frameLimit + " frames");
        }

        return frames.size();
    }
}

package bowling.model;

import bowling.helper.FrameException;

import java.util.ArrayList;
import java.util.List;

import static bowling.util.FrameUtil.*;

public class Frame {
    private List<Bowl> bowls = new ArrayList<Bowl>();
    private Integer maxPins = 10;

    public Frame(List<Bowl> bowls) {
        this.bowls = bowls;
    }

    public List<Bowl> getBowls() {
        return this.bowls;
    }

    public Integer getMaxPins() {
        return this.maxPins;
    }

    public void addBowlToFrame(Frame frame, String bowlDisplay) throws FrameException {

        Integer totalKnockedPinsInThisFrame = calculateTotalKnockedPins(frame.getBowls());

        if (isValidNextBowl(frame.getBowls(), bowlDisplay)) {
            Integer knockedPins = translateBowlDisplay(
                frame.maxPins,
                totalKnockedPinsInThisFrame,
                bowlDisplay
            );

            if (knockedPins + totalKnockedPinsInThisFrame <= frame.maxPins) {
                frame.bowls.add(
                    new Bowl(
                        bowlDisplay,
                        knockedPins
                    )
                );

            } else {
                // What should happen if the entered bowl is not valid?
                // (e.g. 9 followed by 4 in a frame)
                // Should the program ignore bowl? or should the program throw an exception?
                // In this case I choose to do the latter, in the absence of `Either` in Java
                throw new FrameException("exceeding pin limit - cannot add " + knockedPins + " to " + totalKnockedPinsInThisFrame);
            }
        } else {
            // What should happen if the bowl sequence is not valid? (e.g. /9)
            // Should it fail silently or should it throw an exception?
            // In this case I choose to do the latter
            throw new FrameException("invalid sequence - cannot add " + bowlDisplay + " to frame");
        }
    }

}

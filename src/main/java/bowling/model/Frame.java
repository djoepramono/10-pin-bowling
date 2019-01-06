package bowling.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import bowling.helper.FrameException;

public class Frame {
    public List<Bowl> bowls = new ArrayList<Bowl>();
    public Integer maxPins = 10;
    public Integer knockedPins = 0;

    public Frame(List<Bowl> bowls) {
        this.bowls = bowls;
    }

    // What should happen if the entered bowl is not valid?
    // (e.g. 9 followed by 4 in a frame)
    // Should the program ignore bowl? or should the program throw an exception?
    // In this case I choose to the latter, in the absence of `Either` in Java
    public void addBowlToFrame(Frame frame, String bowlDisplay) throws FrameException {
        Integer knockedPins = translateBowlDisplay(
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

    public static Integer translateBowlDisplay(Integer frameMaxPins, Integer frameKnockedPins, String bowlDisplay) {
        Integer bowlScore;
        // Alternatively, regex match can be used here
        // But since it needs to be parsed anyway, try catch is better
        try {
            bowlScore = Integer.parseInt(bowlDisplay);
        } catch(NumberFormatException e) {
            switch(bowlDisplay) {
                case "-": bowlScore = 0; break;
                case "/": bowlScore = frameMaxPins - frameKnockedPins; break;
                case "X": bowlScore = frameMaxPins - frameKnockedPins; break;
                default: bowlScore = 0;
            }
        }

        System.out.println("  calculated " + bowlDisplay + " as " + bowlScore);
        return bowlScore;
    }
}

package bowling.util;

import bowling.model.Frame;

public final class FrameUtil {

    public static Integer calculateTotalKnockedPins(Frame frame) {
        return frame.bowls.stream()
            .map(bowl -> bowl.knockedPins)
            .reduce(0, (a, b) -> a + b);
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

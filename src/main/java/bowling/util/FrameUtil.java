package bowling.util;

import bowling.model.Frame;

public final class FrameUtil {

    public static Integer calculateTotalKnockedPins(Frame frame) {
        return frame.bowls.stream()
            .map(bowl -> bowl.knockedPins)
            .reduce(0, (a, b) -> a + b);
    }

    public static Integer calculateKnockedPins(Integer maxPins, Integer knockedPins, String bowlDisplay) {
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

        System.out.println("  calculated " + bowlDisplay + " as " + bowlScore);
        return bowlScore;
    }
}

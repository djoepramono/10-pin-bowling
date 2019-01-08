package bowling.util;

import bowling.model.Frame;

public final class FrameUtil {

    // Avoid throwing Exception whenever necessary
    // Otherwise handle Exception as quickly as possible
    private static Boolean isNumeric(String str)
    {
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    public static Integer calculateTotalKnockedPins(Frame frame) { //todo just list of bowls to most
        return frame.getBowls().stream()
            .map(bowl -> bowl.knockedPins)
            .reduce(0, (a, b) -> a + b);
    }

    public static Integer translateBowlDisplay(Integer frameMaxPins, Integer frameKnockedPins, String bowlDisplay) {
        Integer bowlScore;
        if (isNumeric(bowlDisplay)) {
            bowlScore = Integer.parseInt(bowlDisplay);
        } else {
            switch(bowlDisplay) {
                case "-": bowlScore = 0; break;
                case "/": bowlScore = frameMaxPins - frameKnockedPins; break;
                case "X": bowlScore = frameMaxPins - frameKnockedPins; break;
                default: bowlScore = 0;
            }
        }

        return bowlScore;
    }

    // Function to check if the display score is valid given the existing display score in a frame
    // e.g. 2/ is valid, but 2X is not valid
    // Atm ignore all numbers, as we check it in other function, but we might need to revisit
    // Thankfully we have a rule where there's maximum 2 bowls per frame, so validation is a little easier
    // This doesn't validate the frame maximum pins
    public static Boolean isValidNextBowl(Frame frame, String bowlDisplay) {
        Boolean isValid = false;

        if (isNumeric(bowlDisplay)) {
            isValid = true;
        } else {
            switch(bowlDisplay) {
                case "-":
                    // always valid
                    isValid = true;
                    break;
                case "/":
                    // only valid when:
                    // - if there's already a `-`
                    if (frame.getBowls().stream().map(b -> b.display).filter(s -> s.equals("-")).count() > 0) {
                        isValid = true;
                        // - if there's already a integer in the frame
                    } else if(frame.getBowls().stream().filter(b -> isNumeric(b.display)).count() > 0) {
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                    break;
                case "X":
                    // valid if the frame is empty
                    isValid = frame.getBowls().isEmpty();
                    break;
                default:
                    isValid = false;
            }
        }

        return isValid;
    }
}

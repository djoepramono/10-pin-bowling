package bowling.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import bowling.helper.FrameException;

public class Frame {
    public List<Bowl> bowls = new ArrayList<Bowl>();
    public Integer maxPins = 10;

    public Frame(List<Bowl> bowls) {
        this.bowls = bowls;
    }

    public static Integer calculateTotalKnockedPins(Frame frame) {
        return frame.bowls.stream()
            .map(bowl -> bowl.knockedPins)
            .reduce(0, (a, b) -> a + b);
    }

    public void addBowlToFrame(Frame frame, String bowlDisplay) throws FrameException {

        Integer totalKnockedPinsInThisFrame = frame.calculateTotalKnockedPins(frame);

        if (isValidNextBowl(frame, bowlDisplay)) {
            System.out.println("valid sequence");
            Integer knockedPins = translateBowlDisplay(
                frame.maxPins,
                totalKnockedPinsInThisFrame,
                bowlDisplay
            );

            if (knockedPins + totalKnockedPinsInThisFrame <= frame.maxPins) {
                System.out.println(knockedPins + " " + totalKnockedPinsInThisFrame + " " + frame.maxPins);
                frame.bowls.forEach(b -> System.out.println(b.display + " " + b.knockedPins));
                frame.bowls.add(
                    new Bowl(
                        bowlDisplay,
                        knockedPins
                    )
                );

            } else {
                System.out.println("exceeding pin limit");
                // What should happen if the entered bowl is not valid?
                // (e.g. 9 followed by 4 in a frame)
                // Should the program ignore bowl? or should the program throw an exception?
                // In this case I choose to do the latter, in the absence of `Either` in Java
                throw new FrameException("exceeding pin limit - cannot add " + knockedPins + " to " + frame.calculateTotalKnockedPins(frame));
            }
        } else {
            System.out.println("invalid sequence");
//            frame.bowls.forEach(b -> System.out.println(b.display + " " + b.knockedPins));
            // What should happen if the bowl sequence is not valid? (e.g. /9)
            // Should it fail silently or should it throw an exception?
            // In this case I choose to do the latter
            throw new FrameException("invalid sequence - cannot add " + bowlDisplay + " to frame");
        }
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
            //ignore for now
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
                    if (frame.bowls.stream().map(b -> b.display).filter(s -> s == "-").count() > 0) {
                        isValid = true;
                    // - if there's already a integer in the frame
                    } else if(frame.bowls.stream().filter(b -> isNumeric(b.display)).count() > 0) {
                        isValid = true;
                    } else {
                        isValid = false;
                    }
                    break;
                case "X":
                    // valid if the frame is empty
                    isValid = frame.bowls.isEmpty();
                    break;
                default:
                    isValid = false;
            }
        }

        return isValid;
    }

    // I can't believe this "PURE" function is not in a standard library ...
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
}

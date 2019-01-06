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
            // What should happen if the entered bowl is not valid?
            // (e.g. 9 followed by 4 in a frame)
            // Should the program ignore bowl? or should the program throw an exception?
            // In this case I choose to the latter, in the absence of `Either` in Java
            throw new FrameException("cannot add " + knockedPins + " to " + frame.knockedPins);
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

    // Function to check if the display score is valid given the existing display score in a frame
    // e.g. 2/ is valid, but 2X is not valid
    // Atm ignore all numbers, as we check it in other function, but we might need to revisit
    // Thankfully we have a rule where there's maximum 2 bowls per frame, so validation is a little easier
    public static Boolean isValidNextBowl(Frame frame, String bowlDisplay) {
        Boolean isValid = false;
        // try {
        //     bowlScore = Integer.parseInt(bowlDisplay);
        // } catch(NumberFormatException e) {
        //     switch(bowlDisplay) {
        //         case "-": isValid = true; break;
        //         case "/":
        //             //if there's already a integer in the frame, then it's valid, otherwise invalid
        //             if Frame.bowls.stream().map(b -> parseInt(b.knockedPins));
        //             break;
        //         case "X": bowlScore = frameMaxPins - frameKnockedPins; break;
        //         default: bowlScore = 0;
        //     }
        // }

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
                    // - if there's already a integer in the frame
                    isValid = false;
                    isValid = frame.bowls.stream()
                        .map(b -> isNumeric(b.display))
                        .reduce(false, (a, b) -> a ); // check again
                    // - if there's already a `-`
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

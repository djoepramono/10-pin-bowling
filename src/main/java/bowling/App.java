package bowling;

import bowling.helper.BowlingException;

import java.util.stream.Stream;

import static bowling.util.ScoreUtil.getAllBowls;
import static bowling.util.ScoreUtil.getTotalScore;

public class App {
    public static void main(String... args) {
        try {
            Integer totalScore = process(args);
            System.out.println("The total score is " + totalScore);
        } catch(BowlingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static Integer process(String... args) throws BowlingException {

        // This game is built in such a way that it accepts a single character string
        // Thus we need to get the arguments coming to this App into an array of String
        String[] entries = Stream.of(args)
            .flatMap(a -> Stream.of(a.split("(?!^)")))
            .toArray(String[]::new);


        Integer totalScore = 0;
        Game game = new Game();

        for(int i=0; i < entries.length; i++){
            if (validateStringEntry(entries[i])) {
                game.process(entries[i]);
            } else
            {
                throw new BowlingException("The entry " + entries[i] + " is not a valid entry");
            }
        }

        totalScore = getTotalScore(getAllBowls(game.frames), game.bowlPerFrame * game.frameLimit);

        return totalScore;
    }

    private static Boolean validateStringEntry(String entry) {
        return entry.matches("^[1-9|X|\\/|-]$");
    }
}

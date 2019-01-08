package bowling;

import bowling.helper.FrameException;

import java.util.stream.Stream;

public class App {
    public static void main(String... args) {
        try {
            Integer totalScore = process(args);
            System.out.println("The total score is " + totalScore);
        } catch(FrameException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Integer process(String... args) throws FrameException{

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
                throw new FrameException("The entry " + entries[i] + " is not a valid entry");
            }
        }

        totalScore = game.getTotalScore(game.getAllBowls(game.frames));

        return totalScore;
    }

    public static Boolean validateStringEntry(String entry) {
        // this is a string match you don't need `/ /g`
        return entry.matches("^[1-9|X|\\/|-]$");
    }
}

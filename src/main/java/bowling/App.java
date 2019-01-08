package bowling;

import bowling.helper.FrameException;

import java.util.stream.Stream;

public class App {
    public static void main(String... args) {

        // This game is built in such a way that it accepts a single character string
        // Thus we need to get the arguments coming to this App into an array of String
        String[] entries = Stream.of(args)
            .flatMap(a -> Stream.of(a.split("(?!^)")))
            .toArray(String[]::new);


        // String[] entries = {"1","3","X","9","/","4","2"};
        // String[] entries = {"1","s","X","9","/","4"};
//         String[] entries = {"-","/","1","2","X","9","5","4"};
//        String[] entries = {"-","/","1","2","X","2","5","4"}; // correct 42 not 38
//        String[] entries = {"-","/","1","2","X","2"}; // correct 28 not 24
//        String[] entries = {"-","/","1","2","X","2","5"}; // correct 38 not 24

//        String[] entries = {"X","/","X","9","/","4"};

        try {
            Game game = new Game();
            for(int i=0; i < entries.length; i++){
                if (validateStringEntry(entries[i])) {
                    game.process(entries[i]);
                    System.out.println("The total score is " + game.getTotalScore(game.getAllBowls(game.frames)));
                } else
                {
                    System.out.println("The entry " + entries[i] + " is not a valid string");
                    break;
                }
            }
        } catch(FrameException e) {
            System.out.println(e.getMessage());
        }

    }

//    public Array<String> parseInput()

    private static Boolean validateStringEntry(String entry) {
        // this is a string match you don't need `/ /g`
        return entry.matches("^[1-9|X|\\/|-]$");
    }
}

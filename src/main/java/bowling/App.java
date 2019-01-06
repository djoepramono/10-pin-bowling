package bowling;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import bowling.model.Bowl;
import java.util.stream.Stream;
import bowling.helper.FrameException;

public class App {
    public static void main(String... args) {

        Game game = new Game();

        // String[] entries = {"1","3","X","9","/","4"};
        // String[] entries = {"1","s","X","9","/","4"};
        String[] entries = {"1","3","X","9","5","4"};

        try {
            for(int i=0; i < entries.length; i++){
                if (validateStringEntry(entries[i])) {
                    game.process(entries[i]);
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

    private static Boolean validateStringEntry(String entry) {
        // this is a string match you don't need `/ /g`
        return entry.matches("^[0-9|X|\\/|-]$");
    }
}

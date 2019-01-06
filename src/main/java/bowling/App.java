package bowling;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import bowling.model.Bowl;
import java.util.stream.Stream;

public class App {
    public static void main(String... args) {
        System.out.println("hello");

        Game game = new Game();

        String[] entries = {"1","3","X","9","/","4"};

        for(int i=0; i < entries.length; i++){
            if (validateStringEntry(entries[i])) {
                game.process(entries[i]);
            } else
            {
                System.out.println("The entry " + entries[i] + " is not a valid string");
                break;
            }
        }
    }

    private static Boolean validateStringEntry(String entry) {
        // this is a string match you don't need `/ /g`
        return entry.matches("^[0-9|X|\\/|-]$");
    }
}

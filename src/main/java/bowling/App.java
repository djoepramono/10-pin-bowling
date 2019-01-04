package bowling;

// import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
// import java.util.stream.Collectors;
// import bowling.model.Score;
import bowling.model.Bowl;
// import bowling.model.Frame;

import java.util.stream.Stream;

public class App {
    public static void main(String... args) {
        System.out.println("hello");
                
        Game game = new Game();

        List<Bowl> bowls = List.of(
           new Bowl("1"),
           new Bowl("2"),
           new Bowl("3")
        );
        bowls.forEach(bowl -> game.process(bowl));        
    }
}

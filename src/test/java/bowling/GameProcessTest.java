package bowling;

import bowling.helper.BowlingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameProcessTest {

    @Test
    public void addingFrameIntoNewGame() throws BowlingException {
        String bowlDisplay = "X";

        Game game = new Game();
        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 1, noOfFrames);
    }

    @Test
    public void addingFrameIntoGameWithExistingUnfinishedFrame() throws BowlingException {
        String bowlDisplay = "2";

        Game game = new Game();
        game.process(bowlDisplay);

        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 1, noOfFrames);
    }

    @Test
    public void addingFrameIntoGameWithExistingFinishedFrame() throws BowlingException {
        String bowlDisplay = "2";

        Game game = new Game();
        game.process(bowlDisplay);
        game.process(bowlDisplay);

        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 2, noOfFrames);
    }

    @Test
    public void addingFrameIntoGameWithExistingStrikeFrame() throws BowlingException {
        String bowlDisplay = "2";

        Game game = new Game();
        game.process("X");

        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 2, noOfFrames);
    }

}

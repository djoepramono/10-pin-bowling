package bowling;

import bowling.helper.FrameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameProcessTest {

    @Test
    public void addingFrameIntoNewGame() throws FrameException {
        String bowlDisplay = "X";

        Game game = new Game();
        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 1, noOfFrames);
    }

    @Test
    public void addingFrameIntoGameWithExistingUnfinishedFrame() throws FrameException {
        String bowlDisplay = "2";

        Game game = new Game();
        game.process(bowlDisplay);

        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 1, noOfFrames);
    }

    @Test
    public void addingFrameIntoGameWithExistingFinishedFrame() throws FrameException {
        String bowlDisplay = "2";

        Game game = new Game();
        game.process(bowlDisplay);
        game.process(bowlDisplay);

        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 2, noOfFrames);
    }

    @Test
    public void addingFrameIntoGameWithExistingStrikeFrame() throws FrameException {
        String bowlDisplay = "2";

        Game game = new Game();
        game.process("X");

        Integer noOfFrames = game.process(bowlDisplay);
        assertEquals((Integer) 2, noOfFrames);
    }

}

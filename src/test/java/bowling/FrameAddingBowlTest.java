package bowling;

import bowling.helper.BowlingException;
import bowling.model.Bowl;
import bowling.model.Frame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FrameAddingBowlTest {

    @Test
    public void addingNumericIntoEmptyFrame() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "3";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingNumericIntoNonEmptyFrame() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "3";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }


    @Test
    public void addingStrikeIntoNonEmptyFrame() {
        List<Bowl> bowls = Arrays.asList(new Bowl("1",1));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "X";

        assertThrows(BowlingException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);

        });
    }

    @Test
    public void addingStrikeIntoEmptyFrame() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "X";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoFrameWithAMiss() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("-",0));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "/";

        frame.addBowlToFrame(frame, bowlDisplay);


        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoFrameWithKnockedPins() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("2",2));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "/";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoEmptyFrame() {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "/";

        assertThrows(BowlingException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);
        });
    }

    @Test
    public void addingMissIntoNonEmptyFrame() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "-";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingMissIntoEmptyFrame() throws BowlingException {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "-";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

}

package bowling;

import bowling.helper.FrameException;
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
    public void addingNumericIntoEmptyFrame() throws FrameException {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "3";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingNumericIntoNonEmptyFrame() throws FrameException {
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

        assertThrows(FrameException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);

        });
    }

    @Test
    public void addingStrikeIntoEmptyFrame() throws FrameException {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "X";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoFrameWithAMiss() throws FrameException {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("-",0));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "/";

        frame.addBowlToFrame(frame, bowlDisplay);


        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoFrameWithKnockedPins() throws FrameException {
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

        assertThrows(FrameException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);
        });
    }

    @Test
    public void addingMissIntoNonEmptyFrame() throws FrameException {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("1",1));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "-";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingMissIntoEmptyFrame() throws FrameException {
        List<Bowl> bowls = new ArrayList<>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "-";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

}

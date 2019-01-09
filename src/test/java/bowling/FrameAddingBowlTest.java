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
        Frame frame = new Frame();
        String bowlDisplay = "3";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingNumericIntoNonEmptyFrame() throws FrameException {
        Frame frame = new Frame();
        frame.getBowls().add(new Bowl("1",1));
        String bowlDisplay = "3";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }


    @Test
    public void addingStrikeIntoNonEmptyFrame() {
        Frame frame = new Frame();
        frame.getBowls().add(new Bowl("1",1));
        String bowlDisplay = "X";

        assertThrows(FrameException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);

        });
    }

    @Test
    public void addingStrikeIntoEmptyFrame() throws FrameException {
        Frame frame = new Frame();
        String bowlDisplay = "X";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoFrameWithAMiss() throws FrameException {
        Frame frame = new Frame();
        frame.getBowls().add(new Bowl("-",0));
        String bowlDisplay = "/";

        frame.addBowlToFrame(frame, bowlDisplay);


        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoFrameWithKnockedPins() throws FrameException {
        Frame frame = new Frame();
        frame.getBowls().add(new Bowl("2",2));
        String bowlDisplay = "/";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingSpareIntoEmptyFrame() {
        Frame frame = new Frame();
        String bowlDisplay = "/";

        assertThrows(FrameException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);
        });
    }

    @Test
    public void addingMissIntoNonEmptyFrame() throws FrameException {
        Frame frame = new Frame();
        frame.getBowls().add(new Bowl("1",1));
        String bowlDisplay = "-";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

    @Test
    public void addingMissIntoEmptyFrame() throws FrameException {
        Frame frame = new Frame();
        String bowlDisplay = "-";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.getBowls().stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }
}

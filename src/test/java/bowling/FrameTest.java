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

class FrameTest {

    @Test
    public void addingXIntoFrameWithBowl()  {

        List<Bowl> bowls = Arrays.asList(new Bowl("1",1));
        Frame frame = new Frame(bowls);
        String bowlDisplay = "X";

        assertThrows(FrameException.class, () -> {
            frame.addBowlToFrame(frame, bowlDisplay);

        });
    }

    @Test
    public void addingXIntoEmptyFrame() throws FrameException {

        List<Bowl> bowls = new ArrayList<Bowl>();
        Frame frame = new Frame(bowls);
        String bowlDisplay = "X";

        frame.addBowlToFrame(frame, bowlDisplay);
        assertEquals(frame.bowls.stream().map(b -> b.display).filter(s -> s == bowlDisplay).count(), 1);
    }

}

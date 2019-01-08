package bowling;

import bowling.helper.FrameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    @Test
    public void processSuccessfulEntries() throws FrameException {

        String[] entries = {"-","/","1","2","X","2"};
        App app = new App();
        Integer score = app.process(entries);

        assertEquals((Integer) 28, score);
    }

    @Test
    public void processInvalidEntries() {

        String[] entries = {"A","1","2","3"};
        App app = new App();

        assertThrows(FrameException.class, () -> app.process(entries));
    }

    @Test
    public void processInvalidSequence() {

        String[] entries = {"/","1","2","3"};
        App app = new App();

        assertThrows(FrameException.class, () -> app.process(entries));
    }

    @Test
    public void processInvalidNumericZero() {

        String[] entries = {"0","1","2","3"};
        App app = new App();

        assertThrows(FrameException.class, () -> app.process(entries));
    }

//    @Test
//    public void processEntriesOverMaximumFrames() throws FrameException {
//
//        String[] entries = {"X","X","X","X","X","X","X","X","X","X","X","X"};
//        App app = new App();
//        Integer score = app.process(entries);
//
//        assertEquals((Integer) 300, score);
//    }

}

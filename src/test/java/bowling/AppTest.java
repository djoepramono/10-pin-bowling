package bowling;

import bowling.helper.BowlingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    @Test
    public void processSuccessfulEntries() throws BowlingException {

        String[] entries = {"-","/","1","2","X","2"};
        App app = new App();
        Integer score = app.process(entries);

        assertEquals((Integer) 28, score);
    }

    @Test
    public void processInvalidEntries() {

        String[] entries = {"A","1","2","3"};
        App app = new App();

        assertThrows(BowlingException.class, () -> app.process(entries));
    }

    @Test
    public void processInvalidSequence() {

        String[] entries = {"/","1","2","3"};
        App app = new App();

        assertThrows(BowlingException.class, () -> app.process(entries));
    }

    @Test
    public void processInvalidNumericZero() {

        String[] entries = {"0","1","2","3"};
        App app = new App();

        assertThrows(BowlingException.class, () -> app.process(entries));
    }

    @Test
    public void processFirstSampleEntriesTest() throws BowlingException {

        String[] entries = {"X","X","X","X","X","X","X","X","X","X","X","X"};
        App app = new App();
        Integer score = app.process(entries);

        assertEquals((Integer) 300, score);
    }

    @Test
    public void processSecondSampleEntriesTest() throws BowlingException {

        String[] entries = {"9-","9-","9-","9-","9-","9-","9-","9-","9-","9-"};
        App app = new App();
        Integer score = app.process(entries);

        assertEquals((Integer) 90, score);
    }

    @Test
    public void processThirdSampleEntriesTest() throws BowlingException {

        String[] entries = {"5/","5/","5/","5/","5/","5/","5/","5/","5/","5/", "5"};
        App app = new App();
        Integer score = app.process(entries);

        assertEquals((Integer) 150, score);
    }

}

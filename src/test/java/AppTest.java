package bowling;

import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import bowling.model.Frame;
import bowling.model.Bowl;
import java.util.List;
import java.util.Arrays;
import org.junit.rules.ExpectedException;

class JUnit5ExampleTest {

    // @Test
    // void justAnExample() {
    //     System.out.println("This test method should be run");
    // }

    // @Test
    // public void testAdd() {
    //     assertEquals(42, Integer.sum(19, 23));
    // }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFrame() {

        List<Bowl> bowls = Arrays.asList(new Bowl("1",1));
        Frame frame = new Frame(bowls);

         try {
            frame.addBowlToFrame(frame, "X");
         } catch (Exception e) {
             assertThat(e.getMessage(), is("invalid sequence - cannot add X to frame"));
         }
    }
}

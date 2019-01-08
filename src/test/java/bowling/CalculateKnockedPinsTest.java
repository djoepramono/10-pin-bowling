package bowling;

import bowling.model.Bowl;
import bowling.model.Frame;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static bowling.util.FrameUtil.calculateTotalKnockedPins;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateKnockedPinsTest {

    @Test
    public void processSuccessfulEntries() {
        List<Bowl> bowls = new ArrayList<>();
        bowls.add(new Bowl("8",8));
        Integer pins = calculateTotalKnockedPins(bowls);

        assertEquals((Integer) 8, pins);
    }
}

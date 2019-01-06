package bowling.util;

import bowling.model.Frame;

public final class FrameUtil {

    public static Integer calculateTotalKnockedPins(Frame frame) {
        return frame.bowls.stream()
            .map(bowl -> bowl.knockedPins)
            .reduce(0, (a, b) -> a + b);
    }
}

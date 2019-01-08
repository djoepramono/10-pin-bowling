package bowling;

import java.util.Collections;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import bowling.model.Bowl;
import bowling.model.Frame;
import bowling.util.FrameUtil;
import bowling.helper.FrameException;

public class Game {

    List<Frame> frames = new ArrayList<Frame>();
    Integer bowlPerFrame = 2;
    Integer frameLimit;

    Frame frame = new Frame(new ArrayList<Bowl>()); // needed for the first run

    public Game() {
        this.frameLimit = 3;
    }

    public void process(String entry) throws FrameException {
        System.out.println("-----");
        System.out.println("Process " + entry);
        System.out.println("  no of frames : " + frames.size());

        if (frames.size() < frameLimit) {
            frame.addBowlToFrame(frame, entry);

            //todo maybe add frame first
            if ((frame.bowls.size() >= bowlPerFrame) || (frame.calculateTotalKnockedPins(frame) >= frame.maxPins)) {
                frames.add(frame);
                debug();
                frame = new Frame(new ArrayList<Bowl>());
            }
        } else {
            // What should be done once the frame limit has been reached?
            // Throw an exception or just log an output?
            // I choose to do the latter
            System.out.println("cannot add anymore frame. You have reached the limit of " + frameLimit + " frames");
        }
    }

    public Integer getTotalScore() {
        Integer totalScore = 0;

        List<Bowl> bowls = getAllBowls(frames);
        Collections.reverse(bowls);

//        totalScore = bowls.stream()
//            .map(b -> b.knockedPins)
//            .reduce(0 ,(a,b) -> a+b);

        for (var i = 0; i < bowls.size(); i++) {
            Bowl bowl = bowls.get(i);
            
            if (
                ( i+1 >= bowls.size() || bowls.get(i+1).display != "/") &&
                ( i+2 >= bowls.size() || bowls.get(i+2).display != "X")
            ) {
                totalScore += bowl.knockedPins;
            } else {
                System.out.println("has modifier");
                totalScore += (bowl.knockedPins * 2);
            }
        }

        return totalScore;
    }

//    private Boolean isTheNextTwoBowlsHasModifier(ArrayList<Bowl> bowls, Integer index) {
//        if (index < bowls.size() && (bowls.get(index+1).display == "/") || bowls.get(index+2).display == "X") {
//            return true;
//        } else {
//            return false;
//        }
//    }

    private List<Bowl> getAllBowls(List<Frame> frames) {
        return frames.stream()
            .flatMap(f -> f.bowls.stream())
            .collect(Collectors.toList());
    }

    private void debug() {
        frames.forEach(
            f -> {
                System.out.println("Frame");
                f.bowls.forEach(
                    b -> System.out.println("  display: " + b.display + " knocks: " + b.knockedPins)
                );
            }
        );
    }
}

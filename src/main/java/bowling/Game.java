package bowling;

import bowling.helper.FrameException;
import bowling.model.Bowl;
import bowling.model.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    List<Frame> frames = new ArrayList<Frame>();
    Integer bowlPerFrame = 2;
    Integer frameLimit;
    Boolean finished = false;

    Frame frame = new Frame(new ArrayList<Bowl>()); // needed for the first run

    public Game() {
        this.frameLimit = 10;
        frames.add(frame);
    } //todo check later 10 or 11 or 12

    public Integer process(String entry) throws FrameException {
        System.out.println("-----");
        System.out.println("Process " + entry);

        if (frames.size() <= frameLimit) {
            if (frame.bowls.size() < bowlPerFrame && (frame.calculateTotalKnockedPins(frame) < frame.maxPins)) {
                frame.addBowlToFrame(frame, entry);
            } else {
                frame = new Frame(new ArrayList<Bowl>());
                frames.add(frame);
                frame.addBowlToFrame(frame, entry);
            }

            debug();

        } else {
            finished = true;
            // What should be done once the frame limit has been reached?
            // Throw an exception or just log an output?
            // I choose to do the latter
            System.out.println("cannot add anymore frame. You have reached the limit of " + frameLimit + " frames");
        }

        return frames.size();
    }

    public Integer getTotalScore(List<Bowl> bowls) {
        Integer totalScore = 0;


        for (var i = 0; i < bowls.size(); i++) {
            Bowl bowl = bowls.get(i);

            if (bowl.display == "X") {
                totalScore = totalScore + bowl.knockedPins + getBowlKnockedPinsFromArrayList(bowls, i+1) + getBowlKnockedPinsFromArrayList(bowls, i+2);
            } else if (bowl.display == "/") {
                totalScore = totalScore + bowl.knockedPins + getBowlKnockedPinsFromArrayList(bowls, i+1);
            } else {
                totalScore += bowl.knockedPins;
            }

        }

        return totalScore;
    }


    public List<Bowl> getAllBowls(List<Frame> frames) {
        return frames.stream()
            .flatMap(f -> f.bowls.stream())
            .collect(Collectors.toList());
    }

    private Integer getBowlKnockedPinsFromArrayList(List<Bowl> bowls, Integer i) {
        try {
            return bowls.get(i).knockedPins;
        } catch (IndexOutOfBoundsException e){
            return 0;
        }
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

package bowling;

import bowling.helper.BowlingException;
import bowling.model.Bowl;
import bowling.model.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.util.FrameUtil.calculateTotalKnockedPins;

public class Game {

    List<Frame> frames = new ArrayList<Frame>();
    Integer bowlPerFrame = 2;
    Integer frameLimit;
    Boolean finished = false;

    Frame frame = new Frame(new ArrayList<Bowl>());

    public Game() {
        this.frameLimit = 10;
        frames.add(frame);
    }

    public Integer process(String entry) throws BowlingException {
        if (frames.size() <= frameLimit) {
            if (frame.getBowls().size() < bowlPerFrame && (calculateTotalKnockedPins(frame.getBowls()) < frame.getMaxPins())) {
                frame.addBowlToFrame(frame, entry);
            } else {
                frame = new Frame(new ArrayList<Bowl>());
                frames.add(frame);
                frame.addBowlToFrame(frame, entry);
            }

        } else {
            finished = true;
            // What should be done once the frame limit has been reached?
            // Throw an exception or just log an output?
            // I choose to do the latter
            System.out.println("cannot add anymore frame. You have reached the limit of " + frameLimit + " frames");
        }

        return frames.size();
    }

    public Integer getTotalScore(List<Bowl> bowls) { //todo score util?
        Integer totalScore = 0;


        for (var i = 0; i < bowls.size(); i++) {
            Bowl bowl = bowls.get(i);

            if (i < frameLimit * bowlPerFrame) {
                if (bowl.display.equals("X")) {
                    totalScore = totalScore + bowl.knockedPins + getBowlKnockedPinsFromArrayList(bowls, i+1) + getBowlKnockedPinsFromArrayList(bowls, i+2);
                } else if (bowl.display.equals("/")) {
                    totalScore = totalScore + bowl.knockedPins + getBowlKnockedPinsFromArrayList(bowls, i+1);
                } else {
                    totalScore += bowl.knockedPins;
                }
            }
        }

        return totalScore;
    }


    public List<Bowl> getAllBowls(List<Frame> frames) {
        return frames.stream()
            .flatMap(f -> f.getBowls().stream())
            .collect(Collectors.toList());
    }

    private Integer getBowlKnockedPinsFromArrayList(List<Bowl> bowls, Integer i) {
        try {
            return bowls.get(i).knockedPins;
        } catch (IndexOutOfBoundsException e){
            return 0;
        }
    }
}

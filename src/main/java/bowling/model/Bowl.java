package bowling.model;

//Maybe should be split
public class Bowl {
    public final String display;
    public final Integer knockedPins;

    public Bowl(String display, Integer knockedPins) {
        this.display = display;
        this.knockedPins = knockedPins;
    }
}

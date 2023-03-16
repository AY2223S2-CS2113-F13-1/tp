package seedu.duke.recipe;

public class Step {
    protected String stepDetails;
    public Step(String stepDetails) {
        this.stepDetails = stepDetails;
    }

    @Override
    public String toString() {
        return stepDetails;
    }
}

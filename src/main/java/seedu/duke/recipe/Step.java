package seedu.duke.recipe;

public class Step {
    protected String description;
    public Step(String inputDescription) {
        description = inputDescription;
    }
    public String getStep() {
        return description;
    }
    @Override
    public String toString() {
        return description;
    }
}

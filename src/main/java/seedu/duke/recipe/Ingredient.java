package seedu.duke.recipe;

public class Ingredient {
    protected String name;
    public Ingredient(String inputName) {
        name = inputName;
    }

    @Override
    public String toString() {
        return name;
    }
}

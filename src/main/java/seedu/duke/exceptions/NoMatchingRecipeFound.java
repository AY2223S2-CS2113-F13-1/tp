package seedu.duke.exceptions;

public class NoMatchingRecipeFound extends Exception {
    public NoMatchingRecipeFound(String inputText) {
        super(inputText);
    }
}

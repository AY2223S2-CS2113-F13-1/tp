package seedu.duke.exceptions;

public class NonexistentRecipeException extends Exception{
    public NonexistentRecipeException(String recipeName) {
        super(recipeName);
    }
}

package seedu.duke.exceptions;

public class DuplicateRecipeNameException extends Exception{
    public DuplicateRecipeNameException(String inputText) {
        super(inputText);
    }
}

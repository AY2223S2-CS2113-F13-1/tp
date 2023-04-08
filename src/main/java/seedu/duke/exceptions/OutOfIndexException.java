package seedu.duke.exceptions;

public class OutOfIndexException extends Exception {
    public OutOfIndexException (String inputText) {
        super(inputText);
    }
}

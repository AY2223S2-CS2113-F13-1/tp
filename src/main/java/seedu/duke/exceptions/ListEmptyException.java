package seedu.duke.exceptions;

public class ListEmptyException extends Exception{
    public ListEmptyException() {
        super("The list is empty!");
    }
}

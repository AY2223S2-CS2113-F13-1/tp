package seedu.duke.exceptions;

import seedu.duke.ui.StringLib;

public class InvalidIndexRangeException extends Exception {

    public InvalidIndexRangeException(int validStartIndex, int validEndIndex) {
        super(StringLib.INVALID_INPUT_VALID_RANGE_PREFIX + validStartIndex + " to " + validEndIndex);
    }
}

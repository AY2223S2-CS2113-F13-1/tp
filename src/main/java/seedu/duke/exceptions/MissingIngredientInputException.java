package seedu.duke.exceptions;

import seedu.duke.ui.StringLib;

public class MissingIngredientInputException extends Exception{

    public MissingIngredientInputException() {
        super(StringLib.MISSING_INGREDIENT_INPUT);
    }
}

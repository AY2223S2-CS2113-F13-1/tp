package seedu.duke.command;

/**
 *  Commands that can be used.
 *  <p></p>
 *  {@link #LIST}
 *  {@link #ADD}
 *  {@link #ADDTORECIPE}
 *  {@link #VIEW}
 *  {@link #DELETE}
 *  {@link #DELETEFROMRECIPE}
 *  {@link #HELP}
 *  {@link #CLEAR}
 *  {@link #EDITSTEP}
 *  {@link #EDITINGREDIENT}
 *  {@link #EDIT}
 *  {@link #FINDNAME}
 *  {@link #FINDTAG}
 *  {@link #EXIT}
 *  {@link #UNKNOWN}
 */
public enum CommandType {
    /**
     * Lists out the current recipe list.
     */
    LIST,
    /**
     * Adds a recipe to the recipe list.
     */
    ADD,
    /**
     * Adds an element (step or ingredient) to a recipe.
     */
    ADDTORECIPE,
    /**
     * Displays a particular recipe in the recipe list.
     */
    VIEW,
    /**
     * Removes a particular recipe from the recipe list.
     */
    DELETE,
    /**
     * Deletes an element (step or ingredient) from a recipe.
     */
    DELETEFROMRECIPE,
    /**
     * Shows the full list of commands.
     */
    HELP,
    /**
     * Clear the current recipe list.
     */
    CLEAR,
    /**
     * Terminates the programme and exit without saving.
     */
    EXIT,
    /**
     * Edit a step in the recipe.
     */
    EDITSTEP,
    /**
     * Edit a ingredient in the recipe.
     */
    EDITINGREDIENT,
    /**
     * Edit in one line
     */
    EDIT,
    /**
     * Search dishes by name.
     */
    FINDNAME,
    /**
     * Search dishes by tag.
     */
    FINDTAG,
    /**
     * Command not recognized.
     */
    UNKNOWN
}

package seedu.duke.command;

/**
 *  Commands that can be used.
 *  <p></p>
 *  {@link #LIST}
 *  {@link #ADD}
 *  {@link #VIEW}
 *  {@link #FIND}
 *  {@link #DELETE}
 *  {@link #HELP}
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
     * Displays a particular recipe in the recipe list.
     */
    VIEW,
    /**
     * Finds all recipes containing keywords on the current recipe list.
     */
    FIND,
    /**
     * Removes a particular recipe from the recipe list.
     */
    DELETE,
    /**
     * Shows the full list of commands.
     */
    HELP,
    /**
     * Terminates the programme and exit with saving.
     */
    EXIT,
    /**
     * Command not recognized.
     */
    UNKNOWN
}

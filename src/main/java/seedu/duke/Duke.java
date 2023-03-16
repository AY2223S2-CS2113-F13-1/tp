package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.RecipeList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Represents the Recipe Manager programme. A <code>Duke</code> object corresponds to
 * a Recipe Manager.
 */
public class Duke {
    private final RecipeList recipes;

    /**
     * Class constructor specifying filePath for saving data.
     *
     * @param filePath a relative file path giving the location of the saved file.
     */
    public Duke(String filePath) {
        Storage.setFilePath(filePath);
        try {
            Storage.createSavedFile();
        } catch (Exception e) {
            Ui.showLoadingErrorMessage(e);
        } finally {
            recipes = new RecipeList();
            Ui.showLine();
        }
    }

    /**
     * Starts the Duke programme and continuously take in user inputs until
     * it is terminated by the user.
     */
    public void run() {
        Ui.showWelcome();
        Ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readNextLine();
                Ui.showLine();
                Command c = Parser.parseCommands(fullCommand);
                c.execute(recipes);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showDudeMainError(e);
            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * The main method that runs the entire programme.
     */
    public static void main(String[] args) {
        new Duke("data/recipeList.txt").run();
        System.exit(0);
    }
}

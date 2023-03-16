package seedu.duke.command;


import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.NonexistentRecipeException;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a particular command to be carried out consisting of the
 * command type and command description.
 * <p></p>
 * A <code>Command</code> object corresponds to a particular command represented
 * by <code>type</code> and <code>fullDescription</code> (e.g. <code>DELETE,6</code>)
 */
public class Command {
    private final CommandType type;
    private final String fullDescription;

    /**
     * Class constructor specifying the type of command and its follow-up description.
     *
     * @param type an Enum that represents a particular command.
     * @param fullDescription a String that contains the follow-up description for the command.
     */
    public Command(CommandType type, String fullDescription) {
        this.type = type;
        this.fullDescription = fullDescription;
    }

    /**
     * Returns if the command type is <code>CommandType.EXIT</code>
     * in order to terminate the programme.
     *
     * @return      if the command is the exit command type.
     */
    public boolean isExit() {
        return this.type == CommandType.EXIT;
    }

    /**
     * Based on the <code>type</code>, carries out different tasks assigned
     * while fully checking for any exceptions that may occur along the way.
     *
     * @param recipeList the current list of recipes to be modified or used.
     */
    public void execute(RecipeList recipeList) {

        int recipeListIndex;

        switch (type) {
        case LIST:
            Ui.showRecipeList(recipeList.getRecipeList());
            break;
        case ADD:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The description of " + type + " cannot be empty.\n");
                }
                ArrayList<String> parsed = Parser.parseRecipe(fullDescription);
                String recipeName = parsed.get(0);
                IngredientList ingredientLists = Parser.parseIngredients(parsed.get(1));
                String recipeTag = parsed.get(2);
                StepList recipeSteps = Parser.parseRecipeSteps();
                recipeList.addNewRecipe(new Recipe(recipeName, recipeTag, ingredientLists, recipeSteps));
                Ui.showRecipeAdded(recipeList.getNewestRecipe(), recipeList.getCurrRecipeNumber());
            } catch (Exception e) {
                Ui.showAddingRecipeErrorMessage(e);
            }
            break;
        case DELETE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                recipeListIndex = Integer.parseInt(fullDescription);
                Recipe recipeToBeDeleted = recipeList.getRecipeFromList(recipeListIndex - 1);
                Ui.showRecipeDeleted(recipeToBeDeleted, recipeList.getCurrRecipeNumber() - 1);
                recipeList.removeRecipe(recipeListIndex);
            } catch (Exception e) {
                Ui.showDeletingTaskErrorMessage(e, type);
            }
            break;
        case VIEW:
            try {
            String recipeName = Ui.readNextLine();
            Recipe currRecipe;
            int recipeIndex = recipeList.findRecipeIndex(recipeName);
            if (recipeIndex < 0) {
                throw new NonexistentRecipeException(recipeName);
            }
            currRecipe = recipeList.getRecipeFromList(recipeIndex);
            Ui.showRecipe(currRecipe);
            break;
            } catch (Exception e) {
                Ui.showInvalidRecipeMessage(e);
            }
        case FIND:
            try {
                ArrayList<Recipe> findRecipeResults = new ArrayList<>();
                String keywords = fullDescription;
                if (keywords.isEmpty()) {
                    throw new IncompleteInputException("Find is missing KEYWORDS!");
                }
                for (Recipe recipe : recipeList.getRecipeList()) {
                    if (recipe.getName().contains(keywords)) {
                        findRecipeResults.add(recipe);
                    }
                }
                Ui.showFindResults(findRecipeResults, keywords);
            } catch (Exception e) {
                Ui.showFindingTaskErrorMessage(e);
            }
            break;
        case HELP:
            Ui.showHelp();
            break;
        case EXIT:
            Ui.showExit();
            break;
        case UNKNOWN:
            Ui.showUnrecognizableErrorMessage();
            break;
        default:
            Ui.showUnrecognizableCommandErrorMessage();
        }
    }
}

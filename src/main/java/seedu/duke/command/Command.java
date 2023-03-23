package seedu.duke.command;


import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.ui.IntLib.RECIPE_NAME_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_INGREDIENTS_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_TAG_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_SUM_OF_STEPS_INDEX;

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
    public void execute(RecipeList recipeList, UI ui) throws IOException {

        int recipeListIndex;

        switch (type) {
        case LIST:
            ui.showRecipeList(recipeList.getRecipeList());
            break;
        case ADD:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The description of " + type + " cannot be empty.\n");
                }
                ArrayList<String> parsed = Parser.parseRecipe(fullDescription);
                String recipeName = parsed.get(RECIPE_NAME_INDEX);
                IngredientList ingredientLists =
                        Parser.parseIngredients(parsed.get(RECIPE_INGREDIENTS_INDEX));
                String recipeTag = parsed.get(RECIPE_TAG_INDEX).toString();
                int sumOfSteps = Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX));
                StepList recipeSteps = Parser.parseSteps(ui,sumOfSteps);
                recipeList.addNewRecipe(new Recipe(recipeName, recipeTag, ingredientLists, recipeSteps));
                ui.showRecipeAdded(recipeList.getNewestRecipe(), recipeList.getCurrRecipeNumber());
            } catch (Exception e) {
                ui.showAddingRecipeErrorMessage(e);
            }
            ui.showSave();
            Storage.writeSavedFile();
            break;
        case DELETE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                recipeListIndex = Integer.parseInt(fullDescription);
                Recipe recipeToBeDeleted = recipeList.getRecipeFromList(recipeListIndex);
                ui.showRecipeDeleted(recipeToBeDeleted, recipeList.getCurrRecipeNumber() - 1);
                recipeList.removeRecipe(recipeListIndex);
            } catch (Exception e) {
                ui.showDeletingTaskErrorMessage(e, type);
            }
            ui.showSave();
            Storage.writeSavedFile();
            break;
        case FIND:
            try {
                ArrayList<Recipe> findRecipeResults = new ArrayList<>();
                String keywords = fullDescription;
                if (keywords.isEmpty()) {
                    throw new IncompleteInputException(StringLib.MISSING_KEYWORD);
                }
                for (Recipe recipe : recipeList.getRecipeList()) {
                    if (recipe.getName().contains(keywords)) {
                        findRecipeResults.add(recipe);
                    }
                }
                ui.showFindResults(findRecipeResults, keywords);
            } catch (Exception e) {
                ui.showFindingTaskErrorMessage(e);
            }
            break;
        case CLEAR:
            recipeList.clearRecipeList();
            ui.showRecipeListCleared();
            ui.showSave();
            Storage.writeSavedFile();
            break;
        case VIEW:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                recipeListIndex = Integer.parseInt(fullDescription);
                Recipe recipeToBeViewed = recipeList.getRecipeFromList(recipeListIndex);
                ui.showRecipeViewed(recipeToBeViewed);
            } catch (Exception e) {
                ui.showViewingRecipeErrorMessage(e);
            }
            break;
        case HELP:
            ui.showHelp();
            break;
        case LOAD:
            ui.showLoad();
            Storage.loadSaveFiles();
            break;
        case EXIT:
            ui.showExit();
            break;
        case UNKNOWN:
            ui.showUnrecognizableErrorMessage();
            break;
        default:
            ui.showUnrecognizableCommandErrorMessage();
        }
    }
}

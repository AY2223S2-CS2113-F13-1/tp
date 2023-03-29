package seedu.duke.ui;

import seedu.duke.command.CommandType;
import seedu.duke.exceptions.FileParseReadingException;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.RecipeListEmptyException;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.StepList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class UI {
    private static Scanner in;
    public UI() {
        in = new Scanner(System.in);
    }
    public String readCommand() {
        return in.nextLine();
    }
    public void showRecipeList(ArrayList<Recipe> list) {
        if (list.size() == 0) {
            System.out.println(StringLib.EMPTY_LIST_MESSAGE);
            return;
        }
        System.out.println("\nRECIPE LIST\n");
        int i = 1;
        for (Recipe t : list) {
            System.out.println(i + ". " + t.toString());
            i += 1;
        }
        System.out.println();
    }
    public void showRecipeAdded(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Got it. I've added this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public void showRecipeDeleted(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Noted. I've removed this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public void showRecipeListCleared() {
        System.out.println(StringLib.RECIPE_CLEARED_MESSAGE);
    }
    public void showWelcome() {
        System.out.println(StringLib.WELCOME_MESSAGE);
    }
    public void showExit() {
        System.out.println(StringLib.EXIT_MESSAGE);
    }
    public void showHelp() {
        System.out.println(StringLib.HELP);
    }
    public void showLine() {
        System.out.println(StringLib.LINE);
    }
    public void showStepInsertMessage(int stepNumber) {
        System.out.println("\nPlease enter the description of step " + stepNumber + ":");
    }
    public void showDukeMainError(Exception e) {
        if (e instanceof IOException) {
            System.out.println(StringLib.FILE_IO_ERROR + e.getMessage());
        } else {
            System.out.println(StringLib.DUDE_MAIN_ERROR + e.getMessage());
        }
    }
    public void showUnrecognizableErrorMessage() {
        System.out.println(StringLib.UNRECOGNIZABLE_ERROR);
    }
    public void showUnrecognizableCommandErrorMessage() {
        System.out.println(StringLib.UNRECOGNIZABLE_COMMAND_ERROR);
    }

    public void showLoadingErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            System.out.println(StringLib.FILE_NOT_FOUND_ERROR + e.getMessage() + StringLib.CREATING_NEW_FILE_AND_DIRECTORY);
        } else if (e instanceof FileParseReadingException) {
            System.out.println(StringLib.FILE_PARSE_READING_ERROR + e.getMessage());
        } else {
            System.out.println(StringLib.FILE_LOADING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showAddingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(StringLib.PARSING_STRING_ERROR + e.getMessage());
        } else {
            System.out.println(StringLib.RECIPE_ADDING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showDeletingTaskErrorMessage(Exception e, CommandType type) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                   e instanceof RecipeListEmptyException) {
            System.out.println(StringLib.PREFIX_EMPTY_LIMIT_LIST_ERROR + type + StringLib.SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(StringLib.RECIPE_DELETING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showRecipeViewed(Recipe recipe) {
        System.out.println("Here is the recipe you requested, which is a "+ recipe.getTag() + " flavour:");
        System.out.println("name: " + recipe.getName());
        System.out.println(StringLib.LINE);
        IngredientList ingredients = recipe.getIngredientList();
        ingredients.showList();
        System.out.println(StringLib.LINE);
        StepList steps = recipe.getStepList();
        steps.showStepList();
    }
    public void showViewingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(StringLib.MISSING_DESCRIPTION_ERROR + e.getMessage());
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                e instanceof RecipeListEmptyException) {
            System.out.println(StringLib.PREFIX_EMPTY_LIMIT_LIST_ERROR + CommandType.VIEW + StringLib.SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(StringLib.RECIPE_VIEWING_DEFAULT_ERROR + e.getMessage());
        }
    }
    public void showSave() {
        System.out.println(StringLib.RECIPE_SAVED);
    }
    public void showLoad() {
        System.out.println(StringLib.RECIPE_LOADED);
    }
    public void showInvalidStepMessage() {
        System.out.println(StringLib.INVALID_STEP);
    }
}

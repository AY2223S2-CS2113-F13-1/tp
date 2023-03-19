package seedu.duke.ui;

import seedu.duke.command.CommandType;
import seedu.duke.exceptions.FileParseReadingException;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.RecipeListEmptyError;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.StepList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.ui.StringLib.CREATING_NEW_FILE_AND_DIRECTORY;
import static seedu.duke.ui.StringLib.DUDE_MAIN_ERROR;
import static seedu.duke.ui.StringLib.EMPTY_LIST_MESSAGE;
import static seedu.duke.ui.StringLib.EXIT_MESSAGE;
import static seedu.duke.ui.StringLib.FILE_IO_ERROR;
import static seedu.duke.ui.StringLib.FILE_LOADING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.FILE_NOT_FOUND_ERROR;
import static seedu.duke.ui.StringLib.FILE_PARSE_READING_ERROR;
import static seedu.duke.ui.StringLib.FIND_LIST_MESSAGE;
import static seedu.duke.ui.StringLib.HELP;
import static seedu.duke.ui.StringLib.LINE;
import static seedu.duke.ui.StringLib.MISSING_DESCRIPTION_ERROR;
import static seedu.duke.ui.StringLib.MISSING_INPUTS_ERROR;
import static seedu.duke.ui.StringLib.NO_MATCHING_FIND_RESULTS_MESSAGE;
import static seedu.duke.ui.StringLib.PARSING_STRING_ERROR;
import static seedu.duke.ui.StringLib.PREFIX_EMPTY_LIMIT_LIST_ERROR;
import static seedu.duke.ui.StringLib.RECIPE_ADDING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.RECIPE_DELETING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.RECIPE_FINDING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.SUFFIX_EMPTY_LIMIT_LIST_ERROR;
import static seedu.duke.ui.StringLib.UNRECOGNIZABLE_COMMAND_ERROR;
import static seedu.duke.ui.StringLib.UNRECOGNIZABLE_ERROR;
import static seedu.duke.ui.StringLib.WELCOME_MESSAGE;
import static seedu.duke.ui.StringLib.RECIPE_CLEARED_MESSAGE;
import static seedu.duke.ui.StringLib.RECIPE_VIEWING_DEFAULT_ERROR;

public class UI {
    private static Scanner in;
    public UI() {
        in = new Scanner(System.in);
    }
    public String readCommand() {
        return in.nextLine();
    }
    public void showFindResults(ArrayList<Recipe> list, String keywords) {
        if (list.size() == 0) {
            System.out.println(NO_MATCHING_FIND_RESULTS_MESSAGE + keywords + '\n');
            return;
        }
        System.out.println(FIND_LIST_MESSAGE);
        int i = 1;
        for (Recipe t : list) {
            System.out.println(i + ". " + t.toString());
            i += 1;
        }
        System.out.println();
    }
    public void showRecipeList(ArrayList<Recipe> list) {
        if (list.size() == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
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
        System.out.println(RECIPE_CLEARED_MESSAGE);
    }
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }
    public void showExit() {
        System.out.println(EXIT_MESSAGE);
    }
    public void showHelp() {
        System.out.println(HELP);
    }
    public void showLine() {
        System.out.println(LINE);
    }
    public void showDudeMainError(Exception e) {
        if (e instanceof IOException) {
            System.out.println(FILE_IO_ERROR + e);
        } else {
            System.out.println(DUDE_MAIN_ERROR + e);
        }
    }
    public void showUnrecognizableErrorMessage() {
        System.out.println(UNRECOGNIZABLE_ERROR);
    }
    public void showUnrecognizableCommandErrorMessage() {
        System.out.println(UNRECOGNIZABLE_COMMAND_ERROR);
    }

    public void showLoadingErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            System.out.println(FILE_NOT_FOUND_ERROR + e + CREATING_NEW_FILE_AND_DIRECTORY);
        } else if (e instanceof FileParseReadingException) {
            System.out.println(FILE_PARSE_READING_ERROR + e);
        } else {
            System.out.println(FILE_LOADING_DEFAULT_ERROR + e);
        }
    }
    public void showAddingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(PARSING_STRING_ERROR + e);
        } else {
            System.out.println(RECIPE_ADDING_DEFAULT_ERROR + e);
        }
    }
    public void showDeletingTaskErrorMessage(Exception e, CommandType type) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                   e instanceof RecipeListEmptyError) {
            System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + type + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(RECIPE_DELETING_DEFAULT_ERROR + e);
        }
    }
    public void showFindingTaskErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_INPUTS_ERROR + e);
        } else {
            System.out.println(RECIPE_FINDING_DEFAULT_ERROR + e);
        }
    }
    public void showRecipeViewed(Recipe recipe) {
        System.out.println("Here is the recipe you requested, which is a "+ recipe.getTag() + " flavour:");
        System.out.println("name: " + recipe.getName());
        System.out.println(LINE);
        IngredientList ingredients = recipe.getIngredientList();
        ingredients.showList();
        System.out.println(LINE);
        StepList steps = recipe.getStepList();
        steps.showStepList();
    }
    public void showViewingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                e instanceof RecipeListEmptyError) {
            System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + CommandType.VIEW + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(RECIPE_VIEWING_DEFAULT_ERROR + e);
        }
    }
    public void showSave() {
        System.out.println(StringLib.RECIPE_SAVED);
    }
}

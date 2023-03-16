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

import static seedu.duke.ui.StringLib.*;


public class Ui {

    public static String readNextLine() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }
    public static void showFindResults(ArrayList<Recipe> list, String keywords) {
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
    public static void showRecipeList(ArrayList<Recipe> list) {
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

    /**
     * Prints contents of a specified IngredientList
     * @param recipeIngredientList IngredientList to be displayed
     */
    public static void showRecipeIngredientList(IngredientList recipeIngredientList) {
        System.out.println("\nINGREDIENTS:\n");
        for (int i = 0; i < recipeIngredientList.getMaxIngredientIndex(); ++i) {
            System.out.println(i+1 + ". ");
            System.out.println(recipeIngredientList.getIngredient(i).toString());
        }
    }

    /**
     * Prints contents of a specified StepList
     * @param recipeStepList StepList to be displayed
     */
    public static void showRecipeStepList(StepList recipeStepList) {
        System.out.println("\nSTEPS:\n");
        for (int i = 0; i < recipeStepList.getMaxStepIndex(); ++i) {
            System.out.println(i+1 + ". ");
            System.out.println(recipeStepList.getStep(i).toString());
        }
    }

    /**
     * Prints contents of a specified Recipe
     * @param currRecipe Recipe to be displayed
     */
    public static void showRecipe(Recipe currRecipe) {
        IngredientList recipeIngredientList = currRecipe.getIngredientList();
        StepList recipeStepList = currRecipe.getStepList();
        System.out.println(currRecipe.getName());
        System.out.println(LINE);
        showRecipeIngredientList(recipeIngredientList);
        showRecipeStepList(recipeStepList);
    }
    public static void showRecipeAdded(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Got it. I've added this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public static void showRecipeDeleted(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Noted. I've removed this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public static void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }
    public static void showExit() {
        System.out.println(EXIT_MESSAGE);
    }
    public static void showHelp() {
        System.out.println(HELP);
    }
    public static void showLine() {
        System.out.println(LINE);
    }
    public static void showDudeMainError(Exception e) {
        if (e instanceof IOException) {
            System.out.println(FILE_IO_ERROR + e);
        } else {
            System.out.println(DUDE_MAIN_ERROR + e);
        }
    }
    public static void showUnrecognizableErrorMessage() {
        System.out.println(UNRECOGNIZABLE_ERROR);
    }
    public static void showUnrecognizableCommandErrorMessage() {
        System.out.println(UNRECOGNIZABLE_COMMAND_ERROR);
    }

    public static void showLoadingErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            System.out.println(FILE_NOT_FOUND_ERROR + e + CREATING_NEW_FILE_AND_DIRECTORY);
        } else if (e instanceof FileParseReadingException) {
            System.out.println(FILE_PARSE_READING_ERROR + e);
        } else {
            System.out.println(FILE_LOADING_DEFAULT_ERROR + e);
        }
    }
    public static void showAddingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(PARSING_STRING_ERROR + e);
        } else {
            System.out.println(RECIPE_ADDING_DEFAULT_ERROR + e);
        }
    }
    public static void showDeletingTaskErrorMessage(Exception e, CommandType type) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                   e instanceof RecipeListEmptyError) {
            System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + type + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(RECIPE_DELETING_DEFAULT_ERROR + e);
        }
    }
    public static void showFindingTaskErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_INPUTS_ERROR + e);
        } else {
            System.out.println(RECIPE_FINDING_DEFAULT_ERROR + e);
        }
    }
    public static void showInvalidRecipeMessage(Exception e) {
        if (e instanceof ArrayIndexOutOfBoundsException) {
            System.out.println(RECIPE_NONEXISTENT_ERROR + e);
        } else {
            System.out.println(RECIPE_FINDING_DEFAULT_ERROR + e);
        }
    }
}

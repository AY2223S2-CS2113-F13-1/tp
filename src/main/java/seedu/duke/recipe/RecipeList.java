package seedu.duke.recipe;

import seedu.duke.exceptions.DuplicateRecipeNameException;
import seedu.duke.exceptions.NoMatchingRecipeFound;
import seedu.duke.exceptions.OutOfIndexException;
import seedu.duke.exceptions.EditFormatException;
import seedu.duke.exceptions.RecipeListEmptyException;
import seedu.duke.ui.StringLib;

import java.util.ArrayList;

import static seedu.duke.ui.StringLib.DUPLICATE_RECIPE_NAMES_ERROR;
import static seedu.duke.ui.StringLib.INVALID_RANGE;
import static seedu.duke.ui.StringLib.MISSING_FIND_KEYWORD;
import static seedu.duke.ui.StringLib.EMPTY_LIST_MESSAGE;
import static seedu.duke.ui.StringLib.NO_MATCHES;
import static seedu.duke.ui.StringLib.MATCHING_ITEMS;
import static seedu.duke.ui.StringLib.NO_MATCHING_RECIPE_ERROR;

public class RecipeList {
    protected static ArrayList<Recipe> recipeList;
    protected static int currRecipeNumber;
    private static RecipeList instance;

    private RecipeList() {
        recipeList = new ArrayList<>();
        currRecipeNumber = 0;
    }

    public static void createRecipeList() {
        if (instance == null) {
            instance = new RecipeList();
        }
    }

    public static boolean isEmpty() {
        return recipeList.isEmpty();
    }

    public static ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public static int getCurrRecipeNumber() {
        assert (currRecipeNumber == recipeList.size());
        return currRecipeNumber;
    }

    public static Recipe getRecipeFromList(int itemNum) {
        return recipeList.get(itemNum - 1);
    }

    public static Recipe getNewestRecipe() {
        return recipeList.get(currRecipeNumber - 1);
    }

    public static void addNewRecipe(Recipe recipe) {
        recipeList.add(recipe);
        currRecipeNumber++;
        assert (currRecipeNumber == recipeList.size());
    }

    public static void removeRecipe(int index) throws RecipeListEmptyException {
        if (recipeList.isEmpty()) {
            throw new RecipeListEmptyException();
        }
        recipeList.remove(index - 1);
        currRecipeNumber--;
        assert (currRecipeNumber == recipeList.size());
    }

    public static void clearRecipeList() {
        recipeList.clear();
        currRecipeNumber = 0;
        assert (recipeList.size() == 0);
    }

    public static void searchRecipeList(String term) {
        ArrayList<String> matches = new ArrayList<>();
        term = term.trim().toLowerCase();
        if (term.equals(StringLib.EMPTY_STRING)) {
            System.out.println(MISSING_FIND_KEYWORD);
            return;
        }
        if (getCurrRecipeNumber() == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
            return;
        }
        for (int i = 1; i <= getCurrRecipeNumber(); i++) {
            Recipe dish = getRecipeFromList(i);
            if (dish.getName().trim().toLowerCase().contains(term)) {
                matches.add(dish + " [Index: " + i + "]");
            }
        }
        if (matches.isEmpty()) {
            System.out.println(NO_MATCHES);
        } else {
            System.out.println(MATCHING_ITEMS);
            for (String match : matches) {
                System.out.println("  " + match);
            }
        }
    }

    public static void editIngredient(int recipeIndex, int ingredientIndex, String newIngredient)
            throws EditFormatException {
        if (recipeIndex > getCurrRecipeNumber() || recipeIndex < 1) {
            throw new EditFormatException(StringLib.INVALID_RECIPE_INDEX);
        }
        Recipe recipe = getRecipeFromList(recipeIndex);
        IngredientList ingredientList = recipe.getIngredientList();
        if (ingredientIndex > ingredientList.getCurrIngredientNumber() || ingredientIndex < 1) {
            throw new EditFormatException(StringLib.INVALID_INGREDIENT_INDEX);
        }
        ingredientList.editIngredient(newIngredient, ingredientIndex - 1);
    }

    public static void editStep(Integer recipeIndex, int stepIndex, String newStep) throws EditFormatException {
        if (recipeIndex > getCurrRecipeNumber() || recipeIndex < 1) {
            throw new EditFormatException(StringLib.INVALID_RECIPE_INDEX);
        }
        Recipe recipe = getRecipeFromList(recipeIndex);
        StepList stepList = recipe.getStepList();
        if (stepIndex > stepList.getCurrStepNumber() || stepIndex < 1) {
            throw new EditFormatException(StringLib.INVALID_STEP_INDEX);
        }
        stepList.editStep(stepIndex - 1, newStep);
    }
    
    public static void searchByTag(String tag) {
        ArrayList<String> matches = new ArrayList<>();
        tag = tag.trim().toLowerCase();
        if (tag.equals(StringLib.EMPTY_STRING)) {
            System.out.println(MISSING_FIND_KEYWORD);
        } else if (getCurrRecipeNumber() == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
        } else {
            for (int i = 1; i <= getCurrRecipeNumber(); i++) {
                Recipe dish = getRecipeFromList(i);
                if (dish.getTag().trim().toLowerCase().contains(tag)) {
                    matches.add(dish + " [Index: " + i + "]");
                }
            }
            if (matches.isEmpty()) {
                System.out.println(NO_MATCHES);
            } else {
                System.out.println(MATCHING_ITEMS);
                for (String match : matches) {
                    System.out.println("  " + match);
                }
            }
        }
    }

    public static Recipe viewRecipe(String term)
            throws DuplicateRecipeNameException, NoMatchingRecipeFound, OutOfIndexException {
        Recipe recipeToBeViewed;
        try {
            int recipeListIndex = Integer.parseInt(term);
            if (recipeListIndex <= 0 || recipeListIndex > getCurrRecipeNumber()) {
                throw new OutOfIndexException(INVALID_RANGE + "1 to " + getCurrRecipeNumber() + '\n');
            }
            recipeToBeViewed = recipeList.get(recipeListIndex - 1);
        } catch (NumberFormatException e) {
            ArrayList<Recipe> viewRecipeResults = new ArrayList<>();
            for (Recipe recipe : recipeList) {
                if (recipe.getName().equalsIgnoreCase(term)) {
                    viewRecipeResults.add(recipe);
                }
            }
            if (viewRecipeResults.isEmpty()) {
                throw new NoMatchingRecipeFound(NO_MATCHING_RECIPE_ERROR);
            } else if (viewRecipeResults.size() == 1) {
                recipeToBeViewed = viewRecipeResults.get(0);
            } else {
                throw new DuplicateRecipeNameException(DUPLICATE_RECIPE_NAMES_ERROR);
            }
        }
        return recipeToBeViewed;
    }
}

package seedu.duke.recipe;

import seedu.duke.exceptions.RecipeListEmptyError;
import seedu.duke.ui.StringLib;

import java.util.ArrayList;

public class RecipeList {
    protected static ArrayList<Recipe> recipeList;
    protected int currRecipeNumber;

    public RecipeList() {
        recipeList = new ArrayList<>();
        currRecipeNumber = 0;
    }

    public RecipeList(ArrayList<Recipe> inputRecipeList) {
        recipeList = inputRecipeList;
        currRecipeNumber = inputRecipeList.size();
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public int getCurrRecipeNumber() {
        return currRecipeNumber;
    }

    public Recipe getRecipeFromList(int index) {
              return recipeList.get(index);
        }

    public Recipe getNewestRecipe() {
        return recipeList.get(currRecipeNumber-1);
    }

    public void addNewRecipe(Recipe recipe) {
        recipeList.add(recipe);
        currRecipeNumber++;
    }

    public void removeRecipe(int index) throws RecipeListEmptyError {
        if (recipeList.isEmpty()) {
            throw new RecipeListEmptyError();
        }
        recipeList.remove(index-1);
        currRecipeNumber--;
    }

    public int findRecipeIndex(String recipeName) {
        Recipe currentRecipe;
        for (int i = 0; i < recipeList.size(); ++i) {
            currentRecipe = recipeList.get(i);
            if (currentRecipe.getName().equals(recipeName)) {
                return i;
            }
        }
        return StringLib.NOTFOUND_INDEX;
    }
}

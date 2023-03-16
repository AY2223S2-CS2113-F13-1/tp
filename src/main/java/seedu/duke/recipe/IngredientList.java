package seedu.duke.recipe;

import java.util.ArrayList;

public class IngredientList {
    protected ArrayList<Ingredient> list;

    protected int maxIngredientIndex;

    /**
     * Class constructor without arguments.
     */
    public IngredientList() {
        list = new ArrayList<>();
        maxIngredientIndex = 0;
    }

    /**
     * Class constructor with argument. An existing array list of ingredients
     * is used as the argument
     *
     * @param ingredientList - list of all ingredients in the recipe.
     */
    public IngredientList(ArrayList<Ingredient> ingredientList) {
        list = ingredientList;
        maxIngredientIndex = ingredientList.size();
    }

    public int getMaxIngredientIndex() {
        return maxIngredientIndex;
    }

    /**
     * Adds a new ingredient to the list.
     *
     * @param item - the ingredient to be added to the list.
     */
    private void addIngredient(Ingredient item) {
        list.add(item);
        maxIngredientIndex++;
    }

    /**
     * Removes a new ingredient to the list.
     *
     * @param index - the index of the ingredient to be removed from the list.
     */
    private void removeIngredient(int index) {
        list.remove(index-1);
        maxIngredientIndex--;
    }
    public Ingredient getIngredient(int ingredientIndex) {
        return list.get(ingredientIndex);
    }

}

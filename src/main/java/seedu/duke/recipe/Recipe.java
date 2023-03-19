package seedu.duke.recipe;

import java.util.ArrayList;

public class Recipe {
    protected IngredientList ingredientList;
    protected StepList stepList;
    protected String name;
    protected String tag;

    public Recipe(String inputName, String inputTag, IngredientList inputList, StepList inputSteps) {
        name = inputName;
        tag = inputTag;
        ingredientList = inputList;
        stepList = inputSteps;
    }

    public IngredientList getIngredientList() {
        return ingredientList;
    }
    public StepList getStepList() {
        return stepList;
    }

    public StepList getSteps() {
        return stepList;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
    public String toString() {
        return '[' + tag + "] " + name;
    }
}

package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.UI;
import seedu.duke.command.CommandType;

import java.util.ArrayList;

public class UITest {
    UI ui;
    RecipeList recipeList;
    private final CommandType type = CommandType.DELETE;
    @Test
    void printGreeting() {
        assertTrue(true);
    }
    @BeforeEach
    void setUp() {
        ui = new UI();
        recipeList = new RecipeList();
        try{
            ArrayList<String> parsed = Parser.parseRecipe(
                    "add n/Spaghetti i/Pasta, Tomato Sauce, Cheese t/Italian");
            String recipeName = parsed.get(0);
            IngredientList ingredientLists = Parser.parseIngredients(parsed.get(1));
            String recipeTag = parsed.get(2);
            StepList recipeSteps = new StepList();
            //TODO: add recipeSteps parser
            Recipe recipe = new Recipe(recipeName, recipeTag, ingredientLists, recipeSteps);
            recipeList.addNewRecipe(recipe);
        } catch (Exception e) {
            ui.showAddingRecipeErrorMessage(e);
        }
    }
    @Test
    void addRecipe() {
        assert(recipeList.getCurrRecipeNumber() == 1);
    }
    @Test
    void deleteRecipe() {
        try {
            recipeList.removeRecipe(1);
        } catch (Exception e) {
            ui.showDeletingTaskErrorMessage(e,type);
        }
        assert(recipeList.getCurrRecipeNumber() == 0);
    }
}

package seedu.duke.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.command.Command;
import seedu.duke.exceptions.InvalidIndexRangeException;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.command.CommandType.EDITINGREDIENT;

public class EditIngredientTest {
    ByteArrayOutputStream output;
    PrintStream console;
    UI ui;

    @BeforeEach
    public void setup() throws Exception {
        RecipeList.createRecipeList();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("maggi"));
        ingredients.add(new Ingredient("water"));
        IngredientList ingredientList = new IngredientList(ingredients);
        RecipeList.addNewRecipe(new
                Recipe("TypicalRecipe", "Fast", ingredientList, new StepList()));
        output = new ByteArrayOutputStream();
        console = System.out;
        ui = new UI();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
        RecipeList.clearRecipeList();
    }

    /**
     * Test the result of changing step 2 "water" to "boiling water"
     * Directly tests the editIngredient method in IngredientList class
     *
     * @throws Exception if test returns unexpected result
     */
    @Test
    public void editBasicTestCase() throws Exception {
        String newDescription = "boiling water";
        int replaceIndex = 1;
        Recipe recipeToEdit = RecipeList.getRecipeFromList(1);
        IngredientList ingredientListToEdit = recipeToEdit.getIngredientList();
        ingredientListToEdit.editIngredient(replaceIndex,newDescription);
        Ingredient expectedResult = new Ingredient(newDescription);

        assertEquals(expectedResult.getName(),
                ingredientListToEdit.getIngredient(replaceIndex).getName());

        assertEquals(StringLib.INGREDIENT_EDIT_SUCCESS + System.lineSeparator() +
                        (replaceIndex + 1) + ". "
                + ingredientListToEdit.getIngredient(replaceIndex).getName(),
                output.toString().trim());
    }
    /**
     * Test the result of NULL recipe index.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void editMissingRecipeIndexTestCase() throws Exception {
        new Command(EDITINGREDIENT,"").execute(ui);
        assertEquals(StringLib.RECIPE_EDITING_DEFAULT_ERROR +
                        "The index of EDITINGREDIENT cannot be empty.\n",
                '\n' + output.toString().trim() + '\n');
    }

    /**
     * Test the result of 0 recipe index
     *
     * @throws Exception if test has an error
     */
    @Test
    public void editRecipeIndexZeroTestCase() throws Exception {
        new Command(EDITINGREDIENT,"0").execute(ui);
        assertEquals(StringLib.POS_INT + System.lineSeparator() + "Valid range: " + 1 + " to " + 1,
                '\n' + output.toString().trim());
    }

    /**
     * Test the result of recipe index exceeding recipe list
     *
     * @throws Exception if test has an error
     */
    @Test
    public void editRecipeIndexExceededTestCase() throws Exception {
        new Command(EDITINGREDIENT,"6").execute(ui);
        assertEquals(StringLib.POS_INT + System.lineSeparator() + "Valid range: " + 1 + " to " + 1,
                '\n' + output.toString().trim());
    }

    /**
     * Test the result of ingredient index exceeds number of ingredients
     * in ingredientList
     *
     * @throws Exception if there is an unexpected result
     *
     */
    @Test
    public void editIngredientIndexExceededTestCase() throws Exception {
        String newDescription = "boiling water";
        int userInput = 3;
        Recipe recipeToEdit = RecipeList.getRecipeFromList(1);
        IngredientList ingredientListToEdit = recipeToEdit.getIngredientList();

        String exceptionOutput = "";
        try {
            ingredientListToEdit.isIndexWithinRange(userInput - 1);
        } catch (InvalidIndexRangeException e) {
            exceptionOutput = e.getMessage();
        }
        String expectedOutput = "Invalid Input! Valid Range: 1 to 2";
        assertEquals(expectedOutput,
                exceptionOutput);

    }

    /**
     * Test the result of ingredient index is 0 in non-empty ingredientList
     *
     * @throws Exception if there is an unexpected result
     *
     */
    @Test
    public void editIngredientIndexZeroTestCase() throws Exception {
        String newDescription = "boiling water";
        int userInput = 0;
        Recipe recipeToEdit = RecipeList.getRecipeFromList(1);
        IngredientList ingredientListToEdit = recipeToEdit.getIngredientList();

        String exceptionOutput = "";
        try {
            ingredientListToEdit.isIndexWithinRange( userInput - 1);
        } catch (InvalidIndexRangeException e) {
            exceptionOutput = e.getMessage();
        }
        String expectedOutput = "Invalid Input! Valid Range: 1 to 2";
        assertEquals(expectedOutput,
                exceptionOutput);

    }

}

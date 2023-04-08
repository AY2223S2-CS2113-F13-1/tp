package seedu.duke.recipe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.exceptions.InvalidIndexRangeException;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.command.CommandType.EDITINGREDIENT;

public class EditStepTest {
    ByteArrayOutputStream output;
    PrintStream console;
    UI ui;

    @BeforeEach
    public void setup() throws Exception {
        RecipeList.createRecipeList();
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(new Step("boil water"));
        steps.add(new Step("drink boiling water"));
        StepList stepList = new StepList(steps);
        RecipeList.addNewRecipe(new
                Recipe("TypicalRecipe", "Fast", new IngredientList(), stepList));
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
     * Test the result of changing step 2 "drink boiling water" to "add boiling water"
     * Directly tests the editStep method in StepList class
     *
     * @throws Exception if test returns unexpected result
     */
    @Test
    public void editBasicTestCase() throws Exception {
        String newDescription = "add boiling water";
        int replaceIndex = 1;
        Recipe recipeToEdit = RecipeList.getRecipeFromList(1);
        StepList stepListToEdit = recipeToEdit.getStepList();
        stepListToEdit.editStep(replaceIndex,newDescription);
        Step expectedResult = new Step(newDescription);

        assertEquals(expectedResult.getStepDescription(),
                stepListToEdit.getStep(replaceIndex).getStepDescription());

        assertEquals(StringLib.STEP_EDIT_SUCCESS + System.lineSeparator() +
                        (replaceIndex + 1) + ". "
                        + stepListToEdit.getStep(replaceIndex).getStepDescription(),
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
     * Test the result of step index exceeds number of steps
     * in stepList
     *
     * @throws Exception if there is an unexpected result
     *
     */
    @Test
    public void editStepIndexExceededTestCase() throws Exception {
        String newDescription = "boiling water";
        int userInput = 3;
        Recipe recipeToEdit = RecipeList.getRecipeFromList(1);
        StepList stepListToEdit = recipeToEdit.getStepList();

        String exceptionOutput = "";
        try {
            stepListToEdit.isIndexWithinRange(userInput - 1);
        } catch (InvalidIndexRangeException e) {
            exceptionOutput = e.getMessage();
        }
        String expectedOutput = "Invalid Input! Valid Range: 1 to 2";
        assertEquals(expectedOutput,
                exceptionOutput);

    }

    /**
     * Test the result of step index is 0 in non-empty stepList
     *
     * @throws Exception if there is an unexpected result
     *
     */
    @Test
    public void editStepIndexZeroTestCase() throws Exception {
        String newDescription = "boiling water";
        int userInput = 0;
        Recipe recipeToEdit = RecipeList.getRecipeFromList(1);
        StepList stepListToEdit = recipeToEdit.getStepList();

        String exceptionOutput = "";
        try {
            stepListToEdit.isIndexWithinRange( userInput - 1);
        } catch (InvalidIndexRangeException e) {
            exceptionOutput = e.getMessage();
        }
        String expectedOutput = "Invalid Input! Valid Range: 1 to 2";
        assertEquals(expectedOutput,
                exceptionOutput);

    }

}

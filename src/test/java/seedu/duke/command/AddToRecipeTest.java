package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddToRecipeTest {
    ByteArrayOutputStream output;
    PrintStream console;
    UI ui;
    @BeforeEach
    public void setup() throws Exception {
        RecipeList.createRecipeList();
        RecipeList.addNewRecipe(new
                Recipe("Oyakodon", "Japanese", new IngredientList(), new StepList()));

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
    @Test
    public void testAddToRecipeIngredientNoDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i id/1").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientNoId() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i desc/Egg").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION,output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientNoIdDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeNoType() throws Exception {
        new Command(CommandType.ADDTORECIPE, "id/1 desc/Flammenwerfer").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientEmptyDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i id/1 desc/").execute(ui);
        assertEquals(StringLib.EMPTY_INGREDIENT_DESCRIPTION_MESSAGE, output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepEmptyDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s id/1 desc/").execute(ui);
        assertEquals(StringLib.EMPTY_STEP_DESCRIPTION_MESSAGE, output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepNoDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s id/1").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepNoId() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s desc/Beat egg").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION,output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepNoIdDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s").execute(ui);
        assertEquals(StringLib.INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
}

package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {
    ByteArrayOutputStream output;
    PrintStream console;
    @BeforeEach
    public void setup() throws Exception {
        RecipeList.createRecipeList();
        RecipeList.addNewRecipe(new
                Recipe("Spaghetti", "Italian", new IngredientList(), new StepList()));
        output = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
        RecipeList.clearRecipeList();
    }

    /**
     * Test the result of NULL input.
     * @throws Exception if there is an error in the test
     */
    @Test
    public void testResultA() throws Exception {
        RecipeList.searchRecipeList("");
        String s = "Find is missing KEYWORDS!";
        assertEquals(s,output.toString().trim());
    }
    @Test
    public void testResultC() throws Exception {
        RecipeList.searchRecipeList("  ");
        String s = "Find is missing KEYWORDS!";
        assertEquals(s,output.toString().trim());
    }

    /**
     * Test the result of no find
     * @throws Exception if there is an error in the test
     */
    @Test
    public void testResultB() throws Exception {
        RecipeList.searchRecipeList("Pasta");
        String s = "No dishes matches what you are looking for! :(";
        assertEquals(s,output.toString().trim());
    }
    @Test
    public void testFindByTagEmpty1() throws Exception {
        RecipeList.searchByTag("");
        String s = "Find is missing KEYWORDS!";
        assertEquals(s,output.toString().trim());
    }
    @Test
    public void testFindByTagEmpty2() throws Exception {
        RecipeList.searchByTag("Chinese ");
        String s = "No dishes matches what you are looking for! :(";
        assertEquals(s,output.toString().trim());
    }
    @Test
    public void testFindByTagNoMatch() throws Exception {
        RecipeList.searchByTag("XXX");
        String s = "No dishes matches what you are looking for! :(";
        assertEquals(s, output.toString().trim());
    }
}

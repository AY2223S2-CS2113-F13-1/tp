package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.UI;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.command.CommandType.VIEW;

public class ViewTest {
    ByteArrayOutputStream output;
    PrintStream console;
    RecipeList recipeList;
    UI ui;

    private static final String VIEW_HOTPOT = "Here is the recipe you requested, which is Chinese flavour:" +
            System.lineSeparator() + "name: Hotpot" + System.lineSeparator() +
            "__________________________________________________________" + System.lineSeparator() +
            "There are 0 ingredients in the list:" + System.lineSeparator() +
            "__________________________________________________________" + System.lineSeparator() +
            "This recipe has no steps!";
    private static final String DUPLICATE_RECIPE_NAMES_ERROR = "There appears to be duplicate recipe names " +
            "that exist. \nPlease use the FIND feature to get the specific recipe index to be viewed instead.";
    private static final String NO_MATCHING_RECIPE_ERROR = "There are no matching recipes found.";
    private static final String RECIPE_VIEWING_DEFAULT_ERROR = "Error in viewing recipe!"
            + "\nException occurred: ";
    String MISSING_DESCRIPTION_ERROR = "Error in description of inputs!"
            + "\nException occurred: The KEYWORDS of VIEW cannot be empty.";
    private static final String PREFIX_EMPTY_LIMIT_LIST_ERROR = "Error in finding index!"
            + "\nException occurred: Your list is either EMPTY or does not contain "
            + "recipes up to the index you inputted yet,\n"
            + "so you cannot use the ";
    private static final String SUFFIX_EMPTY_LIMIT_LIST_ERROR = " command yet! Try filling up the list first!";
    private static final String INVALID_RANGE = "The range you have entered for the index is invalid!\n\n" +
            "Valid Range: 1 to 3";

    @BeforeEach
    public void setup() throws Exception {
        recipeList = new RecipeList();
        recipeList.addNewRecipe(new
                Recipe("Oyakodon", "Japanese", new IngredientList(), new StepList()));
        recipeList.addNewRecipe(new
                Recipe("OyakodON", "Japanese", new IngredientList(), new StepList()));
        recipeList.addNewRecipe(new
                Recipe("Hotpot", "Chinese", new IngredientList(), new StepList()));
        output = new ByteArrayOutputStream();
        console = System.out;
        ui = new UI();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
    }

    /**
     * Test the result of viewing Hotpot recipe.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void viewPositiveTestCase() throws Exception {
        new Command(VIEW, "3").execute(recipeList, ui);
        assertEquals(VIEW_HOTPOT, output.toString().trim());
    }

    /**
     * Test the result of empty view description.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void viewEmptyDescription() throws Exception {
        new Command(VIEW, "").execute(recipeList, ui);
        assertEquals(MISSING_DESCRIPTION_ERROR, output.toString().trim());
    }

    /**
     * Test the result of NULL view description.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void viewNullRecipe() throws Exception {
        new Command(VIEW, null).execute(recipeList, ui);
        assertEquals(PREFIX_EMPTY_LIMIT_LIST_ERROR + "VIEW" + SUFFIX_EMPTY_LIMIT_LIST_ERROR,
                output.toString().trim());
    }

    /**
     * Test the result of viewing recipe not in the list.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void noMatchingRecipe() throws Exception {
        new Command(VIEW, "Gyudon").execute(recipeList, ui);
        assertEquals(RECIPE_VIEWING_DEFAULT_ERROR + NO_MATCHING_RECIPE_ERROR, output.toString().trim());
    }

    /**
     * Test the result of viewing recipes with duplicate names.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void duplicateRecipes() throws Exception {
        new Command(VIEW, "Oyakodon").execute(recipeList, ui);
        assertEquals(RECIPE_VIEWING_DEFAULT_ERROR + DUPLICATE_RECIPE_NAMES_ERROR, output.toString().trim());
    }

    /**
     * Test the result of viewing recipe index that is not in valid range.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void recipeIndexOutOfBound() throws Exception {
        new Command(VIEW, "-10").execute(recipeList, ui);
        assertEquals(RECIPE_VIEWING_DEFAULT_ERROR + INVALID_RANGE,
                output.toString().trim());
    }


    /**
     * Test the result of viewing by index on an empty list.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void viewingByIndexOnEmptyRecipeList() throws Exception {
        recipeList.clearRecipeList();
        new Command(VIEW, "2").execute(recipeList, ui);
        assertEquals(PREFIX_EMPTY_LIMIT_LIST_ERROR + "VIEW" + SUFFIX_EMPTY_LIMIT_LIST_ERROR,
                output.toString().trim());
    }

    /**
     * Test the result of viewing by name on an empty list.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    public void viewingByNameOnEmptyRecipeList() throws Exception {
        recipeList.clearRecipeList();
        new Command(VIEW, "Hotpot").execute(recipeList, ui);
        assertEquals(PREFIX_EMPTY_LIMIT_LIST_ERROR + "VIEW" + SUFFIX_EMPTY_LIMIT_LIST_ERROR,
                output.toString().trim());
    }
}
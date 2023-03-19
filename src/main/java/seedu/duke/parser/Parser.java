package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.recipe.Ingredient;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Step;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.UI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.ui.StringLib.STEP_INPUT_END;

public class Parser {

    private static final String RECIPE_MISSING_NAME_INGREDIENTS_TAG = "Recipe is missing the \"NAME\" "
            + "or \"INGREDIENTS\" or \"TAG\"!\n";
    private static final String RECIPE_MISSING_NAME = "Recipe is missing \"NAME\"!\n";
    private static final String RECIPE_MISSING_INGREDIENTS = "Recipe is missing \"INGREDIENTS\"!\n";
    private static final String RECIPE_MISSING_TAG = "Recipe is missing \"TAG\"!\n";


    /**
     * Returns a <code>Command</code> type which contains the command to be
     * executed and its full description to support its execution. It takes
     * in a string representing the user input.
     *
     * @param line the input line by user.
     * @return a <code>Command</code> containing the command to be executed.
     */
    public static Command parseCommands(String line) {
        String[] lineSpaced = line.split(" ");
        String fullDescription = "";
        if (lineSpaced.length > 1) {
            for (int i = 2; i < lineSpaced.length; i++) {
                lineSpaced[1] = lineSpaced[1].concat(" " + lineSpaced[i]);
            }
            fullDescription = lineSpaced[1];
        }
        CommandType type;
        switch (lineSpaced[0]) {
        case "list":
            type = CommandType.LIST;
            break;
        case "add":
            type = CommandType.ADD;
            break;
        case "view":
            type = CommandType.VIEW;
            break;
        case "delete":
            type = CommandType.DELETE;
            break;
        case "help":
            type = CommandType.HELP;
            break;
        case "find":
            type = CommandType.FIND;
            break;
        case "clear":
            type = CommandType.CLEAR;
            break;
        case "exit":
            type = CommandType.EXIT;
            break;
        case "save":
            type = CommandType.SAVE;
            break;
        default:
            type = CommandType.UNKNOWN;
        }
        return new Command(type, fullDescription);
    }

    /**
     * Returns an Array of Strings containing the parsed full description
     * of a <code>Recipe</code> into its name, ingredients and tag.
     *
     * @param description the full description of the <code>Recipe</code>.
     * @return parsed ArrayList of Strings containing the recipe's name, ingredients list and tag.
     * @throws IncompleteInputException if full description input is missing the description or due date or both.
     */
    public static ArrayList<String> parseRecipe(String description) throws IncompleteInputException {
        ArrayList<String> parsed = new ArrayList<>();
        String[] parsedName = description.split(" i/");
        String[] parsedIngredientsTag = parsedName[1].split(" t/");
        parsed.add(parsedName[0].substring(2));
        parsed.add(parsedIngredientsTag[0]);
        parsed.add(parsedIngredientsTag[1]);
        if (parsed.size() < 3) {
            throw new IncompleteInputException(RECIPE_MISSING_NAME_INGREDIENTS_TAG);
        }
        if (parsed.get(0).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_NAME);
        }
        if (parsed.get(1).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_INGREDIENTS);
        }
        if (parsed.get(2).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_TAG);
        }
        return parsed;
    }

    public static IngredientList parseIngredients(String inputIngredients) {
        ArrayList<Ingredient> parsed = new ArrayList<>();
        String[] parsedIngredients = inputIngredients.split(", ");
        for (String ingredient : parsedIngredients) {
            parsed.add(new Ingredient(ingredient));
        }
        return new IngredientList(parsed);
    }

    public static StepList parseSteps(UI ui) {
        ArrayList<Step> parsed = new ArrayList<>();
        String inputDescription;
        while (true) {
            inputDescription = ui.readCommand();
            if (inputDescription.equals(STEP_INPUT_END)) {
                break;
            }
            Step inputStep = new Step(inputDescription);
            System.out.println(inputStep);
            parsed.add(inputStep);
        }
        return new StepList(parsed);
    }

}

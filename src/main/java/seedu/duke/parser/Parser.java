package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.CommandType;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.recipe.Ingredient;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Step;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.util.ArrayList;

import static seedu.duke.ui.IntLib.RECIPE_NAME_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_INGREDIENTS_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_TAG_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_SUM_OF_STEPS_INDEX;

public class Parser {

    private static final String RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP = "Recipe is missing the \"NAME\" "
            + "or \"INGREDIENTS\" or \"TAG\" or \"SUM of the STEPs" +
            "\n or there is more than one \"NAME\" or \"INGREDIENTS\" or \"TAG\" or \"SUM of the STEPs\"!\n";
    private static final String RECIPE_WRONG_LEADING_STRING = "Recipe contains the leading string!\n";
    private static final String RECIPE_MISSING_NAME = "Recipe is missing \"NAME\"!\n";
    private static final String RECIPE_MISSING_INGREDIENTS = "Recipe is missing \"INGREDIENTS\"!\n";
    private static final String RECIPE_MISSING_TAG = "Recipe is missing \"TAG\"!\n";
    private static final String RECIPE_MISSING_STEP = "Recipe is missing \"SUM of the STEPs\"!\n";
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
            fullDescription = lineSpaced[1].trim();
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
        case "find":
            type = CommandType.FIND;
            break;
        case "editstep":
            type = CommandType.EDITSTEP;
            break;
        case "editingredient":
            type = CommandType.EDITINGREDIENT;
            break;
        case "delete":
            type = CommandType.DELETE;
            break;
        case "help":
            type = CommandType.HELP;
            break;
        case "clear":
            type = CommandType.CLEAR;
            break;
        case "exit":
            type = CommandType.EXIT;
            break;
        default:
            type = CommandType.UNKNOWN;
        }
        return new Command(type, fullDescription);
    }


    private static boolean matchString(String input, String regex) {
        String matcher = input;
        int count = 0;
        while (matcher.contains(regex)){
            matcher=matcher.substring(matcher.indexOf(regex)+1);
            ++count;
        }
        boolean isMatch = (count==1);
        return isMatch;
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
        if(!matchString(description,"n/") || !matchString(description,"i/")
                || !matchString(description,"t/") || !matchString(description,"s/")){
            throw new IncompleteInputException(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP);
        }
        if(!description.substring(0,2).equals("n/")){
            throw new IncompleteInputException(RECIPE_WRONG_LEADING_STRING);
        }
        String[] parsedName = description.split(" i/");
        String[] parsedIngredientsTag = parsedName[1].split(" t/");
        String[] parsedTagStep = parsedIngredientsTag[1].split(" s/");
        parsed.add(parsedName[0].substring(2).trim());
        parsed.add(parsedIngredientsTag[0].trim());
        parsed.add(parsedTagStep[0].trim());
        try {
            parsed.add(parsedTagStep[1].trim());
        }catch (Exception e){
            throw new IncompleteInputException(RECIPE_MISSING_STEP);
        }

        if (parsed.size() < 4) {
            throw new IncompleteInputException(RECIPE_WRONG_NAME_INGREDIENTS_TAG_STEP);
        }
        if (parsed.get(RECIPE_NAME_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_NAME);
        }
        if (parsed.get(RECIPE_INGREDIENTS_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_INGREDIENTS);
        }
        if (parsed.get(RECIPE_TAG_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_TAG);
        }
        if (parsed.get(RECIPE_SUM_OF_STEPS_INDEX).isEmpty()) {
            throw new IncompleteInputException(RECIPE_MISSING_STEP);
        }
        try {
            Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX));
            if(Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX))<0){
                throw new IncompleteInputException(StringLib.MISSING_NUM);
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new IncompleteInputException(StringLib.MISSING_NUM);
        }
    }

    public static IngredientList parseIngredients(String inputIngredients) {
        ArrayList<Ingredient> parsed = new ArrayList<>();
        String[] parsedIngredients = inputIngredients.split(",");
        for (String ingredient : parsedIngredients) {
            parsed.add(new Ingredient(ingredient.trim()));
        }
        return new IngredientList(parsed);
    }

    public static StepList parseSteps(UI ui, int sumOfSteps) {
        ArrayList<Step> parsedStepList = new ArrayList<>();
        String inputStep;
        for (int i = 1; i <= sumOfSteps; i++) {
            ui.showStepInsertMessage(i);
            inputStep = ui.readCommand();
            while (inputStep.trim().equals("")) {
                ui.showInvalidStepMessage();
                inputStep = ui.readCommand();
            }
            parsedStepList.add(new Step(inputStep));
        }
        return new StepList(parsedStepList);
    }
}

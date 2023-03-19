package seedu.duke.storage;

import seedu.duke.recipe.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.duke.ui.StringLib.*;


/**
 * The <code>Storage</code> class contains various methods which involves
 * handling of the saved file. For instance, file reading, file writing and saving.
 */
public class Storage {

    private static final String SEPARATOR = " I:I ";
    private static final String DIRECTORY_CREATED = "\nDirectory for file saving created.";
    private static final String DIRECTORY_EXISTS = "\nDirectory for file saving already exists.";
    private static final String FILE_CREATED = "Save file created.";
    private static final String FILE_EXISTS = "Save file already exists.";
    private static final String FILE_PARSING_ERROR = "The task does not meet the parsing requirements for unpacking.";
    private static String filePath;

    /**
     * Sets the <code>filePath</code> to a specific value.
     *
     * @param filePath the String containing the location of the file path to be created.
     */
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Creates a new directory to store
     * save file, if it does not already exist.
     *
     * @throws IOException if an I/O error has occurred.
     */
    public static void createDirectory() throws IOException {
        File directory = new File("data");
        if (directory.mkdir()) {
            System.out.println(DIRECTORY_CREATED + "\n");
        } else {
            System.out.println(DIRECTORY_EXISTS + "\n");
        }
    }
    public static void writeSavedFile() throws IOException {
        File saveFile;
        FileWriter saveWriter;
        for (Recipe dish : RecipeList.getRecipeList()) {
            saveFile = new File("data/" + dish.getName());
            System.out.println(dish.getName());
            saveWriter = new FileWriter("data/" + dish.getName() + ".txt");
            saveWriter.write(dish.getName() + "\n");
            saveWriter.write(dish.getTag() + "\n");
            saveWriter.write(INGREDIENT_LIST + "\n");
            for (Ingredient ingredient : dish.getIngredientList().getList()) {
                saveWriter.write( ingredient.getName() + "\n");
            }
            saveWriter.write(STEP_LIST + "\n");
            for (Step step : dish.getStepList().getList()) {
                saveWriter.write(step.getStep() + "\n");
            }
            saveWriter.close();
        }
    }
    public static ArrayList<File> findValidSaveFiles() {
        File searchArea = new File("data");
        File[] saveList = searchArea.listFiles();
        ArrayList<File> validSaveFiles = new ArrayList<>();
        for (File item : saveList) {
            if (item.getName().contains(".txt")) {
                validSaveFiles.add(item);
            }
        }
        return validSaveFiles;
    }
    public static ArrayList<Recipe> readSaveFiles() throws FileNotFoundException {
        ArrayList<File> validSaves = findValidSaveFiles();
        ArrayList<Recipe> recipeList = new ArrayList<>();
        for (File saveFile : validSaves) {
            Scanner reader = new Scanner(saveFile);
            String name = reader.nextLine();
            String tag = reader.nextLine();
            ArrayList<Ingredient> ingredientList = new ArrayList<>();
            ArrayList<Step> stepList = new ArrayList<>();
            while (reader.hasNextLine()) {
                String ingredient = reader.nextLine();
            if (ingredient.equals(INGREDIENT_LIST)) {
                } else {
                    ingredientList.add(new Ingredient(ingredient));
                }
            }
            while (reader.hasNextLine()) {
                String step = reader.nextLine();
                if (step.equals(IMPORT_END_RECIPE)) {
                    break;
                } else if (step.equals(STEP_LIST)) {
                } else {
                    stepList.add(new Step(step));
                }
            }
            recipeList.add(new Recipe(
                    name,
                    tag,
                    new IngredientList(ingredientList),
                    new StepList(stepList)));
        }
        return recipeList;
    }
}

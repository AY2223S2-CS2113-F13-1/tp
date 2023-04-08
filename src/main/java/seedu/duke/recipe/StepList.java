package seedu.duke.recipe;

import java.util.ArrayList;

import seedu.duke.exceptions.InvalidIndexRangeException;
import seedu.duke.exceptions.ListEmptyException;
import seedu.duke.ui.IntLib;
import seedu.duke.ui.UI;
import seedu.duke.ui.StringLib;

public class StepList {
    protected ArrayList<Step> stepList;
    protected int currStepNumber;

    /**
     * Class constructor without arguments.
     */
    public StepList() {
        stepList = new ArrayList<>();
        currStepNumber = 0;
    }
    /**
     * Class constructor with argument. An existing array list of steps
     * is used as the argument
     *
     * @param stepList - list of all steps in the recipe.
     *
     */
    public StepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
        currStepNumber = stepList.size();
    }


    /**
     * Adds a new step to the list.
     *
     * @param step - the step to be added to the list.
     * @param index - position to be added. Current step object at this position is
     *              shifted towards the back.
     */
    public void addStep(Step step, int index) {
        stepList.add(index, step);
        currStepNumber++;
        assert (currStepNumber == stepList.size());
    }
    /**
     * Removes a step from the list.
     *
     * @param stepIndex - the index of the step to be removed from the list.
     */
    public void removeStep(int stepIndex) {
        stepList.remove(stepIndex - 1);
        currStepNumber--;
        assert (currStepNumber == stepList.size());
    }

    public boolean isIndexWithinRange(int stepIndex) throws Exception{
        if (currStepNumber == 0) {
            throw new ListEmptyException();
        } else if (stepIndex < 0 || stepIndex >= currStepNumber) {
            throw new InvalidIndexRangeException(IntLib.NONEMPTY_START_NUMBER,currStepNumber);
        }
        return true;
    }
    /**
     * Replaces a specified step in the list with a new step object
     * @param stepIndex index of the step to be replaced
     * @param ui scanner class
     */
    public void editStep(int stepIndex, UI ui) {
        System.out.println(StringLib.ENTER_STEP_DESCRIPTION);
        String description = ui.readCommand();
        Step newStep = new Step(description);
        stepList.set(stepIndex, newStep);
        System.out.println(StringLib.STEP_EDIT_SUCCESS);
        System.out.print((stepIndex + 1) + ". ");
        System.out.println(stepList.get(stepIndex).toString());
    }

    public void editStep(int stepIndex, String description) {
        Step newStep = new Step(description);
        stepList.set(stepIndex, newStep);
        System.out.println(StringLib.STEP_EDIT_SUCCESS);
        System.out.print((stepIndex + 1) + ". ");
        System.out.println(stepList.get(stepIndex).toString());
    }
    public void showFullStepList() {
        System.out.println("There are " + currStepNumber + " steps in the list");
        for (int i = 0; i < currStepNumber; i++) {
            System.out.println((i + 1) + ". " + stepList.get(i).getStepDescription());
        }
    }
    public void showStepByStep(UI ui) {
        String input;
        for (int i = 0; i < currStepNumber; i++) {
            System.out.println((i + 1) + ". " + stepList.get(i).getStepDescription());
            if (i == currStepNumber - 1) {
                System.out.println(StringLib.END_OF_RECIPE_STEPS);
                return;
            }
            if (ui.readCommand().equals(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                return;
            }
        }
    }
    public void showStepList(UI ui) {
        if (currStepNumber == 0) {
            System.out.println(StringLib.RECIPE_NO_STEPS);
            return;
        }
        System.out.println("There are " + currStepNumber + " steps in the list");
        System.out.println(StringLib.STEPBYSTEP_PROMPT);
        String input = ui.readCommand();
        if (input.equalsIgnoreCase("yes")) {
            System.out.println(StringLib.STEPBYSTEP_EARLY_TERMINATION_PROMPT);
            showStepByStep(ui);
        } else {
            showFullStepList();
        }
    }
    public Step getStep(int stepIndex) {
        return stepList.get(stepIndex);
    }
    public int getCurrStepNumber() {
        return currStepNumber;
    }
    public ArrayList<Step> getList() {
        return stepList;
    }

}

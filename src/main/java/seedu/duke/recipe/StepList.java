package seedu.duke.recipe;

import java.util.ArrayList;

public class StepList {
    protected ArrayList<Step> list;

    protected int maxStepIndex;

    /**
     * Class constructor without arguments.
     */
    public StepList() {
        list = new ArrayList<>();
        maxStepIndex = 0;
    }

    /**
     * Gets the maximum index in the current StepList
     * @return integer value for largest step index
     */
    public int getMaxStepIndex() {
        return maxStepIndex;
    }

    /**
     * Outputs the current StepList
     * @return current StepList instance
     */
    public ArrayList<Step> getList() {
        return list;
    }

    /**
     * Gets a requested step from the StepList
     * @param stepIndex the index of the step in the StepList
     * @return Step object for the required Step
     */
    public Step getStep(int stepIndex) {
        return list.get(stepIndex);
    }

    /**
     * Adds a Step object to the end of the StepList
     * @param newStep Step to be added to the StepList
     */
    public void addStep(Step newStep) {
        list.add(newStep);
        maxStepIndex++;
    }

    /**
     * Replaces an existing Step object with a user-supplied Step
     * @param newStep Step object that the original Step will be replaced with
     * @param stepIndex index of the step object to be replaced
     */
    public void editStep(Step newStep, int stepIndex) {
        list.set(stepIndex,newStep);
    }

}

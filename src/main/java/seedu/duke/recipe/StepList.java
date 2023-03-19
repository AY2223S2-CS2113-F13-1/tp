package seedu.duke.recipe;

import java.util.ArrayList;

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
     */
    public StepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
        currStepNumber = stepList.size();
    }
    private void addStep(Step step) {
        stepList.add(step);
        currStepNumber++;
    }
    private void removeStep(int stepIndex) {
        stepList.remove(stepIndex - 1);
        currStepNumber--;
    }
    public void showStepList() {
        System.out.println("There are " + currStepNumber + " steps in the list");
        for (int i = 0; i < currStepNumber; i++) {
            System.out.println((i+1) + ". " + stepList.get(i).getStep());
        }
    }
    public ArrayList<Step> getList() {
        return stepList;
    }

}

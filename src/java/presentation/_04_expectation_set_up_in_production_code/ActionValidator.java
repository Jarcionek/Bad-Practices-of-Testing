package presentation._04_expectation_set_up_in_production_code;

public class ActionValidator {

    public boolean isValidAction(String action) {
        try {
            Action.valueOf(action);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}

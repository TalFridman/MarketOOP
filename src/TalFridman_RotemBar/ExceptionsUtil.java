package TalFridman_RotemBar;

public class ExceptionsUtil {

    public void checkStringInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or only contain whitespaces\n");
        }
    }
    public void checkNoNumInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or only contain whitespaces\n");
        }if (!input.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Input can not contain a number\n");
        }
    }
    public void checkOnlyNumInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or only contain whitespaces\n");
        }if (!input.matches("[0-9]+")) {
            throw new IllegalArgumentException("Input can contain only numbers\n");
        }
    }
    public void checkNotOnlyNumInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or only contain whitespaces\n");
        }if (input.matches("[0-9]+")) {
            throw new IllegalArgumentException("Input can not contain only numbers\n");
        }
        if (input.matches("[0-9 ]+")) {
            throw new IllegalArgumentException("Input can not contain only numbers and spaces\n");
        }

    }


    public void checkCategoryExists(String category) {
         Category.valueOf(category);
    }

    public void checkBooleanInput(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty\n");
        }
        if (!str.equalsIgnoreCase("y")  && !str.equalsIgnoreCase("n")) {
            throw new IllegalArgumentException("Input can be Y or N\n");
        }
    }

    public void checkRangeInput(String str, int length) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty\n");
        }
        if (!str.matches("[0-9]+")) {
        throw new IllegalArgumentException("Input can contain only numbers\n");
    }
        if (Integer.valueOf(str)>length||Integer.valueOf(str)<=0){
            throw new IllegalArgumentException("Input is out of range\n");
        }



    }

    public void checkMenuInput(String menuInput) {
        if (menuInput == null || menuInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or only contain whitespaces\n");
        }if (!menuInput.matches("[0-9]+")) {
            throw new IllegalArgumentException("Input can contain only numbers\n");
        }    if (Integer.valueOf(menuInput)>9||Integer.valueOf(menuInput)<0){
            throw new IllegalArgumentException("Input is out of range\n");
        }

    }
}

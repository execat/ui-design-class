package ui;

import java.awt.*;

/**
 * Created by atm on 10/3/15.
 */
public class Validator {
    public static boolean minLength(String str, int lim) {
        if (str == null) { return false; }
        if (str.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean maxLength(String str, int lim) {
        if (str == null) { return false; }
        if (str.length() > lim) { return false; }
        return true;
    }

    public static boolean exactLength(String str, int lim) {
        if (str == null) { return false; }
        if (str.length() != lim) { return false; }
        return true;
    }

    // TODO
    public static boolean existence(Component component) {
        return true;
    }

    private static boolean existenceTextfield(TextField textField) {
        return true;
    }
}

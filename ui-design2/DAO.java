package ui;

/**
 * Created by atm on 9/26/15.
 */
public interface DAO {
    default void setup() throws Exception {
        return;
    }

    default void connect() throws Exception {
        return;
    }

    default void close() throws Exception {
        return;
    }
}

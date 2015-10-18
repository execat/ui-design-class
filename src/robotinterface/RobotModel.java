package robotinterface;

/**
 * Created by atm on 10/17/15.
 */
public class RobotModel implements Model {
    State state;
    RobotModel() {
        state = new State();
    }

    State getState() {
        return state;
    }


}

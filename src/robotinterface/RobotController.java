package robotinterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by atm on 10/17/15.
 */
public class RobotController implements Controller {
    RobotModel model;

    RobotController() {
        model = new RobotModel();
    }

    @Override
    public void directions(JButton clickedButton) {
    }

    @Override
    public void armMovement(JButton clickedButton) {

    }

    @Override
    public void armGrabRelease(JButton clickedButton) {

    }

    @Override
    public void fetchCamera(Canvas canvas) {

    }

    @Override
    public void fetchTemperature(JLabel celsius, JLabel farenheit) {

    }

    @Override
    public State fetchState() {
        return model.getState();
    }
}

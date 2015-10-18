package robotinterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by atm on 10/17/15.
 */
public interface Controller {
    void directions(JButton clickedButton);

    void armMovement(JButton clickedButton);

    void armGrabRelease(JButton clickedButton);

    void fetchCamera(Canvas canvas);

    void fetchTemperature(JLabel celsius, JLabel farenheit);

    State fetchState();
}
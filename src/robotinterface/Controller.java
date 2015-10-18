package robotinterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by atm on 10/17/15.
 */
public interface Controller {
    State moveRobotForward();
    State moveRobotBackward();
    State moveRobotLeft();
    State moveRobotRight();
    State playPause();

    State moveArmUp();
    State moveArmDown();
    State grabReleaseArm();

    State getCamera();

    State getTemperature();

    State getState();
}
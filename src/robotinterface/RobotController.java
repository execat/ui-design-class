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
    public State moveRobotForward() {
        model.moveRobotForward();
        return getState();
    }

    @Override
    public State moveRobotBackward() {
        model.moveRobotBackward();
        return getState();
    }

    @Override
    public State moveRobotLeft() {
        model.moveRobotLeft();
        return getState();
    }

    @Override
    public State moveRobotRight() {
        model.moveRobotRight();
        return getState();
    }

    @Override
    public State playPause() {
        model.playPause();
        return getState();
    }

    @Override
    public State moveArmUp() {
        model.moveArmUp();
        return getState();
    }

    @Override
    public State moveArmDown() {
        model.moveArmDown();
        return getState();
    }

    @Override
    public State grabReleaseArm() {
        model.grabReleaseArm();
        return getState();
    }

    @Override
    public State getCamera() {
        model.fetchCamera();
        return getState();
    }

    @Override
    public State getTemperature() {
        model.fetchTemperature();
        return getState();
    }

    @Override
    public State getState() {
        return model.getState();
    }
}

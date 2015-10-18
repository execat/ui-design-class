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
        return model.getState();
    }

    @Override
    public State moveRobotBackward() {
        model.moveRobotBackward();
        return model.getState();
    }

    @Override
    public State moveRobotLeft() {
        model.moveRobotLeft();
        return model.getState();
    }

    @Override
    public State moveRobotRight() {
        model.moveRobotRight();
        return model.getState();
    }

    @Override
    public State playPause() {
        model.playPause();
        return model.getState();
    }

    @Override
    public State moveArmUp() {
        model.moveArmUp();
        return model.getState();
    }

    @Override
    public State moveArmDown() {
        model.moveArmDown();
        return model.getState();
    }

    @Override
    public State grabReleaseArm() {
        model.grabReleaseArm();
        return model.getState();
    }

    @Override
    public State getCamera() {
        model.fetchCamera();
        return model.getState();
    }

    @Override
    public State getTemperature() {
        model.fetchTemperature();
        return model.getState();
    }

    @Override
    public State getState() {
        return model.getState();
    }
}

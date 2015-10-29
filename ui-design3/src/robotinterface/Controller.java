package robotinterface;

/**
 * Controller interface
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

    State toggleCamera();

    State getTemperature();

    State getState();
}
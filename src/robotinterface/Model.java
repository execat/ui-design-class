package robotinterface;

/**
 * Template for RobotModel
 */
public interface Model {
    State getState();
    State moveRobotForward();
    State moveRobotBackward();
    State moveRobotLeft();
    State moveRobotRight();
    State playPause();
    State moveArmUp();
    State moveArmDown();
    State grabReleaseArm();
    State toggleCamera();
    State fetchTemperature();
}

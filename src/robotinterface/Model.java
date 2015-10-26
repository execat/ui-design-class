package robotinterface;

/**
 * Created by atm on 10/17/15.
 */
public interface Model {
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

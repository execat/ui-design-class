package robotinterface;

/**
 * Created by atm on 10/17/15.
 */
public class RobotModel implements Model {

    private State state;

    RobotModel() {
        state = new State();
    }

    State getState() {
        state.updateState();
        return state;
    }

    @Override
    public State moveRobotForward() {
        state.speed += 1;
        return getState();
    }

    @Override
    public State moveRobotBackward() {
        state.speed += -1;
        return getState();
    }

    @Override
    public State moveRobotLeft() {
        return getState();
    }

    @Override
    public State moveRobotRight() {
        return getState();
    }

    @Override
    public State playPause() {
        if (state.speed == 0) {
            state.speed += 1;
        } else {
            state.speed = 0;
        }
        return getState();
    }

    @Override
    public State moveArmUp() {
        state.armAngle += 1;
        return getState();
    }

    @Override
    public State moveArmDown() {
        state.armAngle -= 1;
        return getState();
    }

    @Override
    public State grabReleaseArm() {
        if (state.grabReleaseState.equals(ArmState.GRAB)) {
            state.grabReleaseState = ArmState.RELEASE;
            state.grabReleaseLabel = "Rel";
        } else if (state.grabReleaseState == ArmState.RELEASE) {
            state.grabReleaseState = ArmState.GRAB;
            state.grabReleaseLabel = "Grab";
        }
        return getState();
    }

    @Override
    public State fetchCamera() {
        state.cameraActive = true;
        return getState();
    }

    @Override
    public State fetchTemperature() {
        state.temperatureActive = true;
        int temperatureCelsius = 100;
        int temperatureFarenheit = 220;
        state.temperatureCelsius = Integer.toString(temperatureCelsius);
        state.temperatureFarenheit = Integer.toString(temperatureFarenheit);
        return getState();
    }
}

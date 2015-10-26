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
        if (state.turnCount > -9) {
            state.turnCount -= 1;
        }
        return getState();
    }

    @Override
    public State moveRobotRight() {
        if (state.turnCount < 9) {
            state.turnCount += 1;
        }
        return getState();
    }

    @Override
    public State playPause() {
        if (state.speed == 0) {
            state.speed += 1;
        } else {
            state.speed = 0;
        }
        state.turnCount = 0;
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
    public State toggleCamera() {
        state.cameraActive = state.cameraActive == false;
        return getState();
    }

    @Override
    public State fetchTemperature() {
        state.temperatureActive = true;
        int temperature = 15 + (int)(Math.random() * 5); // Range of 30
        int temperatureCelsius = temperature;
        int temperatureFarenheit = (temperature * 9 / 5) + 32 ;
        state.temperatureCelsius = Integer.toString(temperatureCelsius);
        state.temperatureFarenheit = Integer.toString(temperatureFarenheit);
        return getState();
    }
}

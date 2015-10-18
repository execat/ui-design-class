package robotinterface;

/**
 * Created by atm on 10/17/15.
 */
public class State {

    public int speed;
    public String forwardLabel;
    public String backwardLabel;
    public boolean forwardActive;
    public boolean backwardActive;
    final public String leftLabel;
    final public String rightLabel;
    public String playPauseLabel;

    final public String upLabel;
    final public String downLabel;
    public boolean upActive;
    public boolean downActive;
    public int armAngle;
    public ArmState grabReleaseState;

    public boolean cameraActive;
    final public String cameraLabel;

    public boolean temperatureActive;
    final public String temperatureLabel;
    public String temperatureCelsius;
    public String temperatureFarenheit;
    public String debuggerText;

    void updateState() {
        // Directions

        // Set number of arrows
        if (speed == 0) {
            forwardLabel = "↑";
            backwardLabel = "↓";
        } else if (speed > 0) {
            if (speed == 1) {
                forwardLabel = "↑↑";
            } else if (speed == 2) {
                forwardLabel = "↑↑↑";
            } else if (speed == 3) {
                forwardLabel = "↑↑↑";
            }
        } else if (speed < 0) {
            if (speed == -1) {
                backwardLabel = "↓↓";
            } else if (speed == -2) {
                backwardLabel = "↓↓↓";
            } else if (speed == -3) {
                backwardLabel = "↓↓↓";
            }
        }

        // Set playPauseLabel
        if (speed == 0) {
            playPauseLabel = "▶";
        } else {
            playPauseLabel = "❚❚";
        }

        // Speed control
        if (speed > 3) { speed = 3; }
        if (speed < -3) { speed = -3; }

        // Set active/non-active buttons
        if (speed == 3) {
            forwardActive = false;
            backwardActive = true;
        }  else if (speed == -3) {
            forwardActive = true;
            backwardActive = false;
        } else {
            forwardActive = true;
            backwardActive = true;
        }

        // Arm

        // Set active buttons
        if (armAngle > 90) {
            armAngle = 90;
        } else if (armAngle < 0) {
            armAngle = 0;
        }

        if (armAngle == 90) {
            upActive = false;
            downActive = true;
        } else if (armAngle == 0) {
            upActive = true;
            downActive = false;
        } else {
            upActive = true;
            downActive = true;
        }

        logger();
    }

    void logger() {
        System.out.println("Speed: " + speed + "\n" +
                        "Forward label: " + forwardLabel + " :: Forward active: " + forwardActive + "\n" +
                        "Backward label: " + backwardLabel + " :: Backward active: " + backwardActive + "\n" +
                        "Play Pause label: " + playPauseLabel);
        System.out.println("up active: " + upActive + ", down active: " + downActive + ", arm angle: " + armAngle + ", grab/release state: " + grabReleaseState);
        System.out.println("Camera active: " + cameraActive);
        System.out.println("Temperature active: " + temperatureActive);
    }

    State() {
        speed = 0;
        forwardLabel = "↑";
        backwardLabel = "↓";
        leftLabel = "←";
        rightLabel = "→";
        playPauseLabel = "▶";

        upLabel = "⇞";
        downLabel = "⇟";
        upActive = true;
        downActive = false;
        armAngle = 0;
        grabReleaseState = ArmState.GRAB;

        cameraActive = false;
        cameraLabel = "Fetch Camera View";

        temperatureActive = false;
        temperatureLabel = "Fetch Temperature";
        temperatureCelsius = "NA";
        temperatureFarenheit = "NA";
        debuggerText = "Use the following shortcuts:\n " +
                " * Arrow keys for direction\n" +
                " * a/A to lift arm upLabel and downLabel\n" +
                " * g for grab/release\n" +
                " * c for cameraLabel\n" +
                " * t for temperatureLabel\n\n\n" +
                "Waiting for command...\n";
    }
}

package robotinterface;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RobotInterface extends javax.swing.JFrame {
    RobotController controller = new RobotController();

	/**
	 * Creates new form RobotInterface
	 */
	public RobotInterface() {
        initComponents();
        initShortcuts();
    }

    private void forceRefreshUI(State state) {
        refreshUI(state);

        left.setText(state.leftLabel);
        right.setText(state.rightLabel);
        up.setText(state.upLabel);
        down.setText(state.downLabel);
        fetchCamera.setText(state.cameraLabel);
        fetchTemperature.setText(state.temperatureLabel);
    }

    private void refreshUI(State state) {
        int speed = state.speed;
        int armAngle = state.armAngle;

        forward.setText(state.forwardLabel);
        forward.setEnabled(state.forwardActive);
        backward.setText(state.backwardLabel);
        backward.setEnabled(state.backwardActive);
        playPause.setText(state.playPauseLabel);

        speedBar.setMaximum(3);
        speedBar.setMinimum(0);
        speedBar.setValue(Math.abs(speed));

        up.setEnabled(state.upActive);
        down.setEnabled(state.downActive);
        grabRelease.setText(state.grabReleaseLabel);
        refreshArmCanvas(state.armAngle, state.grabReleaseState);
        refreshCarCanvas(state);

        temperatureLabelCelsius.setText(state.temperatureCelsius);
        temperatureLabelFarentheit.setText(state.temperatureFarenheit);

        fetchCamera.setText(state.cameraLabel);
        refreshUICamera(state.cameraActive);
        statusLabel.setText(state.appStatus);
    }

    private void refreshArmCanvas(int angle, ArmState armState) {
        Graphics2D g = (Graphics2D)armView.getGraphics();
        g.clearRect(0, 0, 10000, 10000);

        int thickness = 3;
        g.setStroke(new BasicStroke(thickness));

        int x1 = 85, y1 = 80;
        int x2 = 149, y2 = 80;
        int length = x2 - x1;

        // 4th quadrant math
        int x3 = x1 + (int)(Math.cos(Math.toRadians(angle)) * length);
        int y3 = y1 - (int)(Math.sin(Math.toRadians(angle)) * length);

        // Tilting algorithm for the angle
        if(armState == ArmState.GRAB) {
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x1, y1, x3, y3);
            g.fillOval(x3 - 3, y3 - 3, 6, 6);
        } else {
            g.setColor(Color.RED);
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x1, y1, x3, y3);
            g.fillOval(x3 - 3, y3 - 3, 6, 6);
        }

        // Reset color
        g.setColor(Color.BLACK);
    }

    private void refreshCarCanvas(State state) {
        Graphics2D g = (Graphics2D)armView.getGraphics();
        Color boxColor = Color.gray;
        if (state.speed == 0) {
            boxColor = Color.gray;
        } else if (state.speed == -1) {
            boxColor = new Color(255, 174, 169);
        } else if (state.speed == -2) {
            boxColor = new Color(255, 97, 93);
        } else if (state.speed == -3) {
            boxColor = new Color(255, 0, 0);
        } else if (state.speed == 1) {
            boxColor = new Color(138, 255, 146);
        } else if (state.speed == 2) {
            boxColor = new Color(51, 255, 87);
        } else if (state.speed == 3) {
            boxColor = new Color(0, 255, 41);
        }

        int thickness = 5;
        g.setStroke(new BasicStroke(thickness));

        Rectangle2D rect = new Rectangle2D.Double(29, 26, 20, 32);
        g.setPaint(boxColor);
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(10 * state.turnCount), 39, 42);
        Path2D rotatedRect = (Path2D)(at.createTransformedShape(rect));

        g.draw(rotatedRect);
    }

    private void refreshUICamera(boolean show) {
        if (show) {
            Image img = null;
            try {
                img = ImageIO.read(new File("robotinterface/cat.jpg"));
                cameraCanvas.getGraphics().drawImage(img, 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cameraCanvas.getGraphics().clearRect(0, 0, 10000, 10000);
        }
    }

    // Used by initShortcuts()
    private void simulateChange(JButton element) {
        element.doClick();
        element.requestFocus();
    }

    private void initShortcuts() {
        MouseEvent fakeEvent = new MouseEvent(new JFrame(), 1, 1, 1, 1, 1, 1, 1, 1, true, 1);

        // Foward
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move forwardLabel");
        getRootPane().getActionMap().put("Move forwardLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(forward);
                forwardMouseClicked(fakeEvent);
            }
        });

        // Backward
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move backwardLabel");
        getRootPane().getActionMap().put("Move backwardLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(backward);
                backwardMouseClicked(fakeEvent);
            }
        });

        // Left
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move leftLabel");
        getRootPane().getActionMap().put("Move leftLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(left);
                leftMouseClicked(fakeEvent);
            }
        });

        // Right
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move rightLabel");
        getRootPane().getActionMap().put("Move rightLabel", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                simulateChange(right);
                rightMouseClicked(fakeEvent);
            }
        });

        // PlayPause
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Play Pause");
        getRootPane().getActionMap().put("Play Pause", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(playPause);
                playPauseMouseClicked(fakeEvent);
            }
        });

        // Arm upLabel
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "Arm upLabel");
        getRootPane().getActionMap().put("Arm upLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(up);
                upMouseClicked(fakeEvent);
            }
        });

        // Arm downLabel
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK), "Arm downLabel");
        getRootPane().getActionMap().put("Arm downLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(down);
                downMouseClicked(fakeEvent);
            }
        });

        // Arm grab/release
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "Grab/release");
        getRootPane().getActionMap().put("Grab/release", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(grabRelease);
                grabReleaseMouseClicked(fakeEvent);
            }
        });

        // Capture cameraLabel
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "Capture cameraLabel");
        getRootPane().getActionMap().put("Capture cameraLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(fetchCamera);
                fetchCameraMouseClicked(fakeEvent);
            }
        });

        // Arm grab/release
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0), "Fetch temperatureLabel");
        getRootPane().getActionMap().put("Fetch temperatureLabel", new AbstractAction()
        {
            public void actionPerformed(ActionEvent e) {
                simulateChange(fetchTemperature);
                fetchTemperatureMouseClicked(fakeEvent);
            }
        });

    }

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jButton3 = new javax.swing.JButton();
                movementPanel = new javax.swing.JPanel();
                forward = new javax.swing.JButton();
                backward = new javax.swing.JButton();
                left = new javax.swing.JButton();
                right = new javax.swing.JButton();
                playPause = new javax.swing.JButton();
                speedBar = new javax.swing.JProgressBar();
                armControlPanel = new javax.swing.JPanel();
                up = new javax.swing.JButton();
                down = new javax.swing.JButton();
                grabRelease = new javax.swing.JButton();
                armView = new javax.swing.JPanel();
                cameraPanel = new javax.swing.JPanel();
                fetchCamera = new javax.swing.JButton();
                cameraCanvas = new java.awt.Canvas();
                temperaturePanel = new javax.swing.JPanel();
                fetchTemperature = new javax.swing.JButton();
                temperatureLabelCelsius = new javax.swing.JLabel();
                temperatureLabelFarentheit = new javax.swing.JLabel();
                tempLabelCelsius = new javax.swing.JLabel();
                tempLabelFarenheit = new javax.swing.JLabel();
                statusLabel = new javax.swing.JLabel();

                jButton3.setText("jButton3");

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                movementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Movement"));

                forward.setText("↑");
                forward.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                forwardMouseClicked(evt);
                        }
                });

                backward.setText("↓");
                backward.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                backwardMouseClicked(evt);
                        }
                });

                left.setText("←");
                left.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                leftMouseClicked(evt);
                        }
                });

                right.setText("→");
                right.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                rightMouseClicked(evt);
                        }
                });

                playPause.setText("▶");
                playPause.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                playPauseMouseClicked(evt);
                        }
                });

                speedBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.GroupLayout movementPanelLayout = new javax.swing.GroupLayout(movementPanel);
                movementPanel.setLayout(movementPanelLayout);
                movementPanelLayout.setHorizontalGroup(
                        movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(movementPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(speedBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(movementPanelLayout.createSequentialGroup()
                                                .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(backward, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(movementPanelLayout.createSequentialGroup()
                                                                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(playPause, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(forward, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                movementPanelLayout.setVerticalGroup(
                        movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(movementPanelLayout.createSequentialGroup()
                                .addComponent(forward, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(movementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(playPause, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backward, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(speedBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                armControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Arm Control"));

                up.setText("⇞");
                up.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                upMouseClicked(evt);
                        }
                });

                down.setText("⇟");
                down.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                downMouseClicked(evt);
                        }
                });

                grabRelease.setText("Grab");
                grabRelease.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                grabReleaseMouseClicked(evt);
                        }
                });

                armView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                javax.swing.GroupLayout armViewLayout = new javax.swing.GroupLayout(armView);
                armView.setLayout(armViewLayout);
                armViewLayout.setHorizontalGroup(
                        armViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                armViewLayout.setVerticalGroup(
                        armViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 94, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout armControlPanelLayout = new javax.swing.GroupLayout(armControlPanel);
                armControlPanel.setLayout(armControlPanelLayout);
                armControlPanelLayout.setHorizontalGroup(
                        armControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(armControlPanelLayout.createSequentialGroup()
                                .addGroup(armControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(up))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(grabRelease)
                                .addGap(0, 5, Short.MAX_VALUE))
                        .addGroup(armControlPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(armView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                armControlPanelLayout.setVerticalGroup(
                        armControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(armControlPanelLayout.createSequentialGroup()
                                .addGroup(armControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(grabRelease, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(armControlPanelLayout.createSequentialGroup()
                                                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(armView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );

                cameraPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Camera"));

                fetchCamera.setText("Fetch Camera View");
                fetchCamera.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                fetchCameraMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout cameraPanelLayout = new javax.swing.GroupLayout(cameraPanel);
                cameraPanel.setLayout(cameraPanelLayout);
                cameraPanelLayout.setHorizontalGroup(
                        cameraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fetchCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(cameraPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cameraCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                cameraPanelLayout.setVerticalGroup(
                        cameraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cameraPanelLayout.createSequentialGroup()
                                .addComponent(fetchCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cameraCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );

                temperaturePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Temperature"));

                fetchTemperature.setText("Fetch Temperature");
                fetchTemperature.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                fetchTemperatureMouseClicked(evt);
                        }
                });

                temperatureLabelCelsius.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                temperatureLabelCelsius.setText("NA");
                temperatureLabelCelsius.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                temperatureLabelFarentheit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                temperatureLabelFarentheit.setText("NA");
                temperatureLabelFarentheit.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                tempLabelCelsius.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
                tempLabelCelsius.setText("°C");

                tempLabelFarenheit.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
                tempLabelFarenheit.setText("°F");

                javax.swing.GroupLayout temperaturePanelLayout = new javax.swing.GroupLayout(temperaturePanel);
                temperaturePanel.setLayout(temperaturePanelLayout);
                temperaturePanelLayout.setHorizontalGroup(
                        temperaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(temperaturePanelLayout.createSequentialGroup()
                                .addGroup(temperaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, temperaturePanelLayout.createSequentialGroup()
                                                .addComponent(temperatureLabelCelsius, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tempLabelCelsius))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, temperaturePanelLayout.createSequentialGroup()
                                                .addComponent(temperatureLabelFarentheit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tempLabelFarenheit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(114, 114, 114))
                        .addGroup(temperaturePanelLayout.createSequentialGroup()
                                .addComponent(fetchTemperature)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                temperaturePanelLayout.setVerticalGroup(
                        temperaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(temperaturePanelLayout.createSequentialGroup()
                                .addComponent(fetchTemperature, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(temperaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tempLabelCelsius, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                        .addComponent(temperatureLabelCelsius, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(temperaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(temperatureLabelFarentheit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tempLabelFarenheit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(movementPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cameraPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(temperaturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(armControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(movementPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(armControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(temperaturePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cameraPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void forwardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forwardMouseClicked
            State state = controller.moveRobotForward();
            refreshUI(state);
            System.out.println("Moving foward");
        }//GEN-LAST:event_forwardMouseClicked

        private void backwardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backwardMouseClicked
            State state = controller.moveRobotBackward();
            refreshUI(state);
            System.out.println("Moving backwardLabel");
        }//GEN-LAST:event_backwardMouseClicked

        private void leftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftMouseClicked
            State state = controller.moveRobotLeft();
            refreshUI(state);
            System.out.println("Moving leftLabel");
        }//GEN-LAST:event_leftMouseClicked

        private void rightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightMouseClicked
            State state = controller.moveRobotRight();
            refreshUI(state);
            System.out.println("Moving rightLabel");
        }//GEN-LAST:event_rightMouseClicked

        private void playPauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playPauseMouseClicked
            State state = controller.playPause();
            refreshUI(state);
            System.out.println("Play pause");
        }//GEN-LAST:event_playPauseMouseClicked

        private void upMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upMouseClicked
            State state = controller.moveArmUp();
            refreshUI(state);
            System.out.println("Lifting arm upLabel");
        }//GEN-LAST:event_upMouseClicked

        private void downMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downMouseClicked
            State state = controller.moveArmDown();
            refreshUI(state);
            System.out.println("Lifting arm downLabel");
        }//GEN-LAST:event_downMouseClicked

        private void grabReleaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grabReleaseMouseClicked
            State state = controller.grabReleaseArm();
            refreshUI(state);
            System.out.println("Executing grab/release");
        }//GEN-LAST:event_grabReleaseMouseClicked

        private void fetchCameraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fetchCameraMouseClicked
            State state = controller.toggleCamera();
            refreshUI(state);
            System.out.println("Fetching cameraLabel stream");
        }//GEN-LAST:event_fetchCameraMouseClicked

        private void fetchTemperatureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fetchTemperatureMouseClicked
            State state = controller.getTemperature();
            refreshUI(state);
            System.out.println("Fetching temperatureLabel sensor reading");
        }//GEN-LAST:event_fetchTemperatureMouseClicked

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Basic look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Basic (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Basic".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RobotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RobotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RobotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RobotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RobotInterface().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel armControlPanel;
        private javax.swing.JPanel armView;
        private javax.swing.JButton backward;
        private java.awt.Canvas cameraCanvas;
        private javax.swing.JPanel cameraPanel;
        private javax.swing.JButton down;
        private javax.swing.JButton fetchCamera;
        private javax.swing.JButton fetchTemperature;
        private javax.swing.JButton forward;
        private javax.swing.JButton grabRelease;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton left;
        private javax.swing.JPanel movementPanel;
        private javax.swing.JButton playPause;
        private javax.swing.JButton right;
        private javax.swing.JProgressBar speedBar;
        private javax.swing.JLabel statusLabel;
        private javax.swing.JLabel tempLabelCelsius;
        private javax.swing.JLabel tempLabelFarenheit;
        private javax.swing.JLabel temperatureLabelCelsius;
        private javax.swing.JLabel temperatureLabelFarentheit;
        private javax.swing.JPanel temperaturePanel;
        private javax.swing.JButton up;
        // End of variables declaration//GEN-END:variables
}

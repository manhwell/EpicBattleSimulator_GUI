import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimatedBattlePane extends JFrame{

    private JPanel contentPane;
    private JPanel displayPanel;
    private JMenuBar menuBar;
    private JLabel lblReportLabel;
    private JButton pauseButton;
    private static int WINDOW_X = 400;
    private static int WINDOW_Y = 100;
    private static int WINDOW_WIDTH = 750;
    private static int WINDOW_HEIGHT = 600;
    private AnimationThread animation;

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                lblReportLabel.setText("Key pressed: " + e.getKeyChar());
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                lblReportLabel.setText("Key released: " + e.getKeyChar());
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                lblReportLabel.setText("Key typed: " + e.getKeyChar());
            }
            return false;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // anonymous launch
            public void run() {
                try {
                    JOptionPane.showMessageDialog(null, "Simulation will initialize with default values.");
                    AnimatedBattlePane frame = new AnimatedBattlePane();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AnimatedBattlePane() {
        setTitle("Epic Battle Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(getMyMenuBar());
        contentPane.add(getDisplayPanel());
        contentPane.add(getLblReportLabel());
        contentPane.add(pauseButton());

        /////////// special keyboard handling //////////////////
        KeyboardFocusManager manager =
                KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        /////////// end of special added code //////////////////

        JMenuBar jmb = getMyMenuBar();
        this.setJMenuBar(jmb);
        this.setVisible(true);
    }

    private JPanel getDisplayPanel() {
        if (displayPanel == null) {

            ///////////////
            displayPanel = new AnimationArea(); // subclass of JPanel();
            ///////////////

            displayPanel.addMouseListener(new DisplayPanelMouseListener());
            displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            displayPanel.setBackground(Color.WHITE);
            displayPanel.setBounds(10, 11, WINDOW_WIDTH - 35, WINDOW_HEIGHT - 125);
            displayPanel.setLayout(null);

            ///////////////
            animation = new AnimationThread( (AnimationArea) displayPanel );
            animation.start();
            ///////////////

        }
        return displayPanel;
    }
    private JLabel getLblReportLabel() {
        if (lblReportLabel == null) {
            lblReportLabel = new JLabel("This is where feedback goes...");
            lblReportLabel.setBounds(10, WINDOW_HEIGHT  - 100, (int) (WINDOW_WIDTH * .75), 30);
        }
        return lblReportLabel;
    }

    private JMenuBar getMyMenuBar(){
        if(menuBar == null){
            menuBar = new JMenuBar();

            JMenu jmFile = new JMenu("File");

            JMenuItem jmRestart = new JMenuItem("Restart");
            jmRestart.addActionListener(this::actionPerformed);
            JMenuItem jmPause = new JMenuItem("Pause");
            jmPause.addActionListener(this::actionPerformed);
            JMenuItem jmExit = new JMenuItem("Exit");
            jmExit.addActionListener(this::actionPerformed);

            jmFile.add(jmPause);
            jmFile.add(jmRestart);
            jmFile.add(jmExit);

            JMenu jmSettings = new JMenu("Settings");

            JMenuItem jmBattleSettings = new JMenuItem("Battlefield Settings");
            jmBattleSettings.addActionListener(this::actionPerformed);

            jmSettings.add(jmBattleSettings);

            menuBar.add(jmFile);
            menuBar.add(jmSettings);
        }
        return menuBar;
    }

    public void actionPerformed(ActionEvent ae){
        String commandStr = ae.getActionCommand();
        switch (commandStr) {
            case "Pause":
                animation.toggleAnimation();
                break;
            case "Exit":
                System.exit(0);
            case "Restart":
                animation.getAnimationArea().restart();
                pauseButton.setLabel("Begin Simulation");
                animation.setStopper(true);
                break;
            case "Battlefield Settings":
                this.displaySettingsMenu();
                break;
        }
    }

    private JButton pauseButton() {
        if (pauseButton == null) {
            pauseButton = new JButton("Begin Simulation");
            pauseButton.addMouseListener(new BtnPauseButtonListener());
            pauseButton.setBounds((int) (WINDOW_WIDTH * .75), WINDOW_HEIGHT- 100, (int) (WINDOW_WIDTH * .25) - 23, 30);
        }
        return pauseButton;
    }

    private class DisplayPanelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            lblReportLabel.setText("Mouse clicked at (" + mouseEvent.getX() +
                    ", " + mouseEvent.getY() + ")");
            animation.toggleAnimation();
        }
    }

    private class BtnPauseButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(animation.isAnimationRunning()) {
                animation.toggleAnimation();
                pauseButton().setLabel("Pause");
            }
            else {
                animation.toggleAnimation();
                pauseButton().setLabel("Unpause");
            }
        }
    }

    private void displaySettingsMenu(){
        this.animation.setStopper(true);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame settingsFrame = new JFrame("hello");
                SettingsMenu settingsMenu = new SettingsMenu(getAnimation());
                settingsFrame.setContentPane(settingsMenu.$$$getRootComponent$$$());
                settingsFrame.pack();
                settingsFrame.setVisible(true);
            }
        });
    }

    public AnimationThread getAnimation(){
        return this.animation;
    }
}

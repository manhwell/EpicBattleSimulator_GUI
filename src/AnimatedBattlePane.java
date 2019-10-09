import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.MenuBar;
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

    AnimationThread animation;

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
        this.setSize(WINDOW_X, WINDOW_Y);
        this.setLayout(new BorderLayout());
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        JMenuBar jmb = getMyMenuBar();
        this.add(jmb, BorderLayout.NORTH);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.red));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.add(getDisplayPanel(), BorderLayout.CENTER);
        contentPane.add(getLblReportLabel(), BorderLayout.SOUTH);
        contentPane.add(pauseButton(), BorderLayout.SOUTH);

        /////////// special keyboard handling //////////////////
        KeyboardFocusManager manager =
                KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        /////////// end of special added code //////////////////

    }

    private JPanel getDisplayPanel() {
        if (displayPanel == null) {

            ///////////////
            displayPanel = new AnimationArea(); // subclass of JPanel();
            ///////////////

            displayPanel.addMouseListener(new DisplayPanelMouseListener());
            displayPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            displayPanel.setBackground(Color.WHITE);
            displayPanel.setBounds(10, 30, WINDOW_WIDTH - 35, WINDOW_HEIGHT - 135);
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
            lblReportLabel.setBounds(10, WINDOW_HEIGHT  - 100, (int) (WINDOW_WIDTH * .75), 50);
        }
        return lblReportLabel;
    }

    private JMenuBar getMyMenuBar(){
        //TODO get this working and displaying.
        if(menuBar == null){
            menuBar = new JMenuBar();

            JMenu jmSettings = new JMenu("Settings");

            JMenuItem jmRestart = new JMenuItem("Restart");
            JMenuItem jmPause = new JMenuItem("Pause");
            JMenuItem jmExit = new JMenuItem("Exit");

            jmSettings.add(jmPause);
            jmSettings.add(jmRestart);
            jmSettings.add(jmExit);

            menuBar.add(jmSettings);
        }
        return menuBar;
    }

    private JButton pauseButton() {
        if (pauseButton == null) {
            pauseButton = new JButton("Pause");
            pauseButton.addMouseListener(new BtnPauseButtonListener());
            pauseButton.setBounds((int) (WINDOW_WIDTH * .75), WINDOW_HEIGHT- 100, (int) (WINDOW_WIDTH * .25) - 20, 50);
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
}

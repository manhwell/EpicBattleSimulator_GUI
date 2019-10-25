import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * AnimatedBattlePane() provides a JFrame to run a battle simulation on.
 * @author C2C Manuel Riolo
 */
public class AnimatedBattlePane extends JFrame{

    // Initializing variables.
    private JPanel contentPane;
    private JPanel displayPanel;
    private JMenuBar menuBar;
    private JButton pauseButton;
    private static int WINDOW_X = 400;
    private static int WINDOW_Y = 100;
    private static int WINDOW_WIDTH = 750;
    private static int WINDOW_HEIGHT = 600;
    private AnimationThread animation;

    /**
     * Created the battle pane for the simulation.
     * @param args is a command line argument to pass to main.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // anonymous launch
            public void run() {
                try {
                    JOptionPane.showMessageDialog(null, "Simulation will initialize with default values."); // Initial message.
                    AnimatedBattlePane frame = new AnimatedBattlePane(); // Make a new battle pane
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Default deceleration for an AnimatedBattlePane;
     */
    public AnimatedBattlePane() {
        setTitle("Epic Battle Simulator"); // Title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit option
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT); // Window Size
        // Initialize the content pane.
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        // Add the various aspects to the content pane.
        contentPane.add(getMyMenuBar()); // Menu bar
        contentPane.add(getDisplayPanel()); // Animation pane
        contentPane.add(pauseButton()); // Pause button

       // Set up the menuBar
        JMenuBar jmb = getMyMenuBar(); // Set the menuBar contents to how its outlined in getMyMenuBar().
        this.setJMenuBar(jmb); // Add the menu bar to the frame.
        this.setVisible(true); // Make the frame visible
    }

    /**
     * Gets the panel that displays the battle animation.
     * @return the JPanel containing the battle.
     */
    private JPanel getDisplayPanel() {
        if (displayPanel == null) {

            ///////////////
            displayPanel = new AnimationArea(); // subclass of JPanel();
            ///////////////

            // Set initial values for the display panel.
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

    /**
     * Gets the menu bar displayed on the JFrame.
     * @return the JMenuBar containing options to edit the battle.
     */
    private JMenuBar getMyMenuBar(){
        if(menuBar == null){
            // Create a new JMenuBar.
            menuBar = new JMenuBar();

            // Create the File option.
            JMenu jmFile = new JMenu("File");

            // Create the options within File and add listeners to them.
            JMenuItem jmRestart = new JMenuItem("Restart");
            jmRestart.addActionListener(this::actionPerformed);
            JMenuItem jmPause = new JMenuItem("Pause");
            jmPause.addActionListener(this::actionPerformed);
            JMenuItem jmExit = new JMenuItem("Exit");
            jmExit.addActionListener(this::actionPerformed);

            // Add the options to the file bar.
            jmFile.add(jmPause);
            jmFile.add(jmRestart);
            jmFile.add(jmExit);

            // Create a new Settings option.
            JMenu jmSettings = new JMenu("Settings");

            // Create the option within Settings and add a listener to it.
            JMenuItem jmBattleSettings = new JMenuItem("Battlefield Settings");
            jmBattleSettings.addActionListener(this::actionPerformed);

            // Add the option to the settings bar.
            jmSettings.add(jmBattleSettings);

            // Add both settings and file to the bar.
            menuBar.add(jmFile);
            menuBar.add(jmSettings);
        }
        return menuBar;
    }

    /**
     * Is the function where actions performed on the JMenuBar are handled.
     * @param ae is the action performed on the JMenuBar.
     */
    public void actionPerformed(ActionEvent ae){
        String commandStr = ae.getActionCommand();
        switch (commandStr) { // Based off what was chosen, perform an action.
            case "Pause":
                animation.toggleAnimation(); // Pause the battle.
                break;
            case "Exit":
                System.exit(0); // Exit
            case "Restart":
                animation.getAnimationArea().restart(); // Reset the simulation.
                pauseButton.setLabel("Begin Simulation"); // Set the pause button to its default value.
                animation.setStopper(true); // Pause the battle.
                break;
            case "Battlefield Settings":
                this.displaySettingsMenu(); // Open up the new JFrame that has the battlefield settings on it.
                break;
        }
    }

    /**
     * Is the pause button on the main screen for the simulation.
     * @return the pause button that can pause and unpause the animation.
     */
    private JButton pauseButton() {
        if (pauseButton == null) {
            pauseButton = new JButton("Begin Simulation"); // Message displayed to initially begin the battle.
            pauseButton.addMouseListener(new BtnPauseButtonListener()); // Add a listener.
            pauseButton.setBounds((int) (WINDOW_WIDTH * .75), WINDOW_HEIGHT- 100, (int) (WINDOW_WIDTH * .25) - 23, 30); // Create the button.
        }
        return pauseButton;
    }

    /**
     * Is the action listener for the pause button and pauses and unpauses the battle when pressed.
     */
    private class BtnPauseButtonListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(animation.isAnimationRunning()) { // If the animation is running, pause it.
                animation.toggleAnimation();
                pauseButton().setLabel("Pause");
            }
            else { // If the animation is paused, unpause it.
                animation.toggleAnimation();
                pauseButton().setLabel("Unpause");
            }
        }
    }

    /**
     * Makes the JMenuBar visible to the user.
     */
    private void displaySettingsMenu(){
        this.animation.setStopper(true); // When you open settings pause the simulation.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() { // Initialize a new JFrame.
                JFrame settingsFrame = new JFrame("Battlefield Settings");
                SettingsMenu settingsMenu = new SettingsMenu(getAnimation()); // Load a new settingsMenu format.
                settingsFrame.setContentPane(settingsMenu.$$$getRootComponent$$$()); // Sets what it looks like based on the custom settingsMenu format.
                settingsFrame.pack();
                settingsFrame.setVisible(true);
            }
        });
    }

    /**
     * Gets the animation running on the JFrame.
     * @return the animation running on the JFrame.
     */
    public AnimationThread getAnimation(){
        return this.animation; // Get the animation.
    }
}

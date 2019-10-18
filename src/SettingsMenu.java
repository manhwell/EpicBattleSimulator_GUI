import javax.swing.*;

public class SettingsMenu extends JFrame{
    private JTextField MenuTitle;
    private JButton AddArmy;
    private JPanel BattlefieldSettings;

    public SettingsMenu() {
        JFrame frame = new JFrame("SettingsMenu");
        frame.setContentPane(new SettingsMenu().BattlefieldSettings);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }
}

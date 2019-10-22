import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * SettingsMenu() provides a JFrame with an array of options to change the battlefield.
 *
 * @author C2C Manuel Riolo
 */
public class SettingsMenu extends JFrame {
    private JPanel BattlefieldSettings;
    private JComboBox ColorSelectorComboBox;
    private JLabel ArmyAttributesLabel;
    private JSlider ArmySizeSlider;
    private JLabel ArmySizeLabel;
    private JLabel ArmyColorLabel;
    private JLabel SettingsMenuLabel;
    private JButton AddArmyButton;
    private JButton deleteArmyButton;
    private JLabel armySelectorLabel;
    private JComboBox armySelectorComboBox;
    private JSlider armyPowerSlider;
    private JButton updateArmyButton;
    private JLabel speedLabel;
    private JSlider speedSlider;

    private AnimationThread animation;

    /**
     * Creates a new JFrame that contains options for the battle simulation.
     *
     * @param animation is the animationThread that contains the animation.
     */
    public SettingsMenu(AnimationThread animation) {
        this.animation = animation;

        deleteArmyButton.addActionListener(this::deleteButtonListener);

        AddArmyButton.addActionListener(this::addArmyButtonListener);

        updateArmyButton.addActionListener(this::updateArmyListener);

        updateArmySelector();

        ColorSelectorComboBox.addItem("Red");
        ColorSelectorComboBox.addItem("Blue");
        ColorSelectorComboBox.addItem("Yellow");
        ColorSelectorComboBox.addItem("Green");
    }

    /**
     * Handles what to do when the delete button is pressed.
     *
     * @param ae is the action performed on the delete button.
     */
    public void deleteButtonListener(ActionEvent ae) {
        int selectedArmy = armySelectorComboBox.getSelectedIndex();
        animation.getAnimationArea().getBattlefield().getArmiesOnField().remove(selectedArmy);
        animation.getAnimationArea().setNumArmies();
        updateArmySelector();
    }

    /**
     * Handles what to do when the add army button is pressed.
     *
     * @param ae is the action performed on the add army button.
     */
    public void addArmyButtonListener(ActionEvent ae) {
        if (this.animation.getAnimationArea().getBattlefield().getArmiesOnField().size() <= 3) {
            String selectedColor = (String) ColorSelectorComboBox.getSelectedItem();
            int team;
            int armySize;
            int armyStrength;
            int speed;
            assert selectedColor != null;
            switch (selectedColor) {
                case "Blue":
                    team = 0;
                    break;
                case "Red":
                    team = 1;
                    break;
                case "Green":
                    team = 2;
                    break;
                case "Yellow":
                    team = 3;
                    break;
                default:
                    team = 0;
                    break;
            }
            armySize = ArmySizeSlider.getValue();
            armyStrength = armyPowerSlider.getValue();
            speed = speedSlider.getValue();
            this.animation.getAnimationArea().getBattlefield().getArmiesOnField().add(new Army(team, armySize, armyStrength, speed, animation.getAnimationArea().getWindowWidth(), animation.getAnimationArea().getWindowHeight()));
            updateArmySelector();
        } else {
            JOptionPane.showMessageDialog(null, "No more than 4 armies at a time.", "Too many armies", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles what to do when the update army button is pressed.
     * @param ae is the action performed on the update army button.
     */
    public void updateArmyListener(ActionEvent ae) {
        int selectedArmy = armySelectorComboBox.getSelectedIndex();
        animation.getAnimationArea().getBattlefield().getArmiesOnField().get(selectedArmy).setArmySpeed(speedSlider.getValue());
        animation.getAnimationArea().getBattlefield().getArmiesOnField().get(selectedArmy).setArmyStrength(armyPowerSlider.getValue());
        animation.getAnimationArea().getBattlefield().getArmiesOnField().get(selectedArmy).setArmyColor((String) ColorSelectorComboBox.getSelectedItem());
        updateArmySelector();
    }

    /**
     * Updates the army selector bar so that you can select any army currently on the field.
     */
    public void updateArmySelector() {
        armySelectorComboBox.removeAllItems();
        for (int i = 0; i < this.animation.getAnimationArea().getBattlefield().getArmiesOnField().size(); i++) {
            String color = animation.getAnimationArea().getBattlefield().getArmiesOnField().get(i).getArmyColor();
            this.armySelectorComboBox.addItem(color + " Army");
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        BattlefieldSettings = new JPanel();
        BattlefieldSettings.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 4, new Insets(0, 0, 0, 0), -1, -1));
        ColorSelectorComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        ColorSelectorComboBox.setModel(defaultComboBoxModel1);
        BattlefieldSettings.add(ColorSelectorComboBox, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 25), new Dimension(100, 25), new Dimension(100, 25), 0, false));
        ArmyAttributesLabel = new JLabel();
        ArmyAttributesLabel.setText("Army Attributes");
        BattlefieldSettings.add(ArmyAttributesLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ArmySizeSlider = new JSlider();
        ArmySizeSlider.setMajorTickSpacing(5);
        ArmySizeSlider.setMaximum(30);
        ArmySizeSlider.setPaintLabels(true);
        ArmySizeSlider.setPaintTicks(true);
        ArmySizeSlider.setValue(10);
        BattlefieldSettings.add(ArmySizeSlider, new com.intellij.uiDesigner.core.GridConstraints(9, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ArmySizeLabel = new JLabel();
        ArmySizeLabel.setText("Army Size");
        BattlefieldSettings.add(ArmySizeLabel, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ArmyColorLabel = new JLabel();
        ArmyColorLabel.setText("Color");
        BattlefieldSettings.add(ArmyColorLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SettingsMenuLabel = new JLabel();
        SettingsMenuLabel.setText("Battlefield Settings");
        BattlefieldSettings.add(SettingsMenuLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AddArmyButton = new JButton();
        AddArmyButton.setText("Add Army");
        BattlefieldSettings.add(AddArmyButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteArmyButton = new JButton();
        deleteArmyButton.setText("Delete Army");
        BattlefieldSettings.add(deleteArmyButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        BattlefieldSettings.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        BattlefieldSettings.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 1, false));
        armySelectorLabel = new JLabel();
        armySelectorLabel.setText("Army Selector");
        BattlefieldSettings.add(armySelectorLabel, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        armySelectorComboBox = new JComboBox();
        BattlefieldSettings.add(armySelectorComboBox, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        BattlefieldSettings.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        BattlefieldSettings.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(11, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, 10), new Dimension(10, 10), new Dimension(10, 10), 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Power");
        BattlefieldSettings.add(label1, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        armyPowerSlider = new JSlider();
        armyPowerSlider.setMajorTickSpacing(10);
        armyPowerSlider.setPaintLabels(true);
        armyPowerSlider.setPaintTicks(true);
        armyPowerSlider.setValue(35);
        BattlefieldSettings.add(armyPowerSlider, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateArmyButton = new JButton();
        updateArmyButton.setText("Update Army");
        BattlefieldSettings.add(updateArmyButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speedLabel = new JLabel();
        speedLabel.setText("Speed");
        BattlefieldSettings.add(speedLabel, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speedSlider = new JSlider();
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setMaximum(20);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintTrack(true);
        speedSlider.setSnapToTicks(false);
        speedSlider.setValue(10);
        BattlefieldSettings.add(speedSlider, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return BattlefieldSettings;
    }

}

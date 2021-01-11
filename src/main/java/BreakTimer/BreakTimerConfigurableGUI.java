package BreakTimer;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;


public class BreakTimerConfigurableGUI {

  private JPanel rootPanel;
  private JTextField textField;
  private double currInterval;
  private JLabel label3;

  public BreakTimerConfigurableGUI(double currInterval) {
    this.currInterval = currInterval;
  }

  public JPanel getRootPanel() {
    return this.rootPanel;
  }

  {
    // GUI initializer generated by IntelliJ IDEA GUI Designer
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    rootPanel = new JPanel();
    rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
    rootPanel.setRequestFocusEnabled(true);
    final JLabel label1 = new JLabel();
    label1.setText("Break Time Frequency");
    rootPanel.add(label1,
        new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null,
            new Dimension(80, 16), null, 0, false));
    textField = new JTextField();
    textField.setAutoscrolls(true);
    textField.setEditable(true);
    textField.setEnabled(true);
    textField.setHorizontalAlignment(10);
    rootPanel.add(textField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH,
        GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null,
        null, 0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Enter your desired break interval in hours (doubles accepted)");
    label2.setVerticalAlignment(0);
    rootPanel.add(label2,
        new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null,
            null, 0, false));
    final Spacer spacer1 = new Spacer();
    rootPanel.add(spacer1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
        GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0,
        false));
    label1.setLabelFor(textField);
    this.label3 = new JLabel();
    this.setCurrentIntervalLabel();
    rootPanel.add(label3,
        new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null,
            null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return rootPanel;
  }

  public boolean hasText() {
    return !this.textField.getText().isEmpty();
  }

  public double readTextField() {
    // TODO deal with errors, show error
    double interval = -1;
    try {
      interval = Double.parseDouble(this.textField.getText());
    } catch (NumberFormatException e) {
      e.printStackTrace();
      System.out.println("Number format exception");
    } catch (NullPointerException e) {
      e.printStackTrace();
      System.out.println("Null pointer exception");
    }
    return interval;
  }

  public void setCurrentIntervalLabel() {
    if (this.currInterval <= 0) {
      label3.setText("Current interval: unset");
    }
    else {
      label3.setText("Current interval: " + this.currInterval);
    }
  }

  public void setCurrInterval(double interval) {
    this.currInterval = interval;
  }
}

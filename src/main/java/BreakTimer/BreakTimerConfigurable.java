package BreakTimer;

import BreakTimer.TimeIntervalConfiguration.State;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BreakTimerConfigurable implements ProjectComponent, SearchableConfigurable {

  private Timer timer;
  private double interval;
  private BreakTimerConfigurableGUI gui;
  NotificationGroup groupDisplayIdInfo = new NotificationGroup("Break notifications",
      NotificationDisplayType.BALLOON, true);
  NotificationGroup notificationGroup = new NotificationGroup("display id",
      NotificationDisplayType.BALLOON,
      true, null, null);

  public BreakTimerConfigurable() {}

  @Nls
  @Override
  public String getDisplayName() {
    return "Set Break Frequency (Hours)";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return "Set break frequency in hours";
  }

  @NotNull
  @Override
  public String getId() {
    return "preference.BreakFrequencyConfigurable";
  }

  @Nullable
  @Override
  public Runnable enableSearch(String s) {
    return null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    this.gui = new BreakTimerConfigurableGUI(this.interval);
    return this.gui.getRootPanel();
  }

  @Override
  public boolean isModified() {
    return this.gui.hasText()
        && this.gui.readTextField() != this.interval;
  }

  @Override
  public void apply() throws ConfigurationException {
    double userInput = this.gui.readTextField();
    if (userInput > 0) {
      this.interval = userInput;
      this.projectOpened();
      this.gui.setCurrInterval(userInput);
      this.gui.setCurrentIntervalLabel();
    }
    else {
      // TODO exception handling
    }
  }

  @Override
  public void reset() {

  }

  @Override
  public void disposeUIResources() {
    this.gui = null;
  }

  public void setTimer() {
    if(this.timer != null) {
      this.timer.stop();
    }
    this.timer = new Timer((int) (this.interval * 3600000), new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Notifications.Bus.notify(notificationGroup.createNotification("You've been working "
                + "for " + (float) interval + " hours. You should take a well deserved break!",
            MessageType.INFO));
      }
    });
  }

  @Override
  public void projectOpened() {
    if (this.interval > 0) {
      this.setTimer();
      this.timer.start();
      this.gui.setCurrInterval(this.interval);
      this.gui.setCurrentIntervalLabel();
    }
  }
}
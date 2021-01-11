package BreakTimer;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

@State(
    name = "Time Interval",
    storages = {@Storage("BreakTimer.TimeIntervalConfiguration.xml")}
)

public class TimeIntervalConfiguration implements PersistentStateComponent<TimeIntervalConfiguration.State> {

  static class State {
    public double interval;
  }

  State myState;

  @Override
  public TimeIntervalConfiguration.State getState(){
    return myState;
  }

  @Override
  public void loadState(TimeIntervalConfiguration.State state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public double getInterval() {
    return myState.interval;
  }

  public void setInterval(double interval) {
    this.myState.interval = interval;
  }
}